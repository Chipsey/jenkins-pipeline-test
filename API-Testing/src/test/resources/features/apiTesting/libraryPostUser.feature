Feature: Library API POST USER Operations

  Scenario: Successfully create a book
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "user" with password "password"
    When I send a POST request to create a new book
    Then the response status code should be 201
    And the response should contain the book details

  Scenario: Create a book without a title
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "user" with password "password"
    When I send a POST request to create a book without "title"
    Then the response status code should be 400

  Scenario: Create a book without an author
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "user" with password "password"
    When I send a POST request to create a book without "author"
    Then the response status code should be 400

  Scenario: Create a book without an id
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "user" with password "password"
    When I send a POST request to create a book without "id"
    Then the response status code should be 201

  Scenario: Unauthorized user attempts to create a book with invalid username
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "invalidUser" with password "password"
    When I send a POST request to create a new book
    Then the response status code should be 401

  Scenario: Unauthorized user attempts to create a book with invalid password
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "user" with password "wrongPassword"
    When I send a POST request to create a new book
    Then the response status code should be 401

  Scenario: Duplicate book creation
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "user" with password "password"
    When I send a POST request to create a new book
    Then the response status code should be 201
    And the response should contain the book details
    And I send a POST request to create a book with the same details
    Then the response status code should be 208
    And the response should contain the book details
    And the response should contain the error message "Book Already Exists"

  Scenario: Invalid input data
    Given the library API is running for book creation using user
    And the API endpoint is "/books"
    And the user is authenticated as "user" with password "password"
    When I send a POST request to create a book with invalid data
    Then the response status code should be 400
