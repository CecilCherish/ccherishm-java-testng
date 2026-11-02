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
 * Page Object untuk Halaman Lupa Password Simple Apps.
 */
public class ForgotPasswordPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "input#email, input[type='email'], input[name='email']")
    private WebElement emailInput;

    @FindBy(css = "button[type='submit'], #btn-reset, #btn-forgot, #reset-button, button.btn-primary")
    private WebElement submitButton;

    @FindBy(css = ".alert-success, .success-message, #success-message, .text-success, .toast-success")
    private WebElement successMessage;

    @FindBy(css = ".alert-danger, .error-message, #error-message, .text-danger, [role='alert'], .toast-error")
    private WebElement errorMessage;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        if (email != null && !email.isEmpty()) {
            emailInput.sendKeys(email);
        }
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        try {
            submitButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        }
    }

    public void submitForgotPassword(String email) {
        enterEmail(email);
        clickSubmitButton();
    }

    public boolean isResetProcessTriggered() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(successMessage),
                    ExpectedConditions.urlContains("success"),
                    ExpectedConditions.urlContains("check-email")
            ));
            return true;
        } catch (Exception e) {
            return false;
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

