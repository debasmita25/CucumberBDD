package utilities;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.core.api.Scenario;

public class ReportTestManager {
	
	
	private static ExtentReports extent=ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> testReport=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<Logger> log=new ThreadLocal<Logger>();
    
	public static synchronized void startReport(Scenario scn)
	{
		testReport.set(extent.createTest("Scenario started : "+scn.getName()));
		log.set(Logger.getLogger(Scenario.class));
	}
	
	public static synchronized ExtentTest getExtent()
	{
		return testReport.get();
	}
	
	public static synchronized Logger getLog()
	{
		return log.get();
	}
	
	public static synchronized void  stopExtent()
	{
		if(extent!=null)
		extent.flush();
	}
	
	
}
