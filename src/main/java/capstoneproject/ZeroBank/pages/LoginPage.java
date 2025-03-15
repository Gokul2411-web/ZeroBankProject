package capstoneproject.ZeroBank.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    private By usernameField = By.id("user_login");
    private By passwordField = By.id("user_password");
    private By loginButton = By.name("submit");
    private By errorMessage = By.xpath("//div[@class='alert alert-error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed() ? errorElement.getText() : "";
    }
}