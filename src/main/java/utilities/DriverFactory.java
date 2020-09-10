package utilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

public class DriverFactory {
	

	private static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
		public static synchronized void setDriver(WebDriver driver) {

		dr.set(driver);
	}

	public static synchronized WebDriver getDriver() {

		return dr.get();
	}
	
	public static synchronized void quit()
	{
		if (getDriver() != null) {
			getDriver().quit();
			ReportTestManager.getExtent().log(Status.INFO, "Closing the Driver used by Thread : "+Thread.currentThread().getId());
			ReportTestManager.getLog().info("Closing the Driver used by Thread : "+Thread.currentThread().getId());
			
		}	
	}

}
