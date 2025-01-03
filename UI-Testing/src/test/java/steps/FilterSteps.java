package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;
import pages.FilterPage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class FilterSteps {

    @Steps
    private FilterPage filterPage;

    final String baseUrl = "http://singersl.com/brands/dell";

     @Given("User is on the product page")
    public void userIsOnTheProductPage() {
        filterPage.openWebPage(baseUrl);
    }

     @When("User filters with minimum price {string}")
     public void userFiltersWithMinPrice(String minPrice) {
         filterPage.enterMinPrice(minPrice);
     }


    @Then("Products should be filtered to have prices greater than or equal to {string}")
    public void productsShouldBeFilteredToHavePricesGreaterThanOrEqualTo(String minPrice) {
        assertTrue(filterPage.isProductInPriceRange(Integer.parseInt(minPrice),Integer.MAX_VALUE));
    }
   
    @When("User filters by category {string}")
    public void userFiltersByCategory(String category) {
        filterPage.selectCategoryComputers();
    }

    @Then("Products should be filtered by category {string}")
    public void productsShouldBeFilteredByCategory(String category) {
        assertTrue(filterPage.isProductInCategory(category));
    }

    @When("User filters by {string}")
    public void userFiltersByAvailability(String option) {
        filterPage.selectExcludeStockOut();
    }

    @Then("Out of stock product should not be listed")
    public void outOfStockProductShouldNotBeListed() {
        assertFalse(filterPage.isOutOfStockProductListed());
    }

    @When("User filters by offer {string}")
    public void userFiltersByOffer(String offerPercentage) {
        filterPage.selectOffer20Percent();
    }

    @Then("Products that are not in the offer category should not be listed")
    public void productsThatAreNotInTheOfferCategoryShouldNotBeListed() {
        assertTrue(filterPage.isProductInOfferCategory("20% or More"));
    }

     @When("User applies category {string}, minimum price {string}, and offer {string}")
     public void userAppliesMultipleFilters(String category, String minPrice, String offer) {
         filterPage.selectCategoryComputers();
         filterPage.enterMinPrice(minPrice);
         filterPage.selectOffer20Percent();
     }


    @Then("Products should be filtered by all specified criteria")
    public void productsShouldBeFilteredByAllCriteria() {
      
    }

}