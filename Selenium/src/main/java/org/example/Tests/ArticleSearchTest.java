package org.example.Tests;


import java.util.ArrayList;
import java.util.List;

import org.example.Article;
import org.example.ArticlePage;
import org.example.FindPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ArticleSearchTest {
    static WebDriver driver;


    static FindPage findPage;

    static ArticlePage articlePage;
    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        findPage = new FindPage(driver);
        articlePage = new ArticlePage(driver);
        findPage.navigate("Page Object Model", "Sellenium Testing");
    }

    @ParameterizedTest(name = " {0}  - Publication Date: {1},  DOI:  {2} ")
    @CsvSource({
            "A detector for page-level handwritten music object recognition based on deep learning, 20 January 2023, 10.1007/s00521-023-08216-6",
            "Java Testing with Selenium A Comprehensive Syntax Guide for Automation, 2024,9798868802904"
    })
    public void testArticle(String title, String publicationDate, String doi) {
        // Get the first four articles

        List<Article> savedArticles = articlePage.getFirstFourArticles();

        // Flag to check if the article is found
        boolean articleFound = false;

        // Search for the article by title and verify attributes
        for (Article article : savedArticles) {
            if (article.getTitle().equals(title)) {
                articleFound = true;

                assertEquals("Title does not match", title, article.getTitle() );
                assertEquals("DOI does not match", doi,article.getDoi() );
                assertEquals("Publication date does not match", publicationDate, article.getPublicationDate() );
                break; // Exit loop once the article is found and verified
            }
        }

        // If the article is not found, fail the test
        if (!articleFound) {
            throw new AssertionError("Article with title \"" + title + "\" not found");
        }
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}