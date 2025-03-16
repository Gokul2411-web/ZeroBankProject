package capstoneproject.ZeroBank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
    WebDriver driver;
    WebDriverWait wait;
    private By username = By.xpath("//*[@id=\"settingsBox\"]/ul/li[3]/a");
    private By logout = By.xpath("//a[@id='logout_link']");

    // Constructor
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public boolean checkDashBoard() {
    	WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='active item']")));
    	return dashboardElement.isDisplayed();
    }

    // Logout method
    public void logout() {
    	driver.findElement(username).click();
        driver.findElement(logout).click();
    }
}
