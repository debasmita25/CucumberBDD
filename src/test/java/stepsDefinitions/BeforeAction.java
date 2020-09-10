package stepsDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;

import com.aventstack.extentreports.Status;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import utilities.ReportTestManager;

public class BeforeAction {
	
	String screenshotdir = System.getProperty("user.dir") + "/target/Screenshots/";
	@Before
	public synchronized void setUp(Scenario scn)
	{
		
		System.out.println("BEFORE.....");
		
		if ((new File(screenshotdir)).exists())
			try {
				FileUtils.cleanDirectory(new File(screenshotdir));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		DOMConfigurator.configure("log4j.xml");
		ReportTestManager.startReport(scn);
		ReportTestManager.getExtent().log(Status.INFO, "Scenario started : "+scn.getName());
		
		ReportTestManager.getLog().info("Scenario started : "+scn.getName());
	}

}
