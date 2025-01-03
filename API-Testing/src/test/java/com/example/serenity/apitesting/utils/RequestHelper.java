package com.example.serenity.apitesting.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestHelper {

    private final String baseUri;
    private String endpoint;
    private String username;
    private String password;
    private Object body;

    public RequestHelper(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response fetchBookById(String endpoint, Integer bookId, String username, String password) {
        return this.withEndpoint(endpoint + "/" + bookId)
                .withAuth(username, password)
                .sendRequest("GET");
    }

    public Response fetchBook(String endpoint,String username, String password) {
        return this.withEndpoint(endpoint)
                .withAuth(username, password)
                .sendRequest("GET");
    }

    public Response deleteBookById(String endpoint, Integer bookId, String username, String password) {
        return this.withEndpoint(endpoint + "/" + bookId)
                .withAuth(username, password)
                .sendRequest("DELETE");
    }

    public RequestHelper withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public RequestHelper withAuth(String username, String password) {
        this.username = username;
        this.password = password;
        return this;
    }

    public RequestHelper withBody(Object body) {
        this.body = body;
        return this;
    }

    private RequestSpecification buildRequest() {
        RequestSpecification request = RestAssured.given()
                .baseUri(this.baseUri)
                .basePath(this.endpoint)
                .accept(ContentType.JSON);


        if (this.username != null && this.password != null) {
            request.auth().basic(this.username, this.password);
        }

        if (this.body != null) {
            request.contentType(ContentType.JSON).body(this.body);
        }

        return request;
    }

    public Response sendRequest(String method) {
        switch (method.toUpperCase()) {
            case "GET":
                return buildRequest().get();
            case "POST":
                return buildRequest().post();
            case "PUT":
                return buildRequest().put();
            case "DELETE":
                return buildRequest().delete();
            default:
                throw new IllegalArgumentException("Unsupported HTTP Method: " + method);
        }
    }
}