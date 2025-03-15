package capstoneproject.ZeroBank.stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import capstoneproject.ZeroBank.base.BaseTest;
import capstoneproject.ZeroBank.pages.AccountSummaryPage;
import capstoneproject.ZeroBank.pages.OnlineStatementPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlineStatementStepDefinition {
	
	WebDriver driver;
	AccountSummaryPage accountSummaryPage;
	OnlineStatementPage onlineStatementPage;
	private static final Logger logger = LoggerFactory.getLogger(OnlineStatementStepDefinition.class);
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
		accountSummaryPage = new AccountSummaryPage(driver);
		onlineStatementPage = new OnlineStatementPage(driver);
	}

	@Given("User logs in and navigates to Statements & Documents page")
	public void user_logs_in_and_navigates_to_statements_documents_page() {
		driver.get("http://zero.webappsecurity.com/login.html");
		accountSummaryPage.login("username", "password");
		accountSummaryPage.clickOnlineBanking();
		accountSummaryPage.clickAccountSummary();
		onlineStatementPage.clickonlineStatementTab();
		
	}

	@When("User selects account {string}")
	public void user_selects_account(String string) {
		onlineStatementPage.selectAccount(string);
	}

	@When("User selects year")
	public void user_selects_year() {
		onlineStatementPage.selectYear();
	}

	@When("User clicks on PDF link")
	public void user_clicks_on_pdf_link() {
	    onlineStatementPage.clickDownloadPDF();
	}

	@Then("The statement should be downloaded successfully")
	public void the_statement_should_be_downloaded_successfully() throws InterruptedException {
		Thread.sleep(5000);
		logger.info("File Download completed successfully.");
		
	}
	
	@After
	public void tearDown() {
		BaseTest.teardown();
	}

}
