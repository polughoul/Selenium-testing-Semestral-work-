package org.example.Tests;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.FileReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

public class SearchProductTest {
    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void searchProductTest() throws Exception {
        driver.get("http://automationexercise.com");
        driver.findElement(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button")).click();

        WebElement productsButton = driver.findElement(By.cssSelector("a[href='/products']"));
        productsButton.click();

        Reader in = new FileReader("src/main/java/data2.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        CSVRecord productRecord = records.iterator().next();
        String productName = productRecord.get(0);

        WebElement searchInput = driver.findElement(By.id("search_product"));
        searchInput.sendKeys(productName);

        WebElement searchButton = driver.findElement(By.id("submit_search"));
        searchButton.click();

        Thread.sleep(2000);


        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        String expectedUrl = "https://automationexercise.com/products?search=" + productName;
        assertEquals(expectedUrl, currentUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}