package capstoneproject.ZeroBank.stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import capstoneproject.ZeroBank.base.BaseTest;
import capstoneproject.ZeroBank.pages.AccountSummaryPage;
import capstoneproject.ZeroBank.pages.LogoutPage;
import capstoneproject.ZeroBank.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutStepDefinition {
	
	WebDriver driver;
	AccountSummaryPage accountSummaryPage;
	LogoutPage logoutPage;

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
		logoutPage = new LogoutPage(driver);
	}

	
	@Given("User logs in with valid credentials")
	public void user_logs_in_with_valid_credentials() {
		driver.get("http://zero.webappsecurity.com/login.html");
		accountSummaryPage.login("username", "password");
	}

	@When("Verify the dashboard loads successfully")
	public void verify_the_dashboard_loads_successfully() {
	  boolean status = logoutPage.checkDashBoard();
	  Assert.assertTrue(status,"Dashboard did not load successfully!");
	}

	@When("User clicks on the Logout button")
	public void user_clicks_on_the_logout_button() {
	    logoutPage.logout();
	}

	@Then("User should be redirected to the Login page")
	public void user_should_be_redirected_to_the_login_page()throws IOException {
		String expectedURL = "http://zero.webappsecurity.com/index.html";
        Assert.assertEquals(driver.getCurrentUrl(),expectedURL,"Logout failed! User is not redirected to the login page.");
        Utils.takeScreenshot(driver, "Logout_Successful");
	}

	@When("User clicks the Back button in the browser")
	public void user_clicks_the_back_button_in_the_browser() {
		 driver.navigate().back();
	}

	@Then("User should not be able to access the previous page")
	public void user_should_not_be_able_to_access_the_previous_page()throws IOException {
		String expectedURL = "http://zero.webappsecurity.com/index.html"; // Replace with actual login page URL
	    Assert.assertEquals(driver.getCurrentUrl(),expectedURL, "Session is not expired! User can still access the previous page.");
	    Utils.takeScreenshot(driver, "User_Not_Able_To_Access_Prevoius_Page");
	}
	
	@After
	public void tearDown() {
		BaseTest.teardown();
	}
}
