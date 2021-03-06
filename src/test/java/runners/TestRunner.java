package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/features",  //can give package name or individual feature file
glue={"stepsDefinitions"}, //can give multiple package name
plugin = {"pretty","html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json"
		,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:src/test/resources/failedruns/rerunchrome.txt" },
monochrome = true,
//dryRun=true, [only program runs to check if each step has step definition]
strict=true,
tags = "@sanity")   // for OR  "@validchrome,@valid"
public class TestRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		
	 return super.scenarios();
	}

}
