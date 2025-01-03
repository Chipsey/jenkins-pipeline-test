package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class ProductDescriptivePage extends PageObject {

    final String cartBadgeSelector = ".add-to-cart-id";
    final String addToCartButtonSelector = "add-to-cart-id";


    public void openProductDescriptionPage(String baseUrl) {
        openAt(baseUrl);
    }

    public void clickAddToCartButton() throws InterruptedException {
        WebElementFacade addToCartButton = find(By.id(addToCartButtonSelector));
        addToCartButton.waitUntilVisible().click();
        Thread.sleep(5000);
    }

    public void setQuantity(String quantity) {
        WebElementFacade quantityField = find(By.id("quantity"));
        quantityField.waitUntilVisible().clear();
        quantityField.type(quantity);
    }
}