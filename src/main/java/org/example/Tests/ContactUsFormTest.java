package org.example.Tests;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ContactUsFormTest {
    WebDriver driver;

    @FindBy(css = "a[href='/contact_us']")
    WebElement contactUsButton;

    @FindBy(name = "name")
    WebElement nameInput;

    @FindBy(name = "email")
    WebElement emailInput;

    @FindBy(name = "subject")
    WebElement subjectInput;

    @FindBy(name = "message")
    WebElement messageInput;

    @FindBy(name = "upload_file")
    WebElement fileInput;

    @FindBy(name = "submit")
    WebElement submitButton;

    @FindBy(css = "a[href='/']")
    WebElement homeButton;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\andre\\AppData\\Local\\Google\\Chrome\\User Data\\default");
        driver = new ChromeDriver(options);
        PageFactory.initElements(driver, this);
    }

    @Test
    public void contactUsFormTest() throws Exception {
        driver.get("http://automationexercise.com");
        contactUsButton.click();

        Reader in = new FileReader("src/main/java/data1.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        for (CSVRecord record : records) {
            for (Map.Entry<String, String> entry : record.toMap().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            String name = record.get("name").trim();
            String email = record.get("email").trim();
            String subject = record.get("subject").trim();
            String message = record.get("message").trim();

            nameInput.sendKeys(name);
            Thread.sleep(2000);
            emailInput.sendKeys(email);
            Thread.sleep(2000);
            subjectInput.sendKeys(subject);
            Thread.sleep(2000);
            messageInput.sendKeys(message);
            Thread.sleep(2000);

            String relativePath = "src/main/java/org/example/Tests/test.docx";
            String absolutePath = Paths.get(relativePath).toAbsolutePath().toString();
            fileInput.sendKeys(absolutePath);

            String enteredName = nameInput.getAttribute("value");
            String enteredEmail = emailInput.getAttribute("value");
            String enteredSubject = subjectInput.getAttribute("value");
            String enteredMessage = messageInput.getAttribute("value");

            assertEquals(name, enteredName);
            assertEquals(email, enteredEmail);
            assertEquals(subject, enteredSubject);
            assertEquals(message, enteredMessage);

            Thread.sleep(2000);

            submitButton.click();
            Thread.sleep(2000);

            Alert alert = driver.switchTo().alert();
            alert.accept();

            assertEquals("Success! Your details have been submitted successfully.", driver.findElement(By.cssSelector(".status.alert.alert-success")).getText());

            homeButton.click();

            Thread.sleep(2000);
            assertEquals("https://automationexercise.com/", driver.getCurrentUrl());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
