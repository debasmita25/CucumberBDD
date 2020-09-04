package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import utilities.DriverFactory;
import utilities.ReportTestManager;

public abstract class BasePage<T> {
	
	protected WebDriver driver=null;
	private long LOAD_TIMEOUT=30;
	private int AJAX_ELEMENT_TIMEOUT=10;
	
	
	public BasePage()
	{
		this.driver=DriverFactory.getDriver();	}
	
	public T openPage(Class<T> clazz)
	{
		T page=null;
		try {
		
		driver=DriverFactory.getDriver();
		AjaxElementLocatorFactory ajaxEleFactory= new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
		page=PageFactory.initElements(driver, clazz);
		PageFactory.initElements(ajaxEleFactory, page);
		ExpectedCondition pageLoadCondition=((BasePage) page).getPageLoadCondition();
		waitForPageToLoad(pageLoadCondition);
		}catch(Exception e)
		{
			System.out.println("---ERROR IN OPENING PAGE---"+e.getMessage());
		}
		return page;
		
	}
	
	protected abstract ExpectedCondition getPageLoadCondition();
	
	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
    	WebDriverWait wait = new WebDriverWait(driver,LOAD_TIMEOUT);
        wait.until(pageLoadCondition);
    }
	
	protected void userActions(WebElement element,String objectType,String value)
	{
		Select s = null;
		switch(objectType.toLowerCase())
		{
			case "textbox": case "dropdown":
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.visibilityOf(element));
				element.clear();
				element.sendKeys(value);
				ReportTestManager.getLog().info("Typing in : "+objectType +" entered the value as : "+value);
				ReportTestManager.getExtent().log(Status.INFO, "Typing in : "+objectType +" entered the value as : "+value);
				
				break;
				
			case "image" : case "button": case "link":
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				ReportTestManager.getLog().info("Clicking on : "+objectType);
				ReportTestManager.getExtent().log(Status.INFO, "Clicking on : "+objectType);
				break;
				
			case "selectboxvalue":
				w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.visibilityOf(element));
				s = new Select(element);
				s.selectByValue(value);
				ReportTestManager.getLog().info("Clicking on Dropdown option by : "+objectType);
				ReportTestManager.getExtent().log(Status.INFO, "Clicking on Dropdown option by : "+objectType);
				break;
			
			case "selectboxvisibletext":
				s = new Select(element);
				s.selectByVisibleText(value);
				ReportTestManager.getLog().info("Clicking on Dropdown option by : "+objectType);
				ReportTestManager.getExtent().log(Status.INFO, "Clicking on Dropdown option by : "+objectType);
				
				break;	
				
			case "wait":
				WebDriverWait w1 = new WebDriverWait(driver, 10);
				
				//((JavascriptExecutor) driver).executeScript("arguements[0].click();",element);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
				
				break;
				
			case "validation":
				if(element.getText().equalsIgnoreCase(value))
				{
					ReportTestManager.getLog().info("Validation Successful");
					ReportTestManager.getExtent().info("Validation Successful");
				}
				else
				{
					
					String screenshot_path="";
					try {
						//screenshot_path = ClickScreenshot.clickScreenshot(driver);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//String image= logger.addScreenCapture(screenshot_path);
					//logger.log(LogStatus.FAIL, "Validation Failed",image);
				}
				break;
	
				 
			default:
				System.out.println("Object Not Found, Please Add it!!!");
				break;
			case "objectpresence":
			case "textcheck":
			case "datepick":
			case "makemytripdatepick":
		
		}
		
	}
	
	protected String getMessage(WebElement element,String objectType,String value)
	{
		String message="";
		if(objectType.equalsIgnoreCase("validation"))
		{
		message=element.getText();
		ReportTestManager.getLog().info("Retrieving the webelement text : "+message+" for : "+objectType);
		ReportTestManager.getExtent().log(Status.INFO, "Retrieving the webelement text : "+message+" for : "+objectType);
		}	
		else if(objectType.equalsIgnoreCase("webelement"))
		{
		message=element.getAttribute(value);
		ReportTestManager.getLog().info("Retrieving the attribute value : "+message+" for : "+element+" and attribute : "+value);
		ReportTestManager.getExtent().log(Status.INFO, "Retrieving the attribute value : "+message+" for : "+element+" and attribute : "+value);
		}
		
		return message;
		
		
	}
	
	protected  boolean isElementPresent(WebElement element)
	{
		
		
		try {
		  
		   element.getText();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	
	}
	
}
