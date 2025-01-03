package com.example.serenity.apitesting.tests.get;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibraryWithoutAuthGetTest extends BaseTest {
    private static Response response;

    @When("User fetches all books without providing authentication")
    public void fetchBooksWithoutAuthentication() {
        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.withEndpoint(BOOKS_ENDPOINT).sendRequest("GET"); // No authentication headers included
    }

    @When("User fetches all books with providing invalid credentials")
    public void nonRegisteredUserFetchesAllBooks() {
        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.fetchBook(BOOKS_ENDPOINT, "RandomUser", "strong-password");
    }

    @Then("Then the response code should be {int}")
    public void verifyUnauthorizedResponseCode(int statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }


}
