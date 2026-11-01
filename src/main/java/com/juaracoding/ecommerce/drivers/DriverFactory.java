package com.juaracoding.ecommerce.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createChromeDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless");
        }

        // disable peringatan kebocoran password
        options.addArguments("--password-manager-restricted");
        options.addArguments("--password-protection");
        options.addArguments("--password-generation");

        // options.addArguments("--disable-notifications");
        // options.addArguments("--no-sandbox");
        // options.addArguments("--disable-dev-shm-usage");

        // options.addArguments(" -- incognito");
        // options.addArguments(" -- start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);

        // return new ChromeDriver(options);
    }


    // public static WebDriver createFirefoxDriver(boolean headless) {
    //     FirefoxOptions options = new FirefoxOptions();
    //     if (headless) {
    //         options.addArguments("--headless");
    //     }
    //     options.addArguments("--disable-notifications");
    //     options.addArguments("--no-sandbox");
    //     options.addArguments("--disable-dev-shm-usage");
    //     return new FirefoxDriver(options);
    // }
}
