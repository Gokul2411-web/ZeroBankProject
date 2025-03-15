package capstoneproject.ZeroBank.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
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
public class LogoutRunner {
	
}
