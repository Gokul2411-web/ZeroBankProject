package capstoneproject.ZeroBank.runners;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/resources/features/paybills.feature",glue= {"capstoneproject.ZeroBank.stepdefinitions"},
plugin = {
        "pretty",
        "html:target/PayBillsPage-report.html",
        "json:target/PayBillsPage.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,
    dryRun = false
)
public class PayBillsRunner extends AbstractTestNGCucumberTests {

}
