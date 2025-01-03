package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class ProductDetailsPage extends PageObject {

    public void openProductDetailPage(String url) {
        openAt(url);
    }

    public boolean isSpecificationsSectionDisplayed() {
        return find(By.id("specifications-section")).isPresent();
    }

    public boolean areSpecificationsDetailsCorrect(String spec1, String spec2, String spec3) {
        String pageSource = getDriver().getPageSource();
        return pageSource.contains(spec1) && pageSource.contains(spec2) && pageSource.contains(spec3);
    }

    public boolean isCustomerReviewsSectionDisplayed() {
        return find(By.cssSelector(".customer-reviews")).isDisplayed();
    }

    public boolean verifyCustomerReviewsOrMessage(String message) {
        List<WebElementFacade> reviews = findAll(By.cssSelector(".review-item"));
        return !reviews.isEmpty() || getDriver().getPageSource().contains(message);
    }

    public boolean isRelatedProductsSectionDisplayed() {
        return find(By.id("related-products-section")).isPresent();
    }

    public boolean verifyRelatedProductsCount(int minCount) {
        List<WebElementFacade> relatedProducts = findAll(By.cssSelector(".related-product-item"));
        return relatedProducts.size() >= minCount;
    }

    public boolean isWarrantyInformationDisplayed() {
        return find(By.id("warranty-info")).isPresent();
    }

    public boolean isReturnPolicyDisplayed() {
        return find(By.id("return-policy")).isPresent();
    }

    public boolean isSocialSharingButtonDisplayed(String button) {
        return find(By.cssSelector(".social-share-" + button.toLowerCase())).isPresent();
    }
}
