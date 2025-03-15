package capstoneproject.ZeroBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FundTransferPage {
    WebDriver driver;
    private By fundTransfer = By.xpath("//a[contains(text(),'Transfer Funds')]");
    private By fromAccountDropdown = By.xpath("//select[@id='tf_fromAccountId']");
    private By toAccountDropdown = By.xpath("//select[@id='tf_toAccountId']");
    private By amountField = By.xpath("//input[@id='tf_amount']");
    private By continueButton = By.xpath("//button[@id='btn_submit']");
    private By submitButton = By.xpath("//button[@id='btn_submit']");
    private By successMessage = By.xpath("//div[@class='alert alert-success']");
    private By insufficientFundsMessage = By.id("insufficientFunds");
    private By invalidAmountMessage = By.id("invalidAmount");

    public FundTransferPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickFundTransfer() {
    	driver.findElement(fundTransfer).click();
    }
    public void performFundTransfer(String fromAccount, String toAccount, int amount) {
        driver.findElement(fromAccountDropdown).sendKeys(fromAccount);
        driver.findElement(toAccountDropdown).sendKeys(toAccount);
        driver.findElement(amountField).sendKeys(String.valueOf(amount));
        driver.findElement(continueButton).click();
        driver.findElement(submitButton).click();
    }

    public boolean isTransferSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public boolean isInsufficientFundsMessageDisplayed() {
        return driver.findElement(insufficientFundsMessage).isDisplayed();
    }

    public boolean isInvalidAmountMessageDisplayed() {
        return driver.findElement(invalidAmountMessage).isDisplayed();
    }
}