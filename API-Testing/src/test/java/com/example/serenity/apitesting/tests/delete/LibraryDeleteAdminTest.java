package com.example.serenity.apitesting.tests.delete;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LibraryDeleteAdminTest extends BaseTest {

    private static Response response;

    @When("Admin delete a book using book ID {int}")
    public void adminDeleteBookUsingBookID(Integer bookID) {

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.deleteBookById(BOOKS_ENDPOINT, bookID, ADMIN_USERNAME, ADMIN_PASSWORD);

            assertThat("Failed to delete the book", response.getStatusCode(), is(200));
            System.out.println("Book deleted successfully with ID: " + bookID);
    }

    @Then("the response code after deleting the book should be {int}")
    public void theResponseCodeAfterDeletingTheBookShouldBe(int expectedResponseCode) {
        assertThat(response.getStatusCode(), is(expectedResponseCode));
    }

}
