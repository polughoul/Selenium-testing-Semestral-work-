import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    static WebDriver driver;

    static Login login_test;
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login_test = new Login(driver);
    }

    @Test
    public void searchArticlesAndVerifyDetails() {
        driver.get("https://www.hltv.org/");
        // Navigate to the login page

        // Add a slight delay before clicking the element

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }


}
