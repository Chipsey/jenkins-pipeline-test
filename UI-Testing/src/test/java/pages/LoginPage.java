package pages;

import config.Constants;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    final String loginButtonSelector = "login-link";

    public void login() {
        WebElementFacade loginButton = find(By.className(loginButtonSelector));
        loginButton.waitUntilVisible().click();
        WebElementFacade emailInputBar = find(By.id("email"));
        emailInputBar.waitUntilVisible().waitUntilClickable().type(Constants.USERNAME);
        WebElementFacade passwordInputBar = find(By.id("password"));
        passwordInputBar.waitUntilVisible().waitUntilClickable().type(Constants.PASSWORD);
        WebElementFacade loginSubmitButton = find(By.id("login-submit"));
        loginSubmitButton.waitUntilVisible().click();
    }
}
