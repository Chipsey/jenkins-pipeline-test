package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import java.util.Objects;

public class HomePage extends PageObject {

    final String searchBarSelector = "edit-search";
    final String productItemSelector = "product-item";

    public void openWebPage(String url) {
        openAt(url);
    }

    public void searchForProduct(String product) {
        WebElementFacade searchBar = find(By.id(searchBarSelector));
        searchBar.waitUntilVisible().waitUntilClickable().typeAndEnter(product);
    }

    public boolean verifyProductInSearchResults(String product) throws InterruptedException {
        Thread.sleep(1000);
        return Objects.requireNonNull(this.getDriver().getPageSource()).contains(product);
    }

    public boolean verifyNoSearchResultsMessage() throws InterruptedException {
        Thread.sleep(1000);
        String pageSource = this.getDriver().getPageSource();
        assert pageSource != null;
        boolean messageFound = pageSource.contains("Sorry, no products available!");
        if (messageFound) {
            System.out.println("Message found in page source.");
        } else {
            System.err.println("Message not found in page source.");
        }
        return messageFound;
    }
}
