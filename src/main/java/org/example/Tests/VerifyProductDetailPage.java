package org.example.Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyProductDetailPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = ".fc-button.fc-cta-consent.fc-primary-button")
    WebElement consentButton;

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
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);

        driver.get("http://automationexercise.com");

        consentButton.click();
        productsLink.click();

        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        Thread.sleep(2000);

        viewProductLink.click();

        Thread.sleep(3000);

        String productName = productNameElement.getText();
        String productCategory = productCategoryElement.getText();
        String productPrice = productPriceElement.getText();
        String productAvailability = wait.until(ExpectedConditions.visibilityOf(productAvailabilityElement)).getText();
        String productCondition = wait.until(ExpectedConditions.visibilityOf(productConditionElement)).getText();
        String productBrand = wait.until(ExpectedConditions.visibilityOf(productBrandElement)).getText();

        System.out.println("Product Name: " + productName);
        System.out.println("Product Category: " + productCategory);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Availability: " + productAvailability);
        System.out.println("Product Condition: " + productCondition);
        System.out.println("Product Brand: " + productBrand);

        Assert.assertEquals("Product name does not match", productName, productNameElement.getText());
        Assert.assertEquals("Product category does not match", productCategory, productCategoryElement.getText());
        Assert.assertEquals("Product price does not match", productPrice, productPriceElement.getText());
        Assert.assertEquals("Product availability does not match", productAvailability, wait.until(ExpectedConditions.visibilityOf(productAvailabilityElement)).getText());
        Assert.assertEquals("Product condition does not match", productCondition, wait.until(ExpectedConditions.visibilityOf(productConditionElement)).getText());
        Assert.assertEquals("Product brand does not match", productBrand, wait.until(ExpectedConditions.visibilityOf(productBrandElement)).getText());

        driver.quit();
    }
}