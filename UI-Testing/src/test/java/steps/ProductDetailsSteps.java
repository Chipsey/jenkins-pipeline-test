package steps;

import net.serenitybdd.annotations.Steps;
import pages.ProductDetailsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertTrue;

public class ProductDetailsSteps {

    @Steps
    private ProductDetailsPage productDetailsPage;

    @Given("I navigate to the product detail page for product {string}")
    public void navigateToProductDetailPage(String productId) {
        productDetailsPage.openProductDetailPage("https://www.singersl.com/products/" + productId);
    }

    @Then("I should see a specifications section")
    public void verifySpecificationsSection() {
        assertTrue("Specifications section is not displayed.",
                productDetailsPage.isSpecificationsSectionDisplayed());
    }

    @Then("It should list details like {string}, {string}, and {string}")
    public void verifySpecificationsDetails(String spec1, String spec2, String spec3) {
        assertTrue("Specifications details are missing.",
                productDetailsPage.areSpecificationsDetailsCorrect(spec1, spec2, spec3));
    }

    @Then("I should see the customer reviews section")
    public void verifyCustomerReviewsSection() {
        assertTrue("Customer reviews section is not displayed.",
                productDetailsPage.isCustomerReviewsSectionDisplayed());
    }

    @Then("It should display at least one review or a message {string}")
    public void verifyCustomerReviews(String noReviewsMessage) {
        assertTrue("Customer reviews or the message is not displayed.",
                productDetailsPage.verifyCustomerReviewsOrMessage(noReviewsMessage));
    }

    @Then("I should see a related products section")
    public void verifyRelatedProductsSection() {
        assertTrue("Related products section is not displayed.",
                productDetailsPage.isRelatedProductsSectionDisplayed());
    }

    @Then("It should display at least {int} related items")
    public void verifyRelatedProductsCount(int minCount) {
        assertTrue("Related products count is less than expected.",
                productDetailsPage.verifyRelatedProductsCount(minCount));
    }

    @Then("I should see the warranty information")
    public void verifyWarrantyInformation() {
        assertTrue("Warranty information is not displayed.",
                productDetailsPage.isWarrantyInformationDisplayed());
    }

    @Then("I should see the return policy details")
    public void verifyReturnPolicyDetails() {
        assertTrue("Return policy details are not displayed.",
                productDetailsPage.isReturnPolicyDisplayed());
    }

    @Then("I should see social sharing buttons for {string} and {string}")
    public void verifySocialSharingButtons(String button1, String button2) {
        assertTrue(button1 + " button is not displayed.",
                productDetailsPage.isSocialSharingButtonDisplayed(button1));
        assertTrue(button2 + " button is not displayed.",
                productDetailsPage.isSocialSharingButtonDisplayed(button2));
    }

    @Then("I should see the product title {string}")
    public void i_should_see_the_product_title(String productTitle) {
        // Add code to verify that the product title is displayed
    }

    @Then("I should see a product description that is not empty")
    public void i_should_see_a_product_description_that_is_not_empty() {
        // Add code to verify the product description is not empty
    }

    @Then("I should see a price displayed in {string}")
    public void i_should_see_a_price_displayed_in(String price) {
        // Add code to verify that the price is displayed correctly
    }

    @Then("I should see a high-quality main product image")
    public void i_should_see_a_high_quality_main_product_image() {
        // Add code to verify that the main product image is of high quality
    }

    @Then("I should see a message indicating {string}")
    public void i_should_see_a_message_indicating(String message) {
        // Add code to verify that the correct message is displayed
    }

    @Then("the {string} button should be {string}")
    public void the_button_should_be(String button, String state) {
        // Add code to verify the button state (e.g., enabled, disabled)
    }

    @Then("I should be able to navigate through the image gallery")
    public void i_should_be_able_to_navigate_through_the_image_gallery() {
        // Add code to verify the image gallery navigation functionality
    }

    @Then("I should see the zoom feature working on the product image")
    public void i_should_see_the_zoom_feature_working_on_the_product_image() {
        // Add code to verify the zoom feature on the product image
    }

}
