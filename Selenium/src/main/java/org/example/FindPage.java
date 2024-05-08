package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindPage {
    private WebDriver driver;



    @FindBy(id = "all-words")
    private WebElement allInput;

    @FindBy(id = "least-words")
    private WebElement atleastInput;



    @FindBy(id = "submit-advanced-search")
    private WebElement submitButton2;


    public FindPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void navigate(String all, String atleast) {
        driver.get("https://link.springer.com/advanced-search");

        WebElement acceptButton = driver.findElement(By.cssSelector("button[data-cc-action='accept']"));

        // Click on the accept button
        acceptButton.click();


        allInput.sendKeys(all);

        atleastInput.sendKeys(atleast);
        submitButton2.click();

    }
}