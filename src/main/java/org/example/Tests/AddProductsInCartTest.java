package org.example.Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class AddProductsInCartTest {
    static WebDriver driver;

    @FindBy(css = ".fc-button.fc-cta-consent.fc-primary-button")
    WebElement consentButton;

    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    WebElement productsLink;

    @FindBy(xpath = "//a[@data-product-id='1']")
    WebElement firstProduct;

    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@data-product-id='2']")
    WebElement secondProduct;

    @FindBy(xpath = "//div[@class='modal-body']//a[@href='/view_cart']")
    WebElement cartButton;

    @FindBy(xpath = "//tr[@id='product-1']//td[@class='cart_price']/p")
    WebElement firstProductPrice;

    @FindBy(xpath = "//tr[@id='product-1']//td[@class='cart_quantity']/button")
    WebElement firstProductQuantity;

    @FindBy(xpath = "//tr[@id='product-1']//td[@class='cart_total']/p")
    WebElement firstProductTotal;

    @FindBy(xpath = "//tr[@id='product-2']//td[@class='cart_price']/p")
    WebElement secondProductPrice;

    @FindBy(xpath = "//tr[@id='product-2']//td[@class='cart_quantity']/button")
    WebElement secondProductQuantity;

    @FindBy(xpath = "//tr[@id='product-2']//td[@class='cart_total']/p")
    WebElement secondProductTotal;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @Test
    public void addProductsInCart() throws InterruptedException {
        driver.get("http://automationexercise.com");

        consentButton.click();

        productsLink.click();

        Actions actions = new Actions(driver);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        actions.moveToElement(firstProduct).perform();
        Thread.sleep(2000);
        firstProduct.click();

        Thread.sleep(2000);
        continueShoppingButton.click();

        Thread.sleep(2000);

        actions.moveToElement(secondProduct).perform();
        Thread.sleep(3000);
        secondProduct.click();

        Thread.sleep(3000);
        cartButton.click();

        Thread.sleep(3000);



        assertEquals("Rs. 500", firstProductPrice.getText());
        assertEquals("1", firstProductQuantity.getText());
        assertEquals("Rs. 500", firstProductTotal.getText());

        assertEquals("Rs. 400", secondProductPrice.getText());
        assertEquals("1", secondProductQuantity.getText());
        assertEquals("Rs. 400", secondProductTotal.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}