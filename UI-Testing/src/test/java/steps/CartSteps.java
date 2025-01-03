package steps;

import config.Constants;
import io.cucumber.java.en.And;
import net.serenitybdd.annotations.Steps;
import pages.CartPage;
import pages.ProductDescriptivePage;
import pages.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartSteps {

    @Steps
    private ProductPage productPage;

    @Steps
    private ProductDescriptivePage productDescriptivePage;
    @Steps
    private CartPage cartPage;

    final String baseUrl = Constants.BASE_URL+Constants.PRODUCT_PAGE_ENDPOINT;
    final String cartUrl = Constants.BASE_URL+Constants.CART_PAGE_ENDPOINT;

    @Given("I open the product page")
    public void openProductPage() {
        productPage.openProductPage(baseUrl);
    }

    @And("I open the Cart Page")
    public void openCartPage() {
        cartPage.openWebPage(cartUrl);
    }

    @Given("I open the product {int}")
    public void openProductDescriptionPage(int productNumber) {
        productDescriptivePage.openProductDescriptionPage(Constants.PRODUCT_ENDPOINT[productNumber]);
    }

    @When("I click Add to Cart")
    public void clickAddToCartButton() {
        productPage.clickAddToCartButton();
    }

    @When("I click Product Add to Cart")
    public void clickProductAddToCartButton() throws InterruptedException {
        productDescriptivePage.clickAddToCartButton();
    }

    @When("I increase the product quantity to 2")
    public void setQuantity() {
        cartPage.increaseQuantity();
    }

    @When("I decrease the product quantity to 1")
    public void setQuantityDown() throws InterruptedException {
        cartPage.decreaseQuantity();
    }

    @When("I click the Remove Button")
    public void removeItem() {
        cartPage.removeItemFromCart();
    }

    @Then("I should see the message {string}")
    public void verifyMessage(String message) {
        assertTrue("Expected message not found: " + message, productPage.checkVisibility(message));
    }


    @And("the cart should show {int} item(s)")
    public void verifyCartItems(int itemCount) throws InterruptedException {
        assertEquals(productPage.getCartItemCount(), itemCount);
    }

    @Then("I should be prompted to log in or continue as a guest")
    public void verifyLoginPrompt() {
        assertTrue(productPage.isLoginPromptDisplayed());
    }
}