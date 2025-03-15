package capstoneproject.ZeroBank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountSummaryPage {
    private static final Logger logger = LoggerFactory.getLogger(AccountSummaryPage.class);
    WebDriver driver;
    WebDriverWait wait;
    
    private By onlineBanking = By.xpath("//*[@id='onlineBankingMenu']");
    private By accountSummary = By.id("account_summary_link");
    private By cashAccount = By.xpath("//h2[text()='Cash Accounts']");
    private By investmentAccount = By.xpath("//h2[text()='Investment Accounts']");
    private By creditAccount = By.xpath("//h2[text()='Credit Accounts']");
    private By loanAccount = By.xpath("//h2[text()='Loan Accounts']");

    public AccountSummaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    public void login(String username,String password) {
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    	loginPage.clickLogin();
		String title = driver.getTitle();
		if(title.contains("zero.webappsecurity.com")) {
			driver.navigate().back();
		}
    }
    
    public void clickOnlineBanking() {
    	try {
            wait.until(ExpectedConditions.elementToBeClickable(onlineBanking)).click();
        } catch (Exception e) {
            logger.error("Failed to click on Online Banking menu", e);
        }
    }
    
    public void clickAccountSummary() {
    	try {
            wait.until(ExpectedConditions.elementToBeClickable(accountSummary)).click();
        } catch (Exception e) {
            logger.error("Failed to click on Account Summary", e);
        }
    }


    public boolean isAccountTypeDisplayed(String accountType) {
        try {
            By locator;
            switch (accountType) {
                case "Cash Accounts":
                    locator = cashAccount;
                    break;
                case "Investment Accounts":
                    locator = investmentAccount;
                    break;
                case "Credit Accounts":
                    locator = creditAccount;
                    break;
                case "Loan Accounts":
                    locator = loanAccount;
                    break;
                default:
                    logger.warn("Invalid account type: " + accountType);
                    return false;
            }
            boolean displayed = driver.findElement(locator).isDisplayed();
            logger.info(accountType + " is displayed: " + displayed);
            return displayed;
        } catch (NoSuchElementException e) {
            logger.error("Element not found for account type: " + accountType, e);
            return false;
        }
    }
}

