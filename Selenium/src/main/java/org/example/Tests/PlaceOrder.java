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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class PlaceOrder {
    static WebDriver driver;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void searchArticlesAndVerifyDetails() throws InterruptedException {
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


        // Wait for the "Name" input field to be present
        WebElement nameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa='signup-name']")));

        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa='signup-email']")));

        WebElement sigh_up = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-qa='signup-button']")));




        // Set value for the "Name" input field
        nameInput.sendKeys("Test name"); // Replace "Your Name" with the desired name
        emailInput.sendKeys("asdasd123@gmail.com");

        sigh_up.click();

        WebElement PasswordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa='password']")));
        PasswordInput.sendKeys("123456");

        // Wait for and select the "Mr." radio button
        WebElement mrRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1")));
        mrRadioButton.click();
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[data-qa='first_name']"));
        firstNameInput.sendKeys("John");

        WebElement lastNameInput = driver.findElement(By.cssSelector("input[data-qa='last_name']"));
        lastNameInput.sendKeys("Doe");

        WebElement addressInput = driver.findElement(By.cssSelector("input[data-qa='address']"));
        addressInput.sendKeys("123 Main St");

        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[data-qa='country']")));
        countrySelect.selectByVisibleText("United States");

        WebElement stateInput = driver.findElement(By.cssSelector("input[data-qa='state']"));
        stateInput.sendKeys("California");

        WebElement cityInput = driver.findElement(By.cssSelector("input[data-qa='city']"));
        cityInput.sendKeys("Los Angeles");

        // Fill the "Zipcode" input field
        WebElement zipcodeInput = driver.findElement(By.cssSelector("input[data-qa='zipcode']"));
        zipcodeInput.sendKeys("90001");

        // Fill the "Mobile Number" input field
        WebElement mobileNumberInput = driver.findElement(By.cssSelector("input[data-qa='mobile_number']"));
        mobileNumberInput.sendKeys("1234567890");

        // Click the "Create Account" button
        WebElement createAccountButton = driver.findElement(By.cssSelector("button[data-qa='create-account']"));
        createAccountButton.click();


        WebElement continueButton = driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
        continueButton.click();

        WebElement loggedInAsElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(., 'Logged in as')]/b")));
        String loggedInAsText = loggedInAsElement.getText();

        // Print or use the text as needed
        System.out.println("Logged in as: " + loggedInAsText);


        WebElement productInfoElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.productinfo.text-center")));

// Move the cursor to the product info element
        Actions actions = new Actions(driver);
        actions.moveToElement(productInfoElement).perform();


        WebElement product1 = driver.findElement(By.cssSelector("a[data-product-id='1']"));
        product1.click();

        WebElement cart = driver.findElement(By.cssSelector("a[href='/view_cart']"));
        cart.click();

        Thread.sleep(400000);

        WebElement checkout = driver.findElement(By.cssSelector("a[class='btn btn-default check_out']"));
        checkout.click();


        WebElement form = driver.findElement(By.cssSelector("textarea[class='form-control']"));
        form.sendKeys("Nice order!!");

        WebElement placeOrder = driver.findElement(By.cssSelector("a[href='/payment']"));
        placeOrder.click();

        WebElement nameOnCardInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-qa='name-on-card']")));
        nameOnCardInput.sendKeys("John Doe");

        // Fill the "Card Number" input field
        WebElement cardNumberInput = driver.findElement(By.cssSelector("input[data-qa='card-number']"));
        cardNumberInput.sendKeys("1234567812345678");

        // Fill the "CVC" input field
        WebElement cvcInput = driver.findElement(By.cssSelector("input[data-qa='cvc']"));
        cvcInput.sendKeys("311");

        // Fill the "Expiry Month" input field
        WebElement expiryMonthInput = driver.findElement(By.cssSelector("input[data-qa='expiry-month']"));
        expiryMonthInput.sendKeys("12");

        // Fill the "Expiry Year" input field
        WebElement expiryYearInput = driver.findElement(By.cssSelector("input[data-qa='expiry-year']"));
        expiryYearInput.sendKeys("2025");

        WebElement PayButton = driver.findElement(By.cssSelector("button[class='form-control btn btn-primary submit-button']"));
        PayButton.click();

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