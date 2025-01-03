package com.example.serenity.apitesting.tests.post;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class BookManagementPostTest extends BaseTest {

    private Response response;
    private RequestHelper requestHelper;
    private String endPoint;
    private String userName;
    private String password;
    private int counter = 0;
    private int currentId;

    public void generateUniqueInt() {
        int datePart = Integer.parseInt(LocalDate.now().toString().replace("-", ""));
        counter++;
        this.currentId = Math.abs((datePart * 1000) + (counter % 1000) + ThreadLocalRandom.current().nextInt(1, 10000));
    }

    @Given("the library API is running for book creation using admin")
    public void theLibraryAPIIsRunningAdmin() {
        requestHelper = new RequestHelper(BASE_URI);
        Response healthResponse = requestHelper
                .withEndpoint(BOOKS_ENDPOINT)
                .withAuth(ADMIN_USERNAME, ADMIN_PASSWORD)
                .sendRequest("GET");
        assertThat(healthResponse.getStatusCode(), is(200));
    }

    @Given("the library API is running for book creation using user")
    public void theLibraryAPIIsRunningUser() {
        requestHelper = new RequestHelper(BASE_URI);
        Response healthResponse = requestHelper
                .withEndpoint(BOOKS_ENDPOINT)
                .withAuth(USER_USERNAME, USER_PASSWORD)
                .sendRequest("GET");
        assertThat(healthResponse.getStatusCode(), is(200));
    }

    @Given("the API endpoint is {string}")
    public void setApiEndpoint(String endpoint) {
        System.out.println("------------------------------------------------------------------------"+BASE_URI + endpoint);
        this.endPoint = endpoint;
    }

    @Given("the user is authenticated as {string} with password {string}")
    public void authenticateUser(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    @When("I send a POST request to create a new book")
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

    @And("I send a POST request to create a book with the same details")
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

    @When("I send a POST request to create a book with invalid data")
    public void sendInvalidPostRequest() {
        Book book;
        book = new Book();
        System.out.println(book.getAuthor() +"\n"+ book.getTitle() +"\n" + book.getId());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        createBook(book);
    }

    @When("I send a POST request to create a book without {string}")
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

    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }

    @Then("the response should contain the book details")
    public void verifyResponseContainsBookDetails() {
        Book responseBook = response.as(Book.class);
        assertThat(responseBook.getId(), is(this.currentId));
        assertThat(responseBook.getTitle(), is("Title - " + this.currentId));
        assertThat(responseBook.getAuthor(), is("Title - " + this.currentId));
    }

    @Then("the response should contain the error message {string}")
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
            e.printStackTrace();
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
        System.out.println("+_+_+_+_+_+_+_+_+_++_+_+_++_+_+_+_+_+_+_++_+_+_+_+_+_+_+_+_++_+_+_+_++__+_+_+_+_+_+_++_++_+_");
    }
}
