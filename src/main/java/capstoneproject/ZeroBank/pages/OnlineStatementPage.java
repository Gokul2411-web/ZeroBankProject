package capstoneproject.ZeroBank.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineStatementPage {
    WebDriver driver;
    
    private By onlineStatementTab = By.xpath("//li[@id='online_statements_tab']");
    private By accountDropdown = By.xpath("//select[@id='os_accountId']");
    private By yearField = By.xpath("//a[contains(text(),'2011')]");
    private By downloadPDFButton = By.xpath("//div[@id=\"os_2012\"]//a");

    public OnlineStatementPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickonlineStatementTab() {
    	driver.findElement(onlineStatementTab).click();
    }
    
    public void selectAccount(String accountName) {
       WebElement acc = driver.findElement(accountDropdown);
       Select s = new Select(acc);
       s.selectByContainsVisibleText(accountName);
    }

    public void selectYear() {
        driver.findElement(yearField).click();
    }

    public void clickDownloadPDF() {
    	try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Re-locate element before clicking to avoid stale reference
            WebElement pdfLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id=\"os_2012\"]//a")));
            pdfLink.click();
            
        } catch (StaleElementReferenceException e) {
            
            WebElement pdfLink = driver.findElement(By.xpath("//div[@id=\"os_2012\"]//a"));
            pdfLink.click();
            
            
        }
    }
}
