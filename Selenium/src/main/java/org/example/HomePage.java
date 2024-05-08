package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        driver.get("https://www.hltv.org/");


        WebElement acceptButton = driver.findElement(By.cssSelector("button[data-cc-action='accept']"));

        // Click on the accept button
        acceptButton.click();

        // Wait for the "Sign in" link to be clickable
        WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust timeout as needed


        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("identity-account-widget")));

        loginButton.click();
    }
}