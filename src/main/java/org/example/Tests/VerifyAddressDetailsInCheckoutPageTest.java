package org.example.Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VerifyAddressDetailsInCheckoutPageTest {
    WebDriver driver;

    @FindBy(css = ".fc-button.fc-cta-consent.fc-primary-button")
    WebElement consentButton;

    @FindBy(css = "a[href='/login']")
    WebElement signupLoginButton;

    @FindBy(name = "name")
    WebElement nameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement signupEmailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    WebElement signupButton;

    @FindBy(id = "id_gender1")
    WebElement genderMr;

    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(name = "days")
    WebElement daysDropdown;

    @FindBy(name = "months")
    WebElement monthsDropdown;

    @FindBy(name = "years")
    WebElement yearsDropdown;

    @FindBy(name = "first_name")
    WebElement firstNameInput;

    @FindBy(name = "last_name")
    WebElement lastNameInput;

    @FindBy(name = "address1")
    WebElement addressInput;

    @FindBy(name = "country")
    WebElement countryDropdown;

    @FindBy(name = "state")
    WebElement stateInput;

    @FindBy(name = "city")
    WebElement cityInput;

    @FindBy(name = "zipcode")
    WebElement zipcodeInput;

    @FindBy(name = "mobile_number")
    WebElement mobileNumberInput;

    @FindBy(css = "button[data-qa='create-account']")
    WebElement createAccountButton;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueButton;

    @FindBy(css = "a[href='/logout']")
    WebElement loggedInAsUsername;

    @FindBy(css = "a[href='/products']")
    WebElement productsButton;

    @FindBy(css = "a[data-product-id='1']")
    WebElement addProductButton;

    @FindBy(css = "button.close-modal")
    WebElement closeModalButton;

    @FindBy(css = "a[href='/view_cart']")
    WebElement cartButton;

    @FindBy(css = "div.breadcrumbs")
    WebElement cartPage;

    @FindBy(css = "a.btn.btn-default.check_out")
    WebElement proceedToCheckoutButton;

    @FindBy(css = "#address_delivery .address_firstname.address_lastname")
    WebElement deliveryName;

    @FindBy(css = "#address_delivery .address_address1.address_address2:nth-child(4)")
    WebElement deliveryAddressLine1;

    @FindBy(css = "#address_delivery .address_address1.address_address2:nth-child(3)")
    WebElement deliveryAddressLine2;

    @FindBy(css = "#address_delivery .address_city.address_state_name.address_postcode")
    WebElement deliveryCityStateZip;

    @FindBy(css = "#address_delivery .address_country_name")
    WebElement deliveryCountry;

    @FindBy(css = "#address_delivery .address_phone")
    WebElement deliveryPhone;

    @FindBy(css = "#address_invoice .address_firstname.address_lastname")
    WebElement billingName;

    @FindBy(css = "#address_invoice .address_address1.address_address2:nth-child(4)")
    WebElement billingAddressLine1;

    @FindBy(css = "#address_invoice .address_address1.address_address2:nth-child(3)")
    WebElement billingAddressLine2;

    @FindBy(css = "#address_invoice .address_city.address_state_name.address_postcode")
    WebElement billingCityStateZip;

    @FindBy(css = "#address_invoice .address_country_name")
    WebElement billingCountry;

    @FindBy(css = "#address_invoice .address_phone")
    WebElement billingPhone;

    @FindBy(css = "a[href='/delete_account']")
    WebElement deleteAccountButton;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueAfterDeleteButton;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @Test
    public void verifyAddressDetailsInCheckoutPage() throws InterruptedException {
        driver.get("https://www.automationexercise.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        consentButton.click();


        assertTrue(driver.findElement(By.cssSelector("div.header-middle")).isDisplayed());


        String name = "Test User";
        String email = "plokiomr5688600@example.com";
        String password = "password";
        String firstName = "Test";
        String lastName = "User";
        String address = "123 Test Street";
        String country = "United States";
        String state = "Test State";
        String city = "Test City";
        String zipcode = "12345";
        String mobileNumber = "1234567890";

        signupLoginButton.click();

        nameInput.sendKeys(name);
        signupEmailInput.sendKeys(email);
        signupButton.click();
        genderMr.click();
        passwordInput.sendKeys(password);
        daysDropdown.sendKeys("10");
        monthsDropdown.sendKeys("June");
        yearsDropdown.sendKeys("1990");
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        addressInput.sendKeys(address);
        countryDropdown.sendKeys(country);
        stateInput.sendKeys(state);
        cityInput.sendKeys(city);
        zipcodeInput.sendKeys(zipcode);
        mobileNumberInput.sendKeys(mobileNumber);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(2000);
        createAccountButton.click();

        Thread.sleep(2000);

        assertTrue(driver.findElement(By.cssSelector(".title")).getText().contains("ACCOUNT CREATED!"));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(loggedInAsUsername));

        assertTrue(loggedInAsUsername.isDisplayed());


        productsButton.click();
        js.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(2000);
        addProductButton.click();
        Thread.sleep(2000);
        closeModalButton.click();


        cartButton.click();
        Thread.sleep(2000);


        assertTrue(cartPage.getText().contains("Shopping Cart"));
        Thread.sleep(2000);


        proceedToCheckoutButton.click();
        Thread.sleep(2000);

        assertEquals("Mr. " + firstName + " " + lastName, deliveryName.getText());
        assertEquals(address, deliveryAddressLine1.getText());
        assertEquals("", deliveryAddressLine2.getText());
        assertEquals(city + " " + state + " " + zipcode, deliveryCityStateZip.getText());
        assertEquals(country, deliveryCountry.getText());
        assertEquals(mobileNumber, deliveryPhone.getText());

        assertEquals("Mr. " + firstName + " " + lastName, billingName.getText());
        assertEquals(address, billingAddressLine1.getText());
        assertEquals("", billingAddressLine2.getText());
        assertEquals(city + " " + state + " " + zipcode, billingCityStateZip.getText());
        assertEquals(country, billingCountry.getText());
        assertEquals(mobileNumber, billingPhone.getText());

        Thread.sleep(2000);

        deleteAccountButton.click();

        assertTrue(driver.findElement(By.cssSelector(".title")).getText().contains("ACCOUNT DELETED!"));
        Thread.sleep(2000);
        continueAfterDeleteButton.click();
        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
