import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class Login {

    WebDriver driver;

    @FindBy(linkText = "Sign in")
    private WebElement loginLink;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "login")
    private WebElement loginButton;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void navigateToLogin() {
        loginLink.click();
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void login(String password, String username) {
        navigateToLogin();
        enterUsername(username);
        enterPassword(password);
        loginButton.click();
    }
}
