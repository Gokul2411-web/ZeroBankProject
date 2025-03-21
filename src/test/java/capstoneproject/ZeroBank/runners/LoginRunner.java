package capstoneproject.ZeroBank.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/resources/features/login.feature",glue= {"capstoneproject.ZeroBank.stepdefinitions"},
plugin = {
        "pretty",
        "html:target/homePage-report.html",
        "json:target/homePage.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,
    dryRun = false
)
public class LoginRunner extends AbstractTestNGCucumberTests {

}
