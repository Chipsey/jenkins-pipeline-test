package com.example.serenity.apitesting.tests;

import com.example.serenity.apitesting.base.BaseTest;
import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

import static com.example.serenity.apitesting.config.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LibraryCommonStepsTest  extends BaseTest {

    private String createdBookId;
    private static boolean isSetupDone = false;

//    @Before
//    public void setup() {
//        if (!isSetupDone) {
//
//                Book book = new Book();
//                book.setTitle("Sample Book for Test");
//                book.setAuthor("Test Author");
//
//                RequestHelper requestHelper = new RequestHelper(BASE_URI);
//                Response response = requestHelper
//                        .withEndpoint(CREATE_BOOK_ENDPOINT)
//                        .withAuth(ADMIN_USERNAME, ADMIN_PASSWORD)
//                        .withBody(book)
//                        .sendRequest("POST");
//
//                if (response.getStatusCode() == 201) {
//                    String bookId = response.jsonPath().getString("id");
//                    System.out.println("Sample book created successfully with ID: " + bookId);
//                } else {
//                    System.err.println("Failed to create sample book: " + response.body().asString());
//                }
//
//                // Set the flag to true so the setup runs only once
//                isSetupDone = true;
//            }
//        }


    @Given("the library API is running and a book is created")
    public void theLibraryAPIIsRunningAndBookCreated() {
        RequestHelper requestHelper = new RequestHelper(BASE_URI);
        Response response = requestHelper.fetchBook(BOOKS_ENDPOINT, ADMIN_USERNAME, ADMIN_PASSWORD);
        assertThat("API is not running", response.getStatusCode(), is(200));

//        // Create a book
//        String uniqueTitle = "Test Book " + System.currentTimeMillis();
//        createdBookId = createInitialBook(uniqueTitle, "Default Author");

    }



//    public void deleteCreatedBook() {
//        if (createdBookId != null) {
//            RequestHelper requestHelper = new RequestHelper(BASE_URI);
//            Response response = requestHelper
//                    .withEndpoint(CREATE_BOOK_ENDPOINT + "/" + createdBookId)
//                    .withAuth(USER_USERNAME, ADMIN_PASSWORD)
//                    .sendRequest("DELETE");
//
//            assertThat("Failed to delete the book", response.getStatusCode(), is(200));
//            System.out.println("Book deleted successfully with ID: " + createdBookId);
//        }
//    }
//
//    @After
//    public void cleanUp() {
//        if (createdBookId != null) {
//            deleteCreatedBook();
//        }
//    }
}
