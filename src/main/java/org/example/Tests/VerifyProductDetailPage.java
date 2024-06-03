package org.example.Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyProductDetailPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    WebElement productsLink;

    @FindBy(xpath = "(//a[contains(text(), 'View Product')])[1]")
    WebElement viewProductLink;

    @FindBy(xpath = "//img[@class='newarrival']/following-sibling::h2")
    WebElement productNameElement;

    @FindBy(xpath = "//p[starts-with(text(), 'Category:')]")
    WebElement productCategoryElement;

    @FindBy(xpath = "//span/span")
    WebElement productPriceElement;

    @FindBy(xpath = "//p[contains(., 'Availability:')]")
    WebElement productAvailabilityElement;

    @FindBy(xpath = "//p[contains(., 'Condition:')]")
    WebElement productConditionElement;

    @FindBy(xpath = "//p[contains(., 'Brand:')]")
    WebElement productBrandElement;

    @Test
    public void verifyProductDetails() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\andre\\AppData\\Local\\Google\\Chrome\\User Data\\default");
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);

        driver.get("http://automationexercise.com");

        productsLink.click();

        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        Thread.sleep(2000);

        viewProductLink.click();

        Thread.sleep(3000);

        String expectedProductName = "Blue Top";
        String expectedProductCategory = "Category: Women > Tops";
        String expectedProductPrice = "Rs. 500";
        String expectedProductAvailability = "Availability: In Stock";
        String expectedProductCondition = "Condition: New";
        String expectedProductBrand = "Brand: Polo";

        String actualProductName = wait.until(ExpectedConditions.visibilityOf(productNameElement)).getText();
        String actualProductCategory = wait.until(ExpectedConditions.visibilityOf(productCategoryElement)).getText();
        String actualProductPrice = wait.until(ExpectedConditions.visibilityOf(productPriceElement)).getText();
        String actualProductAvailability = wait.until(ExpectedConditions.visibilityOf(productAvailabilityElement)).getText();
        String actualProductCondition = wait.until(ExpectedConditions.visibilityOf(productConditionElement)).getText();
        String actualProductBrand = wait.until(ExpectedConditions.visibilityOf(productBrandElement)).getText();

        Assert.assertEquals("Product name does not match", expectedProductName, actualProductName);
        Assert.assertEquals("Product category does not match", expectedProductCategory, actualProductCategory);
        Assert.assertEquals("Product price does not match", expectedProductPrice, actualProductPrice);
        Assert.assertEquals("Product availability does not match", expectedProductAvailability, actualProductAvailability);
        Assert.assertEquals("Product condition does not match", expectedProductCondition, actualProductCondition);
        Assert.assertEquals("Product brand does not match", expectedProductBrand, actualProductBrand);

        driver.quit();
    }
}