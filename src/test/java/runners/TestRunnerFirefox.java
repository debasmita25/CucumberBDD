package runners;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/features/Max-SignInFirefox.feature",
glue={"stepsDefinitions"},
plugin = { "pretty","html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json",
"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:src/test/resources/failedruns/rerunfirefox.txt" },
monochrome = true,
//dryRun=true, [only program runs to check if each step has step definition]
strict=true,
tags = "@sanity")
public class TestRunnerFirefox extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
		


	}



