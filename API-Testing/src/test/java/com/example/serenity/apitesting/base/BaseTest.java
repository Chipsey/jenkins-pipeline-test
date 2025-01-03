package com.example.serenity.apitesting.base;


import com.example.serenity.apitesting.models.Book;
import com.example.serenity.apitesting.utils.RequestHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import static com.example.serenity.apitesting.config.Constants.*;



public abstract class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URI;

    }

}
