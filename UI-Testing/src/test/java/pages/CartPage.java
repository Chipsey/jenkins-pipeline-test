package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class CartPage extends PageObject {

    public void openWebPage(String url) {
        openAt(url);
    }

    final String increaseQuantityButtonSelector = "qty-add";
    final String decreaseQuantityButtonSelector = "qty-sub";
    final String removeItemButtonSelector = "remove-item";

    public void increaseQuantity() {
        WebElementFacade increaseQuantityButton = find(By.className(increaseQuantityButtonSelector));
        increaseQuantityButton.waitUntilVisible().click();
    }
    public void decreaseQuantity() throws InterruptedException {
        WebElementFacade decreaseQuantityButton = find(By.className(decreaseQuantityButtonSelector));
        decreaseQuantityButton.waitUntilVisible().click();
    }
    public void removeItemFromCart() {
        WebElementFacade increaseQuantityButton = find(By.className(removeItemButtonSelector));
        increaseQuantityButton.waitUntilVisible().click();
    }
}