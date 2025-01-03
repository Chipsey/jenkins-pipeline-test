package com.example.serenity.apitesting.tests.get;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import com.google.gson.reflect.TypeToken;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.lang.reflect.Type;
import java.util.List;

public class LibraryGetTest extends BaseTest {

    private static List<Book> bookList;
    private static Response response;

    @When("Admin fetch all books with valid credentials")
    public void fetchAllBooksWithValidCredentialsAdmin() {
        Type bookListType = new TypeToken<List<Book>>() {}.getType();
        RequestHelper requestHelper = new RequestHelper( BASE_URI);
        response = requestHelper.fetchBook(BOOKS_ENDPOINT, ADMIN_USERNAME, ADMIN_PASSWORD);
        bookList = response.body().as(bookListType);
    }

    @When("User fetch all books with valid credentials")
    public void fetchAllBooksWithValidCredentialsUser() {
        Type bookListType = new TypeToken<List<Book>>() {}.getType();
        RequestHelper requestHelper = new RequestHelper( BASE_URI);
        response = requestHelper.fetchBook(BOOKS_ENDPOINT, USER_USERNAME, USER_PASSWORD);
        bookList = response.body().as(bookListType);
    }

    @Then("the response code should be {int}")
    public void verifyResponseCode(int statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }

    @Then("the response should contain a list of books")
    public void verifyResponseContainsBooks() {
        assertThat(bookList, is(notNullValue()));
        assertThat(bookList.size(), greaterThan(0));
    }


}
