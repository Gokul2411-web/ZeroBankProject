package capstoneproject.ZeroBank.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/accountsummary.feature",
    glue = {"capstoneproject.ZeroBank.stepdefinitions"},
    plugin = {
            "pretty",
            "html:target/AccountSummaryPage-report.html",
            "json:target/AccountSummaryPage.json",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false)
public class AccountSummaryRunner extends AbstractTestNGCucumberTests  {
	
}
