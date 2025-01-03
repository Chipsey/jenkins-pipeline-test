package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class ProductPage extends PageObject {

    private static final String ADD_TO_CART_BUTTON_SELECTOR = "//a[contains(@class, 'cart-link-btn')]";
    private static final String QUANTITY_FIELD_ID = "quantity";
    private static final String CART_BADGE_SELECTOR = ".cart-block-count";
    private static final String LOGIN_PROMPT_ID = "login-prompt";

    public void openProductPage(String baseUrl) {
        openAt(baseUrl);
    }

    public void clickAddToCartButton() {
        List<WebElementFacade> addToCartButtons = findAll(By.xpath(ADD_TO_CART_BUTTON_SELECTOR));
        if (!addToCartButtons.isEmpty()) {
            WebElementFacade firstAddToCartButton = addToCartButtons.get(0);
            firstAddToCartButton.waitUntilEnabled().waitUntilVisible().click();
        } else {
            throw new AssertionError("No 'Add to Cart' buttons found on the page.");
        }
    }

    public int getCartItemCount() throws InterruptedException {
        Thread.sleep(5000);
        WebElementFacade cartBadge = find(By.cssSelector(CART_BADGE_SELECTOR));
        String cartCountText = cartBadge.getText();
        try {
            return Integer.parseInt(cartCountText);
        } catch (NumberFormatException e) {
            throw new AssertionError("Cart badge does not contain a valid number: " + cartCountText);
        }
    }

    public boolean isLoginPromptDisplayed() {
        WebElementFacade loginPrompt = find(By.id(LOGIN_PROMPT_ID));
        return loginPrompt.waitUntilVisible().isVisible();
    }

    public boolean checkVisibility(String message) {
        return Objects.requireNonNull(getDriver().getPageSource()).contains(message);
    }

    public String getPageSource() {
        return getDriver().getPageSource();
    }
}