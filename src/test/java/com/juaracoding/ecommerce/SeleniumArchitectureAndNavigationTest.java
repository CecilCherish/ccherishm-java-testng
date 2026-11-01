package com.juaracoding.ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumArchitectureAndNavigationTest extends BaseTest {

    @SuppressWarnings("null")
    @Test(priority = 1, description = "Verifikasi browser")
    public void testBrowserOpen() {
        String targetUrl = "https://www.google.com/";
        driver.get(targetUrl);

        Assert.assertEquals(driver.getCurrentUrl(), targetUrl);
        Assert.assertEquals(driver.getTitle(), "Google");
        Assert.assertTrue(driver.getCurrentUrl().contains("google"), "URL contains google");

        System.out.println("Open browser");

    }

    // @Test(priority = 2, description = "Search google")
    // public void testSearchGoogle() {
    // String targetUrl = "https://www.google.com/";
    // driver.get(targetUrl);

    // WebElement search = driver.findElement(By.name("q"));

    // search.sendKeys("Juara Coding");
    // search.submit();

    // Assert.assertTrue(driver.getTitle().contains("Juara Coding"), "Title contains
    // Juara Coding");

    // System.out.println("Search google");

    // }

    @Test(priority = 2, description = "Demoqa text box")
    public void testDemoQATextBox() {
        String targetUrl = "https://demoqa.com/text-box";
        driver.get(targetUrl);

        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys("Juara Coding");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("admin@juaracoding.co.id");

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Jakarta");

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys("Jakarta");

        // scroll to element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", permanentAddress);

        WebElement btnSubmit = driver.findElement(By.id("submit"));
        btnSubmit.click();

        WebElement nameOutput = driver.findElement(By.id("name"));

        Assert.assertTrue(nameOutput.getText().contains("Juara Coding"));
        // Assert.assertEquals(fullName.getText(), "Juara Coding");
        // Assert.assertEquals(emailoutput.getText(), "[EMAIL_ADDRESS]");
        // Assert.assertEquals(currentAddressoutput.getText(), "Jakarta");
        // Assert.assertEquals(permanentAddressOutput.getText(), "Jakarta");

        System.out.println("Test DemoQA Text Box");

    }

    // login saucedemo
    // @Test(priority = 3, description = "Saucedemo login")
    // public void testSaucedemoLogin() {
    // String targetUrl = "https://www.saucedemo.com/";
    // driver.get(targetUrl);

    // // WebElement username = driver.findElement(By.id("user-name"));
    // // username.sendKeys("standard_user");

    // // String getTextLogo = driver.findElement(By.className("login_logo"));
    // // System.out.println(getTextLogo);

    // WebElement inputUsername = driver.findElement(By.id("user-name"));
    // inputUsername.sendKeys("standard_user");

    // WebElement password = driver.findElement(By.id("password"));
    // password.sendKeys("secret_sauce");

    // WebElement btnLogin = driver.findElement(By.id("login-button"));
    // btnLogin.click();

    // Assert.assertEquals(driver.getCurrentUrl(),
    // "https://www.saucedemo.com/inventory.html");

    // // ERROR
    // // WebElement errorMssg =
    // driver.findElement(By.xpath("//h3[@role='alert']"));
    // // System.out.println(errorMssg.getText());

    // // Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"));
    // // Assert.assertTrue(errorMssg.getText().contains("Epic sadface: You can only
    // access '/inventory.html' when you are logged in."));

    // // System.out.println(driver.getCurrentUrl());
    // // System.out.println(driver.getTitle());

    // System.out.println("Test Saucedemo Login");
    // }

    @Test(priority = 3, description = "Saucedemo login and add to cart")
    public void testSaucedemoLogin() {
        String targetUrl = "https://www.saucedemo.com/";
        driver.get(targetUrl);

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement btnLogin = driver.findElement(By.id("login-button"));
        btnLogin.click();

        // Verifikasi URL setelah login
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        WebElement btnAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        btnAddToCart.click();

        WebElement btnRemove = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertTrue(btnRemove.isDisplayed(), "Tombol Remove harus terlihat");
        Assert.assertEquals(btnRemove.getText(), "Remove");

        // Ambil string teksnya
        WebElement priceBackpackElem = driver.findElement(By.xpath(
                "//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']"));
        WebElement priceBikeLightElem = driver.findElement(By.xpath(
                "//div[text()='Sauce Labs Bike Light']/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']"));

        // Ambil string teksnya
        String textPriceBackpack = priceBackpackElem.getText();
        String textPriceBikeLight = priceBikeLightElem.getText();

        // Hilangkan karakter '$' dan konversi ke tipe data Double
        double priceBackpack = Double.parseDouble(textPriceBackpack.replace("$", "").trim());
        double priceBikeLight = Double.parseDouble(textPriceBikeLight.replace("$", "").trim());

        // Hitung total penjumlahan
        double totalPrice = priceBackpack + priceBikeLight;

        // Cetak ke console
        System.out.println("Harga Backpack   : $" + priceBackpack);
        System.out.println("Harga Bike Light : $" + priceBikeLight);
        System.out.println("Total Penjumlahan: $" + totalPrice);

        // memastikan hasil perhitungannya benar
        Assert.assertEquals(totalPrice, 39.98, "Total harga harus 39.98");

        System.out.println("Berhasil menambahkan Sauce Labs Backpack ke keranjang!");
    }
    // libaray extend report untuk laporan

}
