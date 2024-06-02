package org.example.Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class VerifySubscriptionTest {
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\andre\\AppData\\Local\\Google\\Chrome\\User Data\\default");
        driver = new ChromeDriver(options);
    }

    @Test
    public void verifySubscription() {
        driver.get("http://automationexercise.com");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("susbscribe_email")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("subscribe")));

        emailInput.sendKeys("andreystasiuk1488@gmail.com");
        submitButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='You have been successfully subscribed!']")));
        assertEquals("You have been successfully subscribed!", successMessage.getText());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}