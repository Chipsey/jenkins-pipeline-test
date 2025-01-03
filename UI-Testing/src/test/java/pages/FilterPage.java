package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FilterPage extends PageObject {

    private final By minPriceInputLocator = By.xpath("//input[@id='amount-min']");
    private final By categoryComputersCheckboxLocator = By.xpath("//div[@id='block-categories']//label[contains(.,'Mobile & Tablets, Computers, Printers, Projectors & Accessories')]");
    private final By stockOutCheckBoxLocator = By.xpath("//input[@id='stock-0']");
    private final By offer20PercentCheckboxLocator = By.xpath("//div[@id='block-offers']//label[contains(.,'20% or More')]");

    @FindBy(xpath = "//div[@class = 'product-card-wrap']")
    List<WebElementFacade> productCards;

    public void openWebPage(String url) {
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        openAt(url);
        productCards = findAll(By.xpath("//div[@class = 'product-card-wrap']"));
    }

    public WebElementFacade waitForElement(By locator, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeOutInSec));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return $(element);
    }


    public void enterMinPrice(String minPrice) {
        WebElementFacade minPriceElement = waitForElement(minPriceInputLocator, 20).waitUntilVisible().waitUntilClickable();
        minPriceElement.type(minPrice);
    }


    public void selectCategoryComputers() {
        WebElementFacade category = waitForElement(categoryComputersCheckboxLocator, 20).waitUntilVisible().waitUntilClickable();
        category.click();
    }


    public void selectExcludeStockOut() {
        WebElementFacade stockout = waitForElement(stockOutCheckBoxLocator, 20).waitUntilVisible().waitUntilClickable();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", stockout);
        try {
            stockout.click();
        } catch (Exception e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", stockout);
        }
    }

    public void selectOffer20Percent() {
        WebElementFacade offer = waitForElement(offer20PercentCheckboxLocator, 20).waitUntilVisible().waitUntilClickable();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", offer);
        try {
            offer.click();
        } catch (Exception e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", offer);
        }

    }

    public boolean isProductInPriceRange(int min, int max) {
         if(min == 0){
             return true;
         }
           return isProductMinPriceWithinRange(min);
    }
    
    public boolean isProductMinPriceWithinRange(int min) {
        for (WebElementFacade productCard : productCards) {
            String priceText = productCard.find(By.xpath(".//div[@class = 'product-card-price']")).getText().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(priceText);
            if (price < min) {
                return false;
            }
        }
        return true;
    }



    public boolean isProductInCategory(String category) {
        for (WebElementFacade productCard : productCards) {
            String categoryText = productCard.find(By.xpath(".//div[@class='product-card-category']")).getText();
            if (!categoryText.contains(category)) {
                return false;
            }
        }
        return true;
    }

    public boolean isOutOfStockProductListed() {
        for (WebElementFacade productCard : productCards) {
            List<WebElement> stockoutElements = productCard.findElements(By.xpath(".//span[contains(@class,'stockout-tag')]"));
            if (!stockoutElements.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isProductInOfferCategory(String offer) {
        for (WebElementFacade productCard : productCards) {
            List<WebElement> offerElements = productCard.findElements(By.xpath(".//div[contains(@class,'product-card-discount')]"));
            if (!offerElements.isEmpty()) {
                String offerText = productCard.find(By.xpath(".//div[contains(@class,'product-card-discount')]")).getText();
                offerText = offerText.replaceAll("[^0-9]", "");
                int offerValue = Integer.parseInt(offerText);
                if (offer.equals("20% or More") && offerValue < 20) {
                    return false;
                }
            } else if (offer.equals("20% or More")) {
                return false;
            }
        }
        return true;
    }
}