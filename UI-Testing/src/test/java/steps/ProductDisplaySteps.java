package steps;

import net.serenitybdd.core.pages.PageObject;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions; // For improved assertions with AssertJ

public class ProductDisplaySteps extends PageObject {

    @Given("I navigate to the Singer website")
    public void navigateToWebsite() {
        // Navigate to the specified URL
        openUrl("https://www.singersl.com");
    }

    @When("I view the product listings")
    public void viewProductListings() {
        // Scroll down gradually to load product listings
        for (int i = 0; i < 5; i++) {
            evaluateJavascript("window.scrollBy(0, 500);"); // Scroll 500 pixels down
            waitABit(1000); // Wait for 1 second after each scroll
        }
    
        // Ensure products are loaded by checking product container visibility
        waitFor(".product-container"); // Replace with actual selector for the product container
    
        // Click the first product for interaction (optional)
        if (!$$(".product-container").isEmpty()) {
            $$(".product-container").get(0).click(); // Clicks the first product
        } else {
            throw new AssertionError("No products found on the page!");
        }
    
        // Log the total number of products displayed
        System.out.println("Total number of products displayed: " + $$(".product-container").size());
    }
    

    @Then("all products should have visible and correctly loaded images")
    public void verifyProductImages() {
        // Assert that all product images are visible
        Assertions.assertThat(
            $$(".product-image").stream().allMatch(image -> image.isVisible())
        ).isTrue();
    }

    @Then("all products should have visible and readable titles")
    public void verifyProductTitles() {
        // Assert that product titles are visible and not empty
        Assertions.assertThat(
            $$(".product-title").stream().allMatch(title -> !title.getText().isEmpty())
        ).isTrue();
    }

    @Then("all products should have clearly formatted prices")
    public void verifyProductPrices() {
        // Assert that prices match the specified pattern
        Assertions.assertThat(
            $$(".product-price").stream().allMatch(price -> price.getText().matches("Rs\\.\\s[0-9,]+"))
        ).isTrue();
    }

    @Then("all sale items should have visible sale tags")
    public void verifySaleTags() {
        // Assert that all sale tags are visible
        Assertions.assertThat(
            $$(".sale-tag").stream().allMatch(tag -> tag.isVisible())
        ).isTrue();
    }
}
