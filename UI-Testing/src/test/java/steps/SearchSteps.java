package steps;

import config.Constants;
import net.serenitybdd.annotations.Steps;
import pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.LoginPage;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class SearchSteps {

    @Steps
    private HomePage homePage;
    @Steps
    private LoginPage loginPage;


    public void login() {
        loginPage.login();
    }

    final String baseUrl = Constants.BASE_URL+Constants.HOME_PAGE_ENDPOINT;

    @Given("I open the HomePage")
    public void openHomePage() {
        homePage.openWebPage(baseUrl);
    }

    @When("I search for {string}")
    public void searchFor(String product) {
        homePage.searchForProduct(product);
    }

    @Then("I should see search results related to {string}")
    public void seeResults(String product) throws InterruptedException {
        assertTrue(homePage.verifyProductInSearchResults(product));
    }

    @Then("I should see a message indicating no search results were found")
    public void verifyNoResults() throws InterruptedException {
        assertTrue(homePage.verifyNoSearchResultsMessage());
    }

}
