package capstoneproject.ZeroBank.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
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
public class PayBillsRunner {

}
