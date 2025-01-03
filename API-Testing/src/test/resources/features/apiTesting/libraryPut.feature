Feature: Library API PUT Operations

  Scenario: Successfully update a book with valid data
    Given the library API is running for book creation using admin for update test
    And the user is authenticated as "admin" with password "password" for update test
    And I send a POST request to "/books" create a new book for update
    And the API endpoint is set with book id
    When I send a PUT request to update the book with the same id
    Then the response status code should be 200 for update test
    And the response should contain the updated book details

  Scenario: Book not found for update by admin
    Given the library API is running for book creation using admin for update test
    And the user is authenticated as "admin" with password "password" for update test
    When I send a PUT request to update the book with not existing book id
    Then the response status code should be 404 for update test
    And the response should contain the error message "Book not found" for update test

  Scenario: Unauthorized user trying to update a book for update test
    Given the library API is running for book creation using admin for update test
    And the user is authenticated as "user" with password "password" for update test
    And I send a POST request to "/books" create a new book for update
    And the user is authenticated as "unauthorizedUser" with password "wrongPassword" for update test
    And the API endpoint is set with book id
    When I send a PUT request to update the book with the same id
    Then the response status code should be 401 for update test
    And the response should contain the error message "User is not authorized." for update test

  Scenario: Forbidden request to update a book by user
    Given the library API is running for book creation using admin for update test
    And the user is authenticated as "user" with password "password" for update test
    And I send a POST request to "/books" create a new book for update
    And the API endpoint is set with book id
    When I send a PUT request to update the book with the same id
    Then the response status code should be 403 for update test
    And the response should contain the error message "User is not permitted." for update test

  Scenario: Missing mandatory fields in the request body while updating
    Given the library API is running for book creation using admin for update test
    And the user is authenticated as "admin" with password "password" for update test
    And I send a POST request to "/books" create a new book for update
    And the API endpoint is set with book id
    When I send a PUT request to update the book with null "title" field
    Then the response status code should be 400 for update test
    And the response should contain the error message "Bad request." for update test

