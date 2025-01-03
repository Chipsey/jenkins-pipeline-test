package com.example.serenity.apitesting.tests.get;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibraryGetByIdTest extends BaseTest {

    private static Response response;
    private Book book;

    @When("Admin fetch the book with ID {int} using valid credentials")
    public void fetchBookByIdAdmin(Integer bookId) {

        book = null;

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.fetchBookById(BOOKS_ENDPOINT, bookId, ADMIN_USERNAME, ADMIN_PASSWORD);
        book = response.body().as(Book.class);
    }

    @When("User fetch the book with ID {int} using valid credentials")
    public void fetchBookByIdUser(Integer bookId) {

        book = null;

        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        response = requestHelper.fetchBookById(BOOKS_ENDPOINT, bookId, USER_USERNAME, USER_PASSWORD);
//        book = response.body().as(Book.class);
    }

    @Then("the response code when a book exists should be {int}")
    public void verifyResponseCodeForBookById(int statusCode) {

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getStatusCode())
                .isEqualTo(statusCode);
        softAssertions.assertAll();
    }


    @And("the response should contain the book {int} details")
    public void verifyResponseContainsBookDetails(int bookId) {
        if (book == null) {
            assertThat("User is not permitted to fetch the book or the book is unavailable.",
                    response.getStatusCode(), is(403));
            System.out.println("User is not permitted to fetch the book with ID: " + bookId);
        } else {
            // Validate the book details
            assertThat(book, is(notNullValue()));
            assertThat(book.getId(), is(bookId));
            assertThat(book.getTitle(), is(notNullValue()));
            assertThat(book.getAuthor(), is(notNullValue()));
            System.out.println("Book details fetched successfully for book ID: " + bookId);
        }
    }
}
