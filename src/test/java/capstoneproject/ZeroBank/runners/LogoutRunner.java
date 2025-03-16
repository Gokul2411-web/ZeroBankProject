package capstoneproject.ZeroBank.runners;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
    features = "src/test/resources/features/logout.feature",
    glue = {"capstoneproject.ZeroBank.stepdefinitions"},
    plugin = {
            "pretty",
            "html:target/LogoutPage-report.html",
            "json:target/LogoutPage.json",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false)
public class LogoutRunner extends AbstractTestNGCucumberTests {
	
}
