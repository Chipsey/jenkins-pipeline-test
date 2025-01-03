Feature: Search Functionality on UI-Testing

  Scenario: Searching for a single product
    Given I open the HomePage
    When I search for "laptop"
    Then I should see search results related to "laptop"

  Scenario: Searching for only special Characters
    Given I open the HomePage
    When I search for "!@#%^&*"
    Then I should see a message indicating no search results were found