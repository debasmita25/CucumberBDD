package utilities;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	

	private static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
		public static synchronized void setDriver(WebDriver driver) {

		dr.set(driver);
	}

	public static synchronized WebDriver getDriver() {

		return dr.get();
	}

}
