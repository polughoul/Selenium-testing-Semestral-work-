package org.example.Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class LogInTest {
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

        // Wait for the "Signup / Login" link to be clickable
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']/i[contains(@class, 'fa-lock')]")));

        // Click on the "Signup / Login" link
        loginLink.click();

        // Wait for the "Email Address" and "Password" input fields to be present
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa='login-email']")));
        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa='login-password']")));
        WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-qa='login-button']")));

        // Set values for the input fields
        String email = "asdasd123@gmail.com";
        String password = "123456";

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        // Verify the email and password
        String enteredEmail = emailInput.getAttribute("value");
        String enteredPassword = passwordInput.getAttribute("value");


        assertEquals("The email entered does not match the expected email.", email, enteredEmail);
        assertEquals("The password entered does not match the expected password.", password, enteredPassword);
        loginButton.click();

//        // Wait for the logged-in message to be present
//        WebElement loggedInAsElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(., 'Logged in as')]/b")));
//        String loggedInAsText = loggedInAsElement.getText();
//
//        // Print or use the text as needed
//        System.out.println("Logged in as: " + loggedInAsText);


        WebElement deleteAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/delete_account' and contains(., 'Delete Account')]")));
        deleteAccountLink.click();

        WebElement continueButton2 = driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
        continueButton2.click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
