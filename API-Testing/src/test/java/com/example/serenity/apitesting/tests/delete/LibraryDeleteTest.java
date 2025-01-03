package com.example.serenity.apitesting.tests.delete;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static com.example.serenity.apitesting.config.Constants.ADMIN_PASSWORD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LibraryDeleteTest extends BaseTest {

    private static Response response;

    @When("User try to delete a book using book ID {int}")
    public void userDeleteBookUsingBookID(Integer bookID) {

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.deleteBookById(BOOKS_ENDPOINT, bookID, USER_USERNAME, USER_PASSWORD);

        assertThat("Failed to delete the book", response.getStatusCode(), is(403));
        System.out.println("Book deleted successfully with ID: " + bookID);
    }

    @When("Non registered user try to delete a book using book ID {int}")
    public void nonRegisteredUserDeleteBookUsingBookID(Integer bookID) {

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.deleteBookById(BOOKS_ENDPOINT, bookID, "RandomUser", "strong-password");

        assertThat("Failed to delete the book", response.getStatusCode(), is(403));
        System.out.println("Book deleted successfully with ID: " + bookID);
    }

    @Then("the response code should be {int} as user is not permitted")
    public void theResponseCodeAfterDeletingTheBookShouldBe(int expectedResponseCode) {
        assertThat(response.getStatusCode(), is(expectedResponseCode));
    }
}
