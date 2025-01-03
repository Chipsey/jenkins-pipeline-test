Feature: Search Functionality on UI-Testing

  Scenario: Searching for a single product
    Given I open the HomePage
    When I search for "laptop"
    Then I should see search results related to "laptop"

  Scenario: Searching for a product with multiple keywords
    Given I open the HomePage
    When I search for "dell laptop"
    Then I should see search results related to "dell"

  Scenario: Searching for a product with special characters
    Given I open the HomePage
    When I search for "msi modern 15 B13M"
    Then I should see search results related to "msi modern 15 B13M"

  Scenario: Searching for a non-existent product
    Given I open the HomePage
    When I search for "xyz123nonexistentproduct"
    Then I should see a message indicating no search results were found

  Scenario: Searching for only special Characters
    Given I open the HomePage
    When I search for "!@#%^&*"
    Then I should see a message indicating no search results were found