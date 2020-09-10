package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"@src/test/resources/failedruns/rerunchrome.txt","@src/test/resources/failedruns/rerunfirefox.txt"},
glue={"stepsDefinitions"},
plugin = { "pretty","html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
monochrome = true)
public class FailureRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
		


}
