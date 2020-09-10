 package stepsDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverFactory;
import utilities.ExtentManager;
import utilities.ReportTestManager;


public class BaseStep {
	
	
	private WebDriver driver=null;
	private static ThreadLocal<DesiredCapabilities> dc = new ThreadLocal<DesiredCapabilities>();
	
	
	

	public synchronized void getBrowser(String name,String grid) {

		if (grid.equalsIgnoreCase("true")) {
			switch (name) {
			case "chrome":
				dc.set(DesiredCapabilities.chrome());
				dc.get().setBrowserName(name);
				dc.get().setPlatform(Platform.ANY);
				// ChromeOptions option=new ChromeOptions();
				// option.merge(dc.get());
				break;
			case "firefox":
				dc.set(DesiredCapabilities.firefox());
				dc.get().setBrowserName(name);
				dc.get().setPlatform(Platform.ANY);
				break;
			case "ie":
				dc.set(DesiredCapabilities.internetExplorer());
				dc.get().setBrowserName("iexplore");
				dc.get().setPlatform(Platform.WINDOWS);
				break;

			}

			try {
				DriverFactory.setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc.get()));
				//context.setAttribute("WebDriver", dr.get());
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			switch (name) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						"F:\\Selenium PreRequisites\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				ReportTestManager.getExtent().log(Status.INFO, "Chrome Browser is Launched");
				ReportTestManager.getLog().info("Chrome Browser is Launched");
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver",
						"F:\\Selenium PreRequisites\\executables\\iedriverserver.exe");
				DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				InternetExplorerOptions option = new InternetExplorerOptions();
				option.merge(cap);
				driver = new InternetExplorerDriver(option);
				ReportTestManager.getExtent().log(Status.INFO, "Internet Explorer browser is Launched");
				ReportTestManager.getLog().info("Internet Explorer browser is Launched");
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver",
						"F:\\Selenium PreRequisites\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				ReportTestManager.getExtent().log(Status.INFO, "Firefox browser is Launched");
				ReportTestManager.getLog().info("Firefox browser is Launched");
				break;

			}
			DriverFactory.setDriver(driver);
			//context.setAttribute("WebDriver", dr.get());
		}

	}

	public synchronized void getBrowser(String name) {

		switch (name) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "F:\\Selenium PreRequisites\\executables\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions option=new ChromeOptions();
			option.setExperimentalOption("prefs", prefs);
			option.addArguments("start-maximized");
			option.addArguments("disable-infobars");
			option.addArguments("--disable-extensions");
			option.addArguments("--disable-notifications");
			driver = new ChromeDriver(option);
			ReportTestManager.getExtent().log(Status.INFO, "Chrome Browser is Launched");
			ReportTestManager.getLog().info("Chrome Browser is Launched");
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "F:\\Selenium PreRequisites\\executables\\iedriverserver.exe");
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			System.out.println(cap.getPlatform()+"   "+cap.getBrowserName());
			InternetExplorerOptions optionIE = new InternetExplorerOptions();
			System.out.println(optionIE.getPlatform()+"     "+optionIE.getBrowserName());
			
			optionIE.merge(cap);
			driver = new InternetExplorerDriver(optionIE);
			ReportTestManager.getExtent().log(Status.INFO, "Internet Explorer browser is Launched");
			ReportTestManager.getLog().info("Internet Explorer browser is Launched");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "F:\\Selenium PreRequisites\\executables\\geckodriver.exe");
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile testprofile = profile.getProfile("default");
			testprofile.setPreference("dom.webnotifications.enabled", false);
			testprofile.setPreference("dom.push.enabled", false);
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability(FirefoxDriver.PROFILE, testprofile);
			FirefoxOptions opt = new FirefoxOptions();
			opt.merge(dc);
			driver = new FirefoxDriver(opt);
			ReportTestManager.getExtent().log(Status.INFO, "Firefox browser is Launched");
			ReportTestManager.getLog().info("Firefox browser is Launched");
			break;

		}
		  DriverFactory.setDriver(driver);
		  DriverFactory.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  DriverFactory.getDriver().manage().window().maximize();
		//context.setAttribute("WebDriver", dr.get());

	}
	
	public void quit() {
		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
			ReportTestManager.getExtent().log(Status.INFO, "Closing the Driver used by Thread : "+Thread.currentThread().getId());
			ReportTestManager.getLog().info("Closing the Driver used by Thread : "+Thread.currentThread().getId());
			
		}
	}
	
	/*
	 * public String getBase64Screenshot(WebDriver driver) throws IOException {
	 * String Base64StringofScreenshot=""; File src = ((TakesScreenshot)
	 * driver).getScreenshotAs(OutputType.FILE); // for saving screenshots in local
	 * - this is optional Date oDate = new Date(); SimpleDateFormat oSDF = new
	 * SimpleDateFormat("ddMMYYYY_HHmmss"); String sDate = oSDF.format(oDate);
	 * FileUtils.copyFile(src, new File(screenshotdir + "Screenshot_" + sDate +
	 * ".png")); // byte[] fileContent = FileUtils.readFileToByteArray(src);
	 * Base64StringofScreenshot = "data:image/png;base64," +
	 * Base64.getEncoder().encodeToString(fileContent); return
	 * Base64StringofScreenshot; }
	 */


}
