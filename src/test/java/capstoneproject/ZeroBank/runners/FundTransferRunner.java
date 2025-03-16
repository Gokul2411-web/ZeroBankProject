package capstoneproject.ZeroBank.runners;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/features/fundtransfer.feature",
    glue = {"capstoneproject.ZeroBank.stepdefinitions"},
    plugin = {
            "pretty",
            "html:target/AccountSummaryPage-report.html",
            "json:target/AccountSummaryPage.json",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false)
public class FundTransferRunner extends AbstractTestNGCucumberTests {

}
