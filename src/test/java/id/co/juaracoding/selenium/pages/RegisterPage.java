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
 * Page Object untuk Halaman Registrasi Simple Apps.
 */
public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "input#name, input#fullName, input[name='name'], input[name='fullName']")
    private WebElement nameInput;

    @FindBy(css = "input#email, input[type='email'], input[name='email']")
    private WebElement emailInput;

    @FindBy(css = "input#password, input[type='password'], input[name='password']")
    private WebElement passwordInput;

    // Nomor KTP / NPWP (sesuai spesifikasi soal harus 16 digit)
    @FindBy(css = "input#ktp, input#npwp, input#identityNumber, input#no_ktp, input[name='ktp'], input[name='npwp'], input[name='identityNumber']")
    private WebElement identityNumberInput;

    @FindBy(css = "button[type='submit'], #btn-register, #register-button, button.btn-primary")
    private WebElement registerButton;

    @FindBy(css = ".alert-success, .success-message, #success-message, .text-success, .toast-success")
    private WebElement successMessage;

    @FindBy(css = ".alert-danger, .error-message, #error-message, .text-danger, [role='alert'], .invalid-feedback, .toast-error")
    private WebElement errorMessage;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        if (name != null && !name.isEmpty()) {
            nameInput.sendKeys(name);
        }
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        if (email != null && !email.isEmpty()) {
            emailInput.sendKeys(email);
        }
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        if (password != null && !password.isEmpty()) {
            passwordInput.sendKeys(password);
        }
    }

    public void enterIdentityNumber(String identityNumber) {
        wait.until(ExpectedConditions.visibilityOf(identityNumberInput));
        identityNumberInput.clear();
        if (identityNumber != null && !identityNumber.isEmpty()) {
            identityNumberInput.sendKeys(identityNumber);
        }
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        try {
            registerButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);
        }
    }

    public void register(String name, String email, String password, String identityNumber) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        enterIdentityNumber(identityNumber);
        clickRegisterButton();
    }

    public boolean isRegistrationSuccessful() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(successMessage),
                    ExpectedConditions.urlContains("login"),
                    ExpectedConditions.urlContains("success")
            ));
            return true;
        } catch (Exception e) {
            String url = driver.getCurrentUrl().toLowerCase();
            return url.contains("login") || url.contains("success");
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

