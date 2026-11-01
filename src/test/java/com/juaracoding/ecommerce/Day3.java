package com.juaracoding.ecommerce;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day3 extends BaseTest {

    // demoqa select
    @Test
    public void testSelectMenu() {
        String targetUrl = "https://demoqa.com/select-menu";
        driver.get(targetUrl);

        Select select = new Select(driver.findElement(By.xpath("//select[@id='oldSelectMenu']")));
        select.selectByVisibleText("Yellow");
    }

    // saucedemo sorting harga termahal ke termurah
    @Test(description = "Saucedemo sorting harga termahal ke termurah")
    public void testSortHighToLow() {
        String targetUrl = "https://www.saucedemo.com/";
        driver.get(targetUrl);

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        WebElement btnLogin = driver.findElement(By.id("login-button"));
        btnLogin.click();

        Select select = new Select(driver.findElement(By.xpath("//select[@aria-label='Sort products']")));
        select.selectByVisibleText("Price (high to low)");
        // Assert
        WebElement getTextPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
        Assert.assertEquals(getTextPrice.getText(), "$49.99");

        // sort nama berdasarkan huruf Z-A
        Select selectProduct = new Select(driver.findElement(By.xpath("//select[@aria-label='Sort products']")));
        selectProduct.selectByVisibleText("Name (Z to A)");
        // Assert
        WebElement getTextProduct = driver.findElement(By.xpath("//div[@class='inventory_item_name ']"));
        Assert.assertEquals(getTextProduct.getText(), "Test.allTheThings() T-Shirt (Red)");

        // fitur scenario checkout

        // 1. Klik tombol "Add to cart" pada produk teratas (hasil sorting Z-A)
        WebElement btnAddToCart = driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]"));
        btnAddToCart.click();

        // 2. Klik icon/link shopping cart untuk masuk ke halaman keranjang
        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']"));
        shoppingCartLink.click();

        // 3. Periksa/Assert apakah barang yang dibeli sudah benar di halaman cart
        WebElement cartItemName = driver.findElement(By.xpath("//div[@data-test='inventory-item-name']"));
        Assert.assertEquals(cartItemName.getText(), "Test.allTheThings() T-Shirt (Red)");

        // 4. Klik button Checkout
        WebElement btnCheckout = driver.findElement(By.id("checkout"));
        btnCheckout.click();

        // 5. Mengisi form data diri pada halaman Checkout Information
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Che");

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Mar");

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("1234");

        // Opsional: Klik tombol Continue untuk lanjut ke tahap berikutnya
        WebElement btnContinue = driver.findElement(By.id("continue"));
        btnContinue.click();

        WebElement btnFinish = driver.findElement(By.id("finish"));
        btnFinish.click();

        WebElement btnBackToProducts = driver.findElement(By.id("back-to-products"));
        btnBackToProducts.click();

        // fitur logout

    }

    // demoqa alert
    @Test
    public void testQademoAlert() {
        String targetUrl = "https://demoqa.com/alerts";
        driver.get(targetUrl);

        WebElement btnAlert = driver.findElement(By.xpath("//button[@id='alertButton']"));
        btnAlert.click();

        delay(5);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alertMassage = wait.until(ExpectedConditions.alertIsPresent());
        alertMassage.accept();

    }

    public void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
