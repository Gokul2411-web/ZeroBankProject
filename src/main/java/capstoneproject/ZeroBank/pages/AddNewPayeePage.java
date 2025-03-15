package capstoneproject.ZeroBank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewPayeePage {
    WebDriver driver;
    
    private By addNewPayeeTab = By.xpath("//a[contains(text(),'Add New Payee')]");
    private By payeeNameField = By.xpath("//input[@id='np_new_payee_name']");
    private By payeeAddressField = By.xpath("//textarea[@id='np_new_payee_address']");
    private By accountNumberField = By.xpath("//input[@id='np_new_payee_account']");
    private By addButton = By.xpath("//input[@id='add_new_payee']");
    private By successMessage = By.xpath("//div[@id='alert_content']");

    public AddNewPayeePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickAddNewPayee() {
    	driver.findElement(addNewPayeeTab).click();
    }
    public void enterPayeeName(String name) {
        driver.findElement(payeeNameField).sendKeys(name);
       
    }

    public void enterPayeeAddress(String address) {
        driver.findElement(payeeAddressField).sendKeys(address);        
    }

    public void enterAccountNumber(String accountNumber) {
        driver.findElement(accountNumberField).sendKeys(accountNumber);
    }

    public void clickAddPayee() {
        driver.findElement(addButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }

}
