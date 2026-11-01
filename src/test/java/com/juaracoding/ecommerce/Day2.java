package com.juaracoding.ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.juaracoding.ecommerce.drivers.DriverFactory;

public class Day2 {

    public static void main(String[] args) {
        // Inisialisasi driver
        WebDriver driver = DriverFactory.createChromeDriver(false);

        // linktext google gmail
        String targetUrl = "https://www.google.com/";
        driver.get(targetUrl);

        driver.findElement(By.linkText("Gmail")).click();

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        // Menutup browser
        driver.quit();
    }
}
