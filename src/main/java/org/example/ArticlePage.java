package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ArticlePage {
    private WebDriver driver;

    @FindBy(className = "app-card-open__main")
    private List<WebElement> articleElements;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<Article> getFirstFourArticles() {
        List<Article> articles = new ArrayList<>(10);
        for (int i = 0; i < 4; i++) {
            WebElement articleElement = articleElements.get(i);
            String title = articleElement.findElement(By.className("app-card-open__heading")).getText();
            String publicationDate = articleElement.findElement(By.cssSelector("span.c-meta__item[data-test='published']")).getText();

            WebElement linkElement = articleElement.findElement(By.cssSelector("a.app-card-open__link"));

            String href = linkElement.getAttribute("href");
            String cleanedHref1 = href.replace("https://link.springer.com/article/", "");
            String cleanedHref2 = cleanedHref1.replace("https://link.springer.com/book/", "");
            articles.add(new Article(title, publicationDate, cleanedHref2));
        }
        return articles;
    }

    // Other methods for interacting with articles
}