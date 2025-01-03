Feature: Product Display Validation on UI-Testing

  Scenario: Verifying product images are visible and correctly loaded
    Given I navigate to the Singer website
    When I view the product listings
    Then all products should have visible and correctly loaded images

  Scenario: Verifying product titles are visible and readable
    Given I navigate to the Singer website
    When I view the product listings
    Then all products should have visible and readable titles

  Scenario: Verifying product prices are clearly formatted
    Given I navigate to the Singer website
    When I view the product listings
    Then all products should have clearly formatted prices

  Scenario: Verifying sale tags are visible for sale items
    Given I navigate to the Singer website
    When I view the product listings
    Then all sale items should have visible sale tags

  Scenario: Verifying product descriptions are displayed properly
    Given I navigate to the Singer website
    When I view the product listings
    Then all products should have visible and meaningful descriptions
