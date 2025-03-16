package capstoneproject.ZeroBank.runners;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features="src/test/resources/features/addnewpayee.feature",glue= {"capstoneproject.ZeroBank.stepdefinitions"},
plugin = {
        "pretty",
        "html:target/AddNewPayeePage-report.html",
        "json:target/AddNewPayeePage.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,
    dryRun = false
)
public class AddNewPayeeRunner extends AbstractTestNGCucumberTests  {
	
}
