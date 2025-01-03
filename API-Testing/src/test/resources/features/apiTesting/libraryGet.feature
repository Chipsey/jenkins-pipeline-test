Feature: Library API GET Operations
  As a user of the library system,
  I want to retrieve information about books via the API,
  So that I can access library resources based on my user role.

  Background:
    Given the library API is running and a book is created

  Scenario Outline: <role> successfully fetches all books from the library
    When <role> fetch all books with valid credentials
    Then the response code should be 200
    And the response should contain a list of books
    Examples:
      | role  |
      | Admin |
      | User  |

  Scenario Outline: Fetch books <authentication>
    When User fetches all books <authentication>
    Then Then the response code should be 401

    Examples:
      | authentication                      |
      | without providing authentication    |
      | with providing invalid credentials  |

  Scenario Outline: Fetch a specific book by ID
    When <role> fetch the book with ID <bookId> using valid credentials
    Then the response code when a book exists should be 200
    And the response should contain the book <bookId> details

    Examples:
      | role  | bookId |
      | Admin | 1      |
      | User  | 1      |

  Scenario Outline: Fetch a non-existent book
    When <role> fetch a non-exist book with ID <bookId> using valid credentials
    Then the response code when a book does not exists should be 404
    And the response message should indicate the book was not found

    Examples:
      | role  | bookId |
      | Admin | 999      |
      | User  | 999      |

  Scenario Outline: Fetch book by ID <authentication>
    When User fetches a book with ID 1 <authentication>
    Then the response code for book Id 1 should be 401

    Examples:
      | authentication                      |
      | without providing authentication    |
      | with providing invalid credentials  |