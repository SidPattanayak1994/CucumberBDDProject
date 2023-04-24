package TestRunner;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.BeforeClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//@RunWith(Cucumber.class)
@CucumberOptions(
		
		//features ={".//Features/LoginFeature.feature",".//Features/Cutomers.feature"},
		features = { ".//Features/CustomersVendorsMap.feature" }, 
		glue = "StepDefinition", 
		dryRun = false, 
		monochrome = true, 
		tags = "@Sanity",
		//tags="@Sanity or @regression",
		// tags="@Sanity and @regression",
		// tags="@Sanity and not @regression",
		//plugin = { "pretty", "html:target/cucumber-reports/reports1.html" } //for junit report
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class TestRun extends AbstractTestNGCucumberTests {
//***************This class should be empty**************************
}


