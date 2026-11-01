package com.juaracoding.ecommerce;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.juaracoding.ecommerce.drivers.DriverFactory;

public class BaseTest {

    WebDriver driver;

    // @SuppressWarnings("null")
    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createChromeDriver(false);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30000));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
