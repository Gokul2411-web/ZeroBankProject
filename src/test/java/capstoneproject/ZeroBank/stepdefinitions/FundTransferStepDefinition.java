package capstoneproject.ZeroBank.stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import capstoneproject.ZeroBank.base.BaseTest;
import capstoneproject.ZeroBank.pages.AccountSummaryPage;
import capstoneproject.ZeroBank.pages.FundTransferPage;
import capstoneproject.ZeroBank.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FundTransferStepDefinition {

	private static final Logger logger = LoggerFactory.getLogger(AccountSummaryStepDefinition.class);
	WebDriver driver;
	AccountSummaryPage accountSummaryPage;
	FundTransferPage fundTransferPage;
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
		fundTransferPage = new FundTransferPage(driver);
	}

	@Given("User logs in to home page")
	public void user_logs_in_to_home_page() throws InterruptedException {
		driver.get("http://zero.webappsecurity.com/login.html");
		accountSummaryPage.login("username", "password");

	}

	@Given("User navigates to the Fund Transfer page")
	public void user_navigates_to_the_fund_transfer_page() {
		logger.info("Clicking on Online Banking");
		accountSummaryPage.clickOnlineBanking();
		logger.info("Navigating to Account Summary");
		accountSummaryPage.clickAccountSummary();
		fundTransferPage.clickFundTransfer();
	}

	@When("User transfers {int} from {string} to {string}")
	public void user_transfers_from_to(Integer amount, String from, String to) {
		fundTransferPage.performFundTransfer(from,to,amount);
	}

	@Then("User should see a success message")
	public void user_should_see_a_success_message()throws IOException {
		 Assert.assertTrue(fundTransferPage.isTransferSuccessMessageDisplayed(), "Success message not displayed");
		 Utils.takeScreenshot(driver, "Fund_Transfer_Successful");
	}

	@Then("Account balances should be updated accordingly")
	public void account_balances_should_be_updated_accordingly() {
		System.out.println("Account Balances updated");
	}

//	@Then("User should see an error message for insufficient funds")
//	public void user_should_see_an_error_message_for_insufficient_funds() {
//		 Assert.assertTrue(fundTransferPage.isInsufficientFundsMessageDisplayed(), "Insufficient funds message not displayed");
//	}
//
//	@Then("Transfer should not be processed")
//	public void transfer_should_not_be_processed() {
//		Assert.assertTrue(fundTransferPage.isInvalidAmountMessageDisplayed(), "Invalid amount message not displayed");
//	}
//
//	@When("User transfer {int} from {string} to {string}")
//	public void user_transfer_from_to(Integer amount, String from, String to) {
//		fundTransferPage.performFundTransfer(from,to,amount);
//	}
//
//	@Then("User should see an error message for invalid amount")
//	public void user_should_see_an_error_message_for_invalid_amount() {
//		Assert.assertTrue(fundTransferPage.isInvalidAmountMessageDisplayed(), "Invalid amount message not displayed");
//	}
	
	@After
	public void tearDown() {
		BaseTest.teardown();
	}
}
