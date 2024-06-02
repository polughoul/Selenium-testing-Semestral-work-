package org.example.Tests;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.nio.file.Paths;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ContactUsFormTest {
    WebDriver driver;

    @FindBy(css = ".fc-button.fc-cta-consent.fc-primary-button")
    WebElement consentButton;

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

//        consentButton.click();

        contactUsButton.click();

        Reader in = new FileReader("src/main/java/data1.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        Iterator<CSVRecord> recordIterator = records.iterator();

        CSVRecord messageRecord = null;
        CSVRecord nameRecord = null;
        CSVRecord emailRecord = null;
        CSVRecord subjectRecord = null;

        if (recordIterator.hasNext()) {
            messageRecord = recordIterator.next();
        }

        if (recordIterator.hasNext()) {
            nameRecord = recordIterator.next();
        }

        if (recordIterator.hasNext()) {
            emailRecord = recordIterator.next();
        }

        if (recordIterator.hasNext()) {
            subjectRecord = recordIterator.next();
        }

        String message = messageRecord != null ? messageRecord.get(0) : "";
        String name = nameRecord != null ? nameRecord.get(0) : "";
        String email = emailRecord != null ? emailRecord.get(0) : "";
        String subject = subjectRecord != null ? subjectRecord.get(0) : "";

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
        Thread.sleep(2000);
        submitButton.click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        alert.accept();
        String enteredName = nameInput.getAttribute("value");
        String enteredEmail = emailInput.getAttribute("value");
        String enteredSubject = subjectInput.getAttribute("value");
        String enteredMessage = messageInput.getAttribute("value");

        assertEquals(name, enteredName);
        assertEquals(email, enteredEmail);
        assertEquals(subject, enteredSubject);
        assertEquals(message, enteredMessage);

        homeButton.click();
        Thread.sleep(2000);
        assertEquals("https://automationexercise.com/", driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}