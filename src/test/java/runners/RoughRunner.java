package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/features/rough.feature",  //can give package name or individual feature file
glue={"roughSteps"}, //can give multiple package name
plugin = {"pretty","html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json"},
monochrome = true,
//dryRun=true, [only program runs to check if each step has step definition]
strict=true)
//tags = "@sanity and @valid")   // for OR  "@validchrome,@valid"
public class RoughRunner extends AbstractTestNGCucumberTests {


}
