Feature: Product Filtering

  Scenario: Filter by Minimum Price
    Given User is on the product page
    When User filters with minimum price "2000"
    Then Products should be filtered to have prices greater than or equal to "2000"

  Scenario: Filter by Category
    Given User is on the product page
    When User filters by category "Computers"
    Then Products should be filtered by category "Computers"

  Scenario: Filter by Availability
    Given User is on the product page
    When User filters by "Exclude Stock Out"
    Then Out of stock product should not be listed

  Scenario: Filter by Offers
    Given User is on the product page
    When User filters by offer "20% or More"
    Then Products that are not in the offer category should not be listed

  Scenario: Combine Multiple Filters
    Given User is on the product page
    When User applies category "Computers", minimum price "2000", and offer "20% or More"
    Then Products should be filtered by all specified criteria