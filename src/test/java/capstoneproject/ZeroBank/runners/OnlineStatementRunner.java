 package capstoneproject.ZeroBank.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

 @CucumberOptions(
     features = "src/test/resources/features/onlinestatements.feature",
     glue = {"capstoneproject.ZeroBank.stepdefinitions"},
     plugin = {
             "pretty",
             "html:target/OnlineStatementPage-report.html",
             "json:target/OnlineStatementPage.json",
             "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
         },
         monochrome = true,
         dryRun = false)
public class OnlineStatementRunner extends AbstractTestNGCucumberTests {

}
