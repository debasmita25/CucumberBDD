package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/resources/features/SearchAProduct.feature"},
         glue="stepsDefinitions",plugin= {"pretty","html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json"
        			,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
         monochrome=true,strict=true)
public class GeneralRunner extends AbstractTestNGCucumberTests{
	
	

}
