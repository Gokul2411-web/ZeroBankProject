package capstoneproject.ZeroBank.stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import capstoneproject.ZeroBank.base.BaseTest;
import capstoneproject.ZeroBank.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {
	
	WebDriver driver;
    LoginPage loginPage;
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
        BaseTest.setup("chrome");
        driver = BaseTest.driver;
        loginPage = new LoginPage(driver);
    }

	@Given("User is on the login page")
	public void user_is_on_the_login_page() {
		driver.get("http://zero.webappsecurity.com/login.html");
	}

	@When("User enters valid credentials {string} and {string}")
	public void user_enters_valid_credentials_and(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("User clicks on login button")
	public void user_clicks_on_login_button() throws InterruptedException {
		loginPage.clickLogin();
		Thread.sleep(3000);
		String title = driver.getTitle();
		if(title.contains("zero.webappsecurity.com")) {
			driver.navigate().back();
		}
		
	}

	@Then("User should be redirected to the Account Summary page")
	public void user_should_be_redirected_to_the_account_summary_page() {
		 Assert.assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards");
	}

	@When("User enters invalid credentials {string} and {string}")
	public void user_enters_invalid_credentials_and(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@Then("User should see an error message {string}")
	public void user_should_see_an_error_message(String expectedMessage) throws InterruptedException {
		Thread.sleep(2000);
		 Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
	}

	@When("User enters an empty username and password")
	public void user_enters_an_empty_username_and_password() {
		loginPage.enterUsername("");
        loginPage.enterPassword("");
	}
	
	@After
	public void tearDown() {
		BaseTest.teardown();
	}

}
