package org.example.Tests;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveProduct {
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();



    }
    @Test
    public void searchArticlesAndVerifyDetails() {
        driver.get("https://automationexercise.com/");

        // Wait for the "Consent" button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust timeout as needed
        WebElement consentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='fc-button-label' and text()='Consent']")));

        // Click on the "Consent" button
        consentButton.click();

        WebElement productInfoElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.productinfo.text-center")));

// Move the cursor to the product info element
        Actions actions = new Actions(driver);
        actions.moveToElement(productInfoElement).perform();


        WebElement product1 = driver.findElement(By.cssSelector("a[data-product-id='1']"));
        product1.click();
        WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-center']/a[@href='/view_cart']")));
        viewCartLink.click();

        WebElement deleteLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cart_quantity_delete[data-product-id='1']")));
        deleteLink.click();
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}