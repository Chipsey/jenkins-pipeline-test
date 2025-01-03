package com.example.serenity.apitesting.tests.get;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static com.example.serenity.apitesting.config.Constants.USER_PASSWORD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class LibraryGetByIdNonExistTest extends BaseTest {

    private static Response response;
    private Book book;

    @When("Admin fetch a non-exist book with ID {int} using valid credentials")
    public void fetchBookByIdAdmin(Integer bookId) {

        book = null;

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.fetchBookById(BOOKS_ENDPOINT, bookId, ADMIN_USERNAME, ADMIN_PASSWORD);

    }

    @When("User fetch a non-exist book with ID {int} using valid credentials")
    public void fetchBookByIdUser(Integer bookId) {

        book = null;

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.fetchBookById(BOOKS_ENDPOINT, bookId, USER_USERNAME, USER_PASSWORD);

    }

    @Then("the response code when a book does not exists should be {int}")
    public void verifyResponseCodeForNonExistentBook(int statusCode) {

        int actualStatusCode = response.getStatusCode();
        assertThat("Expected status code " + statusCode + " but was " + actualStatusCode,
                actualStatusCode, is(statusCode));
    }


    @And("the response message should indicate the book was not found")
    public void verifyResponseMessageForNonExistentBook() {

        String responseMessage = response.getBody().asString();
        assertThat("Expected response message to indicate 'Book not found'.",
                responseMessage.contains("Book not found"), is(true));

        System.out.println("Response message: " + responseMessage);
    }
}