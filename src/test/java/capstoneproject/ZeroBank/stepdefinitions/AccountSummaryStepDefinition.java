package capstoneproject.ZeroBank.stepdefinitions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import capstoneproject.ZeroBank.base.BaseTest;
import capstoneproject.ZeroBank.pages.AccountSummaryPage;
import capstoneproject.ZeroBank.utils.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class AccountSummaryStepDefinition {
	WebDriver driver;
	AccountSummaryPage accountSummaryPage;
	static ExtentReports extent;
	static ExtentTest test;
	static Properties properties;
	static {
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream("src/test/resources/extent.properties");
			properties.load(fis);
			String reportPath = properties.getProperty("extent.reporter.spark.out");
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.loadXMLConfig("src/test/resources/extent-config.xml");
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
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
	}

	@Given("User is on login page")
	public void user_is_on_login_page() {
		driver.get("http://zero.webappsecurity.com/login.html");
	}

	@When("User logs into the application")
	public void user_logs_into_the_application() throws InterruptedException {
		accountSummaryPage.login("username", "password");
	}

	@When("User clicks on the Online Banking")
	public void user_clicks_on_the_online_banking() {
		accountSummaryPage.clickOnlineBanking();
	}

	@When("User clicks on the Account Summary")
	public void user_clicks_on_the_account_summary() {
		accountSummaryPage.clickAccountSummary();
	}

	@Then("User should see all account types and balances displayed")
	public void user_should_see_all_account_types_and_balances_displayed() throws IOException {
		String[] accountTypes = { "Cash Accounts", "Investment Accounts", "Credit Accounts", "Loan Accounts" };
		for (String accountType : accountTypes) {
			boolean isDisplayed = accountSummaryPage.isAccountTypeDisplayed(accountType);
			Assert.assertTrue(isDisplayed, accountType + " account type is not displayed!");
		}
		Utils.takeScreenshot(driver, "Account_Summary");
	}

	@When("User navigates to the Account Summary page")
	public void user_navigates_to_the_account_summary_page() {
		accountSummaryPage.clickOnlineBanking();
		accountSummaryPage.clickAccountSummary();
	}

	@Then("User should see the following account types displayed:")
	public void user_should_see_the_following_account_types_displayed(DataTable accountTypesTable) throws IOException {
		List<String> accountTypes = accountTypesTable.asList();
		for (String accountType : accountTypes) {
			boolean isDisplayed = accountSummaryPage.isAccountTypeDisplayed(accountType);
			Assert.assertTrue(isDisplayed, accountType + " account type is not displayed!");
		}
		Utils.takeScreenshot(driver, "Validate_Account_Types");
	}

	@After
	public void tearDown() {
		BaseTest.teardown();
	}
}
