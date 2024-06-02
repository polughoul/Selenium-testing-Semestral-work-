package org.example.Tests;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;


public class VerifySubscriptionTest {
    static WebDriver driver;

    @FindBy(css = ".fc-button.fc-cta-consent.fc-primary-button")
    WebElement consentButton;

    @FindBy(id = "susbscribe_email")
    WebElement emailInput;

    @FindBy(id = "subscribe")
    WebElement subscribeButton;

    @FindBy(xpath = "//*[text()='You have been successfully subscribed!']")
    WebElement successMessage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @Test
    public void verifySubscription() {

        driver.get("http://automationexercise.com");
        consentButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        wait.until(ExpectedConditions.elementToBeClickable(subscribeButton));

        emailInput.sendKeys("andreystasiuk1488@gmail.com");
        subscribeButton.click();

        wait.until(ExpectedConditions.visibilityOf(successMessage));
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