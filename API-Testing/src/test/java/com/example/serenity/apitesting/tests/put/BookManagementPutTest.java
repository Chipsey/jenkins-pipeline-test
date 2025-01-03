package com.example.serenity.apitesting.tests.put;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class BookManagementPutTest extends BaseTest {

    private Response response;
    private RequestHelper requestHelper;
    private String endPoint;
    private String userName;
    private String password;
    private int counter = 0;
    private int currentId;
    private int responseBookId;

    public void generateUniqueInt() {
        int datePart = Integer.parseInt(LocalDate.now().toString().replace("-", ""));
        counter++;
        this.currentId = Math.abs((datePart * 1000) + (counter % 1000) + ThreadLocalRandom.current().nextInt(1, 10000));
    }

    @Given("the library API is running for book creation using admin for update test")
    public void theLibraryAPIIsRunningAdmin() {
        requestHelper = new RequestHelper(BASE_URI);
        Response healthResponse = requestHelper
                .withEndpoint(BOOKS_ENDPOINT)
                .withAuth(ADMIN_USERNAME, ADMIN_PASSWORD)
                .sendRequest("GET");
        assertThat(healthResponse.getStatusCode(), is(200));
    }

    @Given("the library API is running for book creation using user for update test")
    public void theLibraryAPIIsRunningUser() {
        requestHelper = new RequestHelper(BASE_URI);
        Response healthResponse = requestHelper
                .withEndpoint(BOOKS_ENDPOINT)
                .withAuth(USER_USERNAME, USER_PASSWORD)
                .sendRequest("GET");
        assertThat(healthResponse.getStatusCode(), is(200));
    }

    @Given("the API endpoint is {string} for update test")
    public void setApiEndpoint(String endpoint) {
        System.out.println("------------------------------------------------------------------------"+BASE_URI + endpoint);
        this.endPoint = endpoint;
    }

    @Given("the user is authenticated as {string} with password {string} for update test")
    public void authenticateUser(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    @When("I send a POST request to create a new book for update test")
    public void sendPostRequestNewBook() {
        generateUniqueInt();
        Book book;
        book = new Book(
                this.currentId,
                "Title - "+this.currentId,
                "Author - "+this.currentId

        );
        System.out.println(book.getAuthor() +"\n"+ book.getTitle() +"\n" + book.getId());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        createBook(book);
    }

    @And("I send a POST request to create a book with the same details for update test")
    public void sendSamePostRequest() {
        Book book;
        book = new Book(
                this.currentId,
                "Title - "+this.currentId,
                "Author - "+this.currentId

        );
        System.out.println(book.getAuthor() +"\n"+ book.getTitle() +"\n" + book.getId());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        createBook(book);
    }

    @When("I send a POST request to create a book with invalid data for update test")
    public void sendInvalidPostRequest() {
        Book book;
        book = new Book();
        System.out.println(book.getAuthor() +"\n"+ book.getTitle() +"\n" + book.getId());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        createBook(book);
    }

    @When("I send a POST request to create a book without {string} for update test")
    public void sendPostRequestNewBookVariations(String item) {
        generateUniqueInt();
        Book book;
        switch (item) {
            case "author": {
                book = new Book(
                        this.currentId,
                        "Title - " + this.currentId,
                        1
                );
                break;
            }
            case "title": {
                book = new Book(
                        this.currentId,
                        "Author - " + this.currentId,
                        2
                );
                break;
            }
            case "id": {
                book = new Book(
                        "Title - " + this.currentId,
                        "Author - " + this.currentId
                );
                break;
            }
            default: {
                book = new Book();
                break;
            }
        }

        System.out.println(book.getAuthor() +"\n"+ book.getTitle() +"\n" + book.getId());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        createBook(book);
    }

    @Then("the response status code should be {int} for update test")
    public void verifyResponseStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }

    @Then("the response should contain the book details for update test")
    public void verifyResponseContainsBookDetails() {
        Book responseBook = response.as(Book.class);
        assertThat(responseBook.getId(), is(this.currentId));
        assertThat(responseBook.getTitle(), is("Title - " + this.currentId));
        assertThat(responseBook.getAuthor(), is("Title - " + this.currentId));
    }

    @Then("the response should contain the error message {string} for update test")
    public void verifyResponseContainsErrorMessage(String errorMessage) {
        assertThat(response.body().asString(), containsString(errorMessage));
    }


    public void createBook(Book book) {
        // Convert the Book object to JSON using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "";
        try {
            jsonBody = objectMapper.writeValueAsString(book);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing Book object to JSON", e);
        }

        System.out.println(jsonBody);
        response = requestHelper
                .withEndpoint(this.endPoint)
                .withAuth(this.userName, this.password)
                .withBody(jsonBody)
                .sendRequest("POST");
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println(this.userName+this.password);
        System.out.println(this.endPoint);
        System.out.println(response.getBody().asString());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++_");
    }

    /// Put Testing Coding

    @When("the API endpoint is set with book id")
    public void setApiForUpdate() {
        this.endPoint = "/books/"+this.responseBookId;
        System.out.println("------------------------------------------------------------------------"+endPoint);
    }

    @When("I send a POST request to {string} create a new book for update")
    public void createNewBook(String endPoint) {
        this.endPoint = endPoint;
        generateUniqueInt();
        Book book = new Book(
                this.currentId,
                "Title - "+this.currentId,
                "Author - "+this.currentId

        );
        createBook(book);
        Book responseBook = response.as(Book.class);
        this.responseBookId = responseBook.getId();
        System.out.println("Response Id: "+this.responseBookId);
    }

    @When("I send a PUT request to update the book with the same id")
    public void updateBookWithSameId() {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = new Book(
                this.responseBookId,
                "updated title"+this.responseBookId,
                "updated author"+this.responseBookId
        );
        String jsonBody = "";
        try {
            jsonBody = objectMapper.writeValueAsString(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Request for the update: "+jsonBody);
        System.out.println("Request for the update endpoint: "+this.endPoint);
        response = requestHelper
                .withEndpoint(this.endPoint)
                .withAuth(this.userName, this.password)
                .withBody(jsonBody)
                .sendRequest("PUT");

        System.out.println("Response: " + response.body().asString());
    }

    @When("I send a PUT request to update the book with not existing book id")
    public void updateBookWithWrongId() {
        generateUniqueInt();
        this.endPoint = "/books/"+this.currentId;
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = new Book(
                this.currentId,
                "updated title"+this.currentId,
                "updated author"+this.currentId
        );
        String jsonBody = "";
        try {
            jsonBody = objectMapper.writeValueAsString(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonBody);
        response = requestHelper
                .withEndpoint(this.endPoint)
                .withAuth(this.userName, this.password)
                .withBody(jsonBody)
                .sendRequest("PUT");
    }

    @When("I send a PUT request to update the book with null {string} field")
    public void updateBookWithNullField(String nullField) {
        String jsonBody = "{ \"id\": " + this.responseBookId + ", ";
        if (!"title".equals(nullField)) {
            jsonBody += "\"title\": \"updated title\""+ this.responseBookId + ", ";
        } else {
            jsonBody += "\"title\": null, ";
        }
        if (!"author".equals(nullField)) {
            jsonBody += "\"author\": \"updated author\" "+ this.responseBookId;
        } else {
            jsonBody += "\"author\": null ";
        }
        jsonBody += "}";

        System.out.println("Constructed JSON body: " + jsonBody);

        response = requestHelper
                .withEndpoint(this.endPoint)
                .withAuth(this.userName, this.password)
                .withBody(jsonBody)
                .sendRequest("PUT");
    }

    @Then("the response should contain the updated book details")
    public void verifyResponseUpdateBookDetails() {
        Book responseBook = response.as(Book.class);
        assertThat(responseBook.getId(), is(this.responseBookId));
        assertThat(responseBook.getTitle(), is("updated title"+ this.responseBookId));
        assertThat(responseBook.getAuthor(), is("updated author"+ this.responseBookId));
    }
}