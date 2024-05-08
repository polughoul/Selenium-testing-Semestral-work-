package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "login-email")
    private WebElement loginInput;

    @FindBy(id = "login-password")
    private WebElement passwordInput;

    @FindBy(id = "email-submit")
    private WebElement submitButton;

    @FindBy(id = "password-submit")
    private WebElement submitButton2;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        // Wait for login input field to be visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginInput));

        // Enter username
        loginInput.sendKeys(username);

        // Click on submit button
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();


        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(submitButton2));
        submitButton2.click();
    }

    // Other methods for interacting with the login page
}
