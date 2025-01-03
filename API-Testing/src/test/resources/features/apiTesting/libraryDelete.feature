Feature: Library API DELETE Operations

  Background:
    Given the library API is running and a book is created


  Scenario: Admin delete a book by ID 2
    When Admin delete a book using book ID 2
    Then the response code after deleting the book should be 200

  Scenario Outline: <role> delete a book by ID 3
    When <role> try to delete a book using book ID 3
    Then the response code should be 403 as user is not permitted

    Examples:
      | role  |
      | User  |
      | Non registered user |

