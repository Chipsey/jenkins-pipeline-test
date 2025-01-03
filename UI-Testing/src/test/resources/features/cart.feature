Feature: Add to Cart Functionality

  Scenario: Adding a single product to the cart
    Given I open the product page
    When I click Add to Cart
    Then I should see the message "added to"
    And the cart should show 1 item

  Scenario: Adding multiple quantities of the same product to the cart
    Given I open the product 0
    When I click Product Add to Cart
    Then I should see the message "added to"
    And I open the Cart Page
    When I increase the product quantity to 2
    Then I should see the message "Your shopping cart has been updated."
    Then the cart should show 2 items

  Scenario: Decreasing quantity of an item
    Given I open the product 0
    When I click Product Add to Cart
    Then I should see the message "added to"
    And I open the Cart Page
    When I increase the product quantity to 2
    Then I should see the message "Your shopping cart has been updated."
    And I open the Cart Page
    When I decrease the product quantity to 1
    Then I should see the message "Your shopping cart has been updated."
    Then the cart should show 1 items

  Scenario: Adding different products to the cart
    Given I open the product 0
    When I click Product Add to Cart
    And I open the product 1
    When I click Product Add to Cart
    Then the cart should show 2 items

  Scenario: Removing item from the cart
    Given I open the product 0
    When I click Product Add to Cart
    Then I should see the message "added to"
    And I open the Cart Page
    When I click the Remove Button
    Then I should see the message "Your shopping cart has been updated."
    Then the cart should show 0 items