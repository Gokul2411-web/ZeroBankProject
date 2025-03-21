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
import capstoneproject.ZeroBank.pages.PayBillsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PayBillsStepDefinition {

	WebDriver driver;
	AccountSummaryPage accountSummaryPage;
	PayBillsPage payBillsPage;
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
	}
	
	@Given("User logs in and User navigates to the Pay Bills page")
	public void user_logs_in_and_user_navigates_to_the_pay_bills_page() {
		driver.get("http://zero.webappsecurity.com/login.html");
		accountSummaryPage.login("username", "password");
		accountSummaryPage.clickOnlineBanking();
		payBillsPage.clickPayBillsTab();
	}
	
	@When("User select a payee {string}")
	public void user_select_a_payee(String string) {
		payBillsPage.selectPayee(string);
	}
	
	@When("User enters valid amount {int} and date {string}")
	public void user_enters_valid_amount_and_date(Integer int1, String string) {
		payBillsPage.enterAmount(int1);
		payBillsPage.enterDate(string);
	}
	
	@When("User clicks pay button")
	public void user_clicks_pay_button() {
		payBillsPage.clickPayButton();
	}
	
	@Then("User sees a success message")
	public void user_sees_a_success_message() {
		boolean successMessage = payBillsPage.isPaymentSuccessMessageDisplayed();
		Assert.assertTrue(successMessage, "Success message not displayed!");
	}

	@When("User schedules a payment of {int} on {string}")
	public void user_schedules_a_payment_of_on(Integer amount, String date) {
		payBillsPage.enterAmount(amount);
		payBillsPage.enterDate(date);
		payBillsPage.clickPayButton();
	}

	@Then("The payment should be scheduled successfully")
	public void the_payment_should_be_scheduled_successfully() {
		boolean scheduledMessage = payBillsPage.isPaymentSuccessMessageDisplayed();
		Assert.assertTrue(scheduledMessage, "Scheduled payment not confirmed!");
	}

	@When("User	leaves the amount field empty.")
	public void user_leaves_the_amount_field_empty() {
		payBillsPage.leaveAmountFieldEmpty();
		payBillsPage.clickPayButton();
	}

	@Then("Error message {string} should be displayed")
	public void error_message_should_be_displayed(String errorMessage) {
		 Assert.assertNotEquals(payBillsPage.getAmountFieldErrorMessage(),null, errorMessage);
	}
	
	@After
	public void tearDown() {
		BaseTest.teardown();
	}

}
