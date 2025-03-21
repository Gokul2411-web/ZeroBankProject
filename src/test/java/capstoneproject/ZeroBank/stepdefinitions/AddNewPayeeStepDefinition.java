package capstoneproject.ZeroBank.stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import capstoneproject.ZeroBank.base.BaseTest;
import capstoneproject.ZeroBank.pages.AccountSummaryPage;
import capstoneproject.ZeroBank.pages.AddNewPayeePage;
import capstoneproject.ZeroBank.pages.PayBillsPage;
import capstoneproject.ZeroBank.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNewPayeeStepDefinition {
	
	WebDriver driver;
	AccountSummaryPage accountSummaryPage;
	PayBillsPage payBillsPage;
	AddNewPayeePage addNewPayeePage; 
	static ExtentReports extent;
	static ExtentTest test;
	static Properties properties;

	static {
		try {
			// Load properties file
			properties = new Properties();
			FileInputStream fis = new FileInputStream("src/test/resources/extent.properties");
			properties.load(fis);

			// Read report path from properties
			String reportPath = properties.getProperty("extent.reporter.spark.out");

			// Setup Extent Reports
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.loadXMLConfig("src/test/resources/extent-config.xml");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			// Adding system info
			extent.setSystemInfo("OS", properties.getProperty("systeminfo.os"));
			extent.setSystemInfo("User", properties.getProperty("systeminfo.user"));
			extent.setSystemInfo("Build", properties.getProperty("systeminfo.build"));
			extent.setSystemInfo("App Name", properties.getProperty("systeminfo.AppName"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() {
		String browser = System.getProperty("browser", "chrome");
		BaseTest.setup(browser);
		driver = BaseTest.getDriver();
		accountSummaryPage = new AccountSummaryPage(driver);
		payBillsPage = new PayBillsPage(driver);
		addNewPayeePage = new AddNewPayeePage(driver);
	}
	
	@Given("User logs in and navigates to Add New Payee page")
	public void user_logs_in_and_navigates_to_add_new_payee_page() {
		driver.get("http://zero.webappsecurity.com/login.html");
		accountSummaryPage.login("username", "password");
		accountSummaryPage.clickOnlineBanking();
		payBillsPage.clickPayBillsTab();
		addNewPayeePage.clickAddNewPayee();
		
		
	}

	@When("User enters payee name {string}")
	public void user_enters_payee_name(String string) {
	    addNewPayeePage.enterPayeeName(string);
	}

	@When("User enters address {string}")
	public void user_enters_address(String string) {
	    addNewPayeePage.enterPayeeAddress(string);
	}

	@When("User enters account number {string}")
	public void user_enters_account_number(String string) {
	    addNewPayeePage.enterAccountNumber(string);
	}

	@When("User clicks add payee button")
	public void user_clicks_add_payee_button() {
	    addNewPayeePage.clickAddPayee();
	}

	@Then("User should see {string} message")
	public void user_should_see_message(String string) throws IOException {
		boolean successMessage = addNewPayeePage.isSuccessMessageDisplayed();
        Assert.assertTrue(successMessage, "Success message not displayed!");
        Utils.takeScreenshot(driver, "New_Payee_Added ");
	}

	@When("User leaves account number empty")
	public void user_leaves_account_number_empty() {
		addNewPayeePage.enterAccountNumber("");
	}
	
	@Then("Error message {string} will be displayed")
	public void error_message_will_be_displayed(String string) throws IOException {
		Utils.takeScreenshot(driver, "Payee_Not_Added");
		System.out.println(string);
	}
	
	@After
	public void tearDown() {
		BaseTest.teardown();
	}
}
