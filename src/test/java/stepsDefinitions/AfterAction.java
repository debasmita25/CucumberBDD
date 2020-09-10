package stepsDefinitions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import utilities.DriverFactory;
import utilities.ExtentManager;
import utilities.ReportTestManager;

public class AfterAction {
	
	WebDriver driver;
	
	@After
	public void tearDown(Scenario scn)
	{

		if(scn.isFailed())
		{
			System.out.println("FAILED ------");
			ReportTestManager.getExtent().log(Status.FAIL, "Scenario FAILED : "+scn.getName());
			ReportTestManager.getLog().error("Scenario FAILED : "+scn.getName());
		
			String path=ExtentManager.captureScreenshot();
			try {
				ReportTestManager.getExtent().fail(
						"<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver=DriverFactory.getDriver();
			byte[]  screenshot1=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			//scn.embed(screenshot1,"image/png") depreciated
			//scn.attach(screenshot1, "image/png", "Failed Testcase");
			//scn.embed(screenshot1, "image/png");
			try {
				//ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getBase64Screenshot(driver));
				ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scn.embed(screenshot1, "image/png");
			
		} 
		else {
			ReportTestManager.getExtent().log(Status.PASS, "Scenario PASSED : "+scn.getName());
			ReportTestManager.getLog().info("Scenario PASSED : "+scn.getName());
		}
		
		DriverFactory.getDriver().quit();
		ReportTestManager.stopExtent();
	}

}
