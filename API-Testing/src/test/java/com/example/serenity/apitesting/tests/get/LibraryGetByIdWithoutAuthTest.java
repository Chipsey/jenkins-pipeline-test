package com.example.serenity.apitesting.tests.get;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class LibraryGetByIdWithoutAuthTest extends BaseTest {
    private static Response response;
    private Book book;

    @When("User fetches a book with ID {int} without providing authentication")
    public void fetchBookByIdAdmin(Integer bookId) {

        book = null;

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.withEndpoint(BOOKS_ENDPOINT + "/" + bookId).sendRequest("GET");

    }

    @When("User fetches a book with ID {int} with providing invalid credentials")
    public void fetchBookByIdUser(Integer bookId) {

        book = null;

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.fetchBookById(BOOKS_ENDPOINT, bookId, "RandomUser", "strong-password");
//        book = response.body().as(Book.class);
    }

    @Then("the response code for book Id 1 should be {int}")
    public void verifyUnauthorizedResponseCode(int statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }
}
