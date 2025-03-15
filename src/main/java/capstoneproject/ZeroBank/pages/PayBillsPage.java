package capstoneproject.ZeroBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PayBillsPage {
    WebDriver driver;
    
    private By payBillsTab = By.xpath("//li[@id='pay_bills_tab']");
    private By payeeDropDown = By.xpath("//select[@id='sp_payee']");
    private By amountField = By.xpath("//input[@id='sp_amount']");
    private By dateField = By.xpath("//input[@id='sp_date']");
    private By payButton = By.xpath("//input[@id='pay_saved_payees']");
    private By successMessage = By.xpath("//div[@id='alert_content']");

    public PayBillsPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickPayBillsTab() {
    	driver.findElement(payBillsTab).click();
    }
    public void selectPayee(String payee) {
        WebElement p = driver.findElement(payeeDropDown);
        Select s1 = new Select(p);
        s1.selectByContainsVisibleText(payee);
    }

    public void enterAmount(int amount) {
        driver.findElement(amountField).sendKeys(String.valueOf(amount));
    }

    public void enterDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    public void clickPayButton() {
        driver.findElement(payButton).click();
    }

    public void leaveAmountFieldEmpty() {
        driver.findElement(amountField).clear();
    }

    public boolean isPaymentSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public String getAmountFieldErrorMessage() {
        return driver.findElement(amountField).getText();
    }

}