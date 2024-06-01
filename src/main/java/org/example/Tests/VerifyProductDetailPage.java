package org.example.Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyProductDetailPage {
    @Test
    public void verifyProductDetails() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\andre\\AppData\\Local\\Google\\Chrome\\User Data\\default");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://automationexercise.com");

//      driver.findElement(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button")).click();

        driver.findElement(By.xpath("//a[contains(text(), 'Products')]")).click();

        driver.findElement(By.xpath("(//a[contains(text(), 'View Product')])[1]")).click();

        String productName = driver.findElement(By.xpath("//img[@class='newarrival']/following-sibling::h2")).getText();
        String productCategory = driver.findElement(By.xpath("//p[starts-with(text(), 'Category:')]")).getText();
        String productPrice = driver.findElement(By.xpath("//span/span")).getText();
        String productAvailability = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(., 'Availability:')]"))).getText();
        String productCondition = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(., 'Condition:')]"))).getText();
        String productBrand = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(., 'Brand:')]"))).getText();

        System.out.println("Product Name: " + productName);
        System.out.println("Product Category: " + productCategory);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Availability: " + productAvailability);
        System.out.println("Product Condition: " + productCondition);
        System.out.println("Product Brand: " + productBrand);

        Assert.assertEquals("Product name does not match", productName, driver.findElement(By.xpath("//img[@class='newarrival']/following-sibling::h2")).getText());
        Assert.assertEquals("Product category does not match", productCategory, driver.findElement(By.xpath("//p[starts-with(text(), 'Category:')]")).getText());
        Assert.assertEquals("Product price does not match", productPrice, driver.findElement(By.xpath("//span/span")).getText());
        Assert.assertEquals("Product availability does not match", productAvailability, wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(., 'Availability:')]"))).getText());
        Assert.assertEquals("Product condition does not match", productCondition, wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(., 'Condition:')]"))).getText());
        Assert.assertEquals("Product brand does not match", productBrand, wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(., 'Brand:')]"))).getText());

        driver.quit();
    }
}