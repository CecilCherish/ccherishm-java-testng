package id.co.juaracoding.selenium;

import id.co.juaracoding.util.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * BaseSeleniumTest mengatur daur hidup (lifecycle) WebDriver.
 * Menggunakan Selenium Manager bawaan Selenium 4 (tanpa perlu download binary driver manual).
 */
public abstract class BaseSeleniumTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Opsi kestabilan browser pada lingkungan testing
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // Opsi headless jika dijalankan di CI / terminal tanpa monitor
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestConfig.IMPLICIT_WAIT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestConfig.PAGE_LOAD_TIMEOUT_SECONDS));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}

