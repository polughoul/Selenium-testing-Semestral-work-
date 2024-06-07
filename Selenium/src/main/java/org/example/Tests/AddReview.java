package org.example.Tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddReview {
    static WebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/review.csv", numLinesToSkip = 1)
    public void searchArticlesAndVerifyDetails(String reviewText) throws InterruptedException {
        driver.get("https://automationexercise.com/");

        WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust timeout as needed

        try {
            WebElement consentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='fc-button-label' and text()='Consent']")));
            consentButton.click();
        } catch (Exception e) {
            System.out.println("Consent button not found or not clickable: " + e.getMessage());
        }

        WebElement viewProductLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/product_details/1' and contains(., 'View Product')]")));
        viewProductLink.click();

        WebElement nameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        nameInput.sendKeys("John Doe");

        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("john.doe@example.com");

        WebElement reviewTextarea = driver.findElement(By.id("review"));
        reviewTextarea.sendKeys(reviewText);
        String enteredReviewText = reviewTextarea.getAttribute("value");
        System.out.println("Entered Review Text: " + enteredReviewText); // Debugging info

        // Verify that the entered review text matches the expected review text
        assertEquals(reviewText, enteredReviewText, "The review text entered in the field does not match the expected text.");

        Thread.sleep(2000);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-review")));
        submitButton.click();

        // Retrieve the entered review text from the textarea for verification

    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
