package id.co.juaracoding.selenium.pages;

import id.co.juaracoding.util.TestConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object untuk Halaman Login Simple Apps.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "input#username, input#email, input[type='email'], input[name='username'], input[name='email']")
    private WebElement usernameInput;

    @FindBy(css = "input#password, input[type='password'], input[name='password']")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit'], #btn-login, #login-button, button.btn-primary")
    private WebElement loginButton;

    @FindBy(css = ".alert-danger, .error-message, #error-message, .text-danger, [role='alert'], .toast-error")
    private WebElement errorMessage;

    @FindBy(css = ".dashboard, #dashboard, header, nav, .navbar, .welcome-message, #btn-logout")
    private WebElement dashboardIndicator;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        if (username != null && !username.isEmpty()) {
            usernameInput.sendKeys(username);
        }
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        if (password != null && !password.isEmpty()) {
            passwordInput.sendKeys(password);
        }
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        try {
            loginButton.click();
        } catch (Exception e) {
            // Fallback klik via Javascript jika ada overlay Vue
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        }
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("dashboard"),
                    ExpectedConditions.visibilityOf(dashboardIndicator)
            ));
            return true;
        } catch (Exception e) {
            return driver.getCurrentUrl().toLowerCase().contains("dashboard");
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

