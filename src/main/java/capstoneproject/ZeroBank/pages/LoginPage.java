package capstoneproject.ZeroBank.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    private By usernameField = By.xpath("//input[@id='user_login']");
    private By passwordField = By.xpath("//input[@id='user_password']");
    private By loginButton = By.xpath("//input[@name='submit']");
    private By errorMessage = By.xpath("//div[@class='alert alert-error']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    public void enterUsername(String username) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        userField.clear();  // Ensures input field is empty before entering text
        userField.sendKeys(username);
    }
    public void enterPassword(String password) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passField.clear();
        passField.sendKeys(password);
    }
    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }
    public String getErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorElement.isDisplayed() ? errorElement.getText() : "";
    }
}