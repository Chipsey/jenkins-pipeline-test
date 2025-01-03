Feature: Product Details Functionality on singersl.com

  Scenario: Viewing product details for a valid product
    Given I navigate to the product detail page for product "12345"
    Then I should see the product title "Dell Inspiron 15"
    And I should see a product description that is not empty
    And I should see a price displayed in "LKR"
    And I should see a high-quality main product image

  Scenario: Viewing product details for an out-of-stock product
    Given I navigate to the product detail page for product "67890"
    Then I should see the product title "Samsung Galaxy S21"
    And I should see a message indicating "Out of Stock"
    And the "Add to Cart" button should be "disabled"

  Scenario: Viewing product details for a non-existent product
    Given I navigate to the product detail page for product "00000"
    Then I should see a message indicating "Product not found"

  Scenario: Verifying the image gallery for a product
    Given I navigate to the product detail page for product "13579"
    Then I should see a high-quality main product image
    And I should be able to navigate through the image gallery
    And I should see the zoom feature working on the product image

  Scenario: Verifying add-to-cart functionality for an available product
    Given I navigate to the product detail page for product "24680"
    Then I should see the product title "Sony WH-1000XM4"
    And the "Add to Cart" button should be "enabled"
