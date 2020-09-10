package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.core.api.Scenario;

public class ReportTestManager {
	
	
	private static ExtentReports extent=ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> testReport=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<Logger> log=new ThreadLocal<Logger>();
	public static ThreadLocal<Properties> propertyfile=new ThreadLocal<Properties>();
    private static FileInputStream fis;
	public static synchronized void startReport(Scenario scn)
	{
		testReport.set(extent.createTest("Scenario started : "+scn.getName()));
		log.set(Logger.getLogger(Scenario.class));
	    setPropertyFile();
	    getExtent().info("Property file setup for : "+scn.getName());
		getLog().info("Property file setup for : "+scn.getName());
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
	
	public static synchronized void setPropertyFile()
	{
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\common.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			propertyfile.set(new Properties());
			propertyfile.get().load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized Properties getProperyFile()
	{
		return propertyfile.get();
	}
	
	
}
