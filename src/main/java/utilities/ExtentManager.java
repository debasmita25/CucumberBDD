package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {
	
	private static ExtentReports extent;
    private static String reportFileName = "Test-Automaton-Report"+".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +"\\src\\test\\resources\\extentreports\\Test-Automaton-Report.html";
    private static String reportFileLocation =  reportFilepath+reportFileName;
  
 
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance() {
      //  String fileName = getReportPath(reportFilepath);
    	File f=new File(System.getProperty("user.dir") +"\\src\\test\\resources\\extentreports");
    	if(!f.exists())
    	{
    		f.mkdir();
    		
    		
    	}
    	
    	f=new File(f,"\\Test-Automaton-Report.html");
    	if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(f);
       
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
 
        return extent;
    }
   private static WebDriver driver;
     static int count=0;
    public static String captureScreenshot()
    {
    	File f=new File(System.getProperty("user.dir") +"\\src\\test\\resources\\screenshots");
    	if(!f.exists())
    	{
    		f.mkdir();
    	}
    	driver=DriverFactory.getDriver();
    	File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	count++;
    	try {
			FileUtils.copyFile(screenshot, new File(f,"\\error_"+count+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ReportTestManager.getExtent().log(Status.INFO, "Screenshot is Taken : " +count);
    	ReportTestManager.getLog().info("Screenshot is Taken : " +count);
    	return System.getProperty("user.dir") +"\\src\\test\\resources\\screenshots\\error_"+count+".png";
    }
}
