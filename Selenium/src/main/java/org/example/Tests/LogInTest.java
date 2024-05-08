package org.example.Tests;


import java.util.ArrayList;
import java.util.List;

import org.example.ArticlePage;
import org.example.HomePage;
import org.example.LoginPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class LogInTest {
    static WebDriver driver;
    static HomePage homePage;
    static LoginPage loginPage;
    static ArticlePage articlePage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        articlePage = new ArticlePage(driver);
    }
    @Test
    public void searchArticlesAndVerifyDetails() {
        // Navigate to the login page
        homePage.navigateToLoginPage();


        loginPage.login("valera.nabok.r@gmail.com", "Valera123789");
        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Add a slight delay before clicking the element
        try {
            Thread.sleep(5000); // 5 second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}