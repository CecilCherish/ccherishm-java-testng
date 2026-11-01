package com.juaracoding.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day4 extends BaseTest {

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

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));

        // Get all inventory item text and convert to list
        List<WebElement> inventoryItems = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
        System.out.println("Jumlah produk dihalaman : " + inventoryItems.size());
        List<String> inventoryItemNames = new ArrayList<>();
        for (WebElement inventoryItem : inventoryItems) {
            inventoryItemNames.add(inventoryItem.getText());

            System.out.println(inventoryItemNames);
        }

        List<WebElement> btnAddToCart = driver.findElements(By.xpath("//button[contains(@class,'btn_small btn_inventory')]"));
        for (WebElement btn : btnAddToCart){
            btn.click();
        }

        System.out.println(inventoryItemNames);

        WebElement getTextBadgeShopingCart = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        int shopingBadge = Integer.parseInt(getTextBadgeShopingCart.getText());
        Assert.assertEquals(shopingBadge, 6);
    
        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']"));
        shoppingCartLink.click();
        
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

        // dari gettext dibuah ke doubleparse sehingga bisa dilakukan kalkulasi
        List<WebElement> itemPrice = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        double totalPrice = 0;
        for (WebElement price : itemPrice) {
            totalPrice += Double.parseDouble(price.getText().replace("$", ""));
        }

        List<WebElement> itemtax = driver.findElements(By.xpath("//div[@class='summary_tax_label']"));
        double itemTax = 0;
        for (WebElement tax : itemtax) {
            itemTax += Double.parseDouble(tax.getText().replace("$", ""));
        }

        WebElement subtotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        double subtotalValue = Double.parseDouble(subtotal.getText().replace("$", ""));

        // memastikan total price + itemtax = subtotalValue
        Assert.assertEquals(totalPrice + itemTax, subtotalValue);
    }


}
