package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

	protected WebDriver driver = null;
	private long LOAD_TIMEOUT = 30;
	private int AJAX_ELEMENT_TIMEOUT = 10;
	WebDriverWait wait;
	/*
	 * openPage method to init page factory and validates the landing pages
	 * 
	 */
	public BasePage() {
		this.driver = DriverFactory.getDriver();
	}

	public T openPage(Class<? extends BasePage> class1) {
		T page = null;
		try {

			driver = DriverFactory.getDriver();
			AjaxElementLocatorFactory ajaxEleFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
			page = (T) PageFactory.initElements(driver, class1);
			PageFactory.initElements(ajaxEleFactory, page);
			ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
			waitForPageToLoad(pageLoadCondition);
		} catch (Exception e) {
			System.out.println("---ERROR IN OPENING PAGE---" + e.getMessage());
		}
		return page;

	}

	protected abstract ExpectedCondition getPageLoadCondition();

	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);
		wait.until(pageLoadCondition);
	}
	
	/*
	 * Common Keywords used across the pages
	 * 
	 */

	protected void userActions(WebElement element, String objectType, String value) {
		Select s = null;
		switch (objectType.toLowerCase()) {
		case "textbox":
		case "dropdown":
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(value);
			ReportTestManager.getLog().info("Typing in : " + objectType + " entered the value as : " + value);
			ReportTestManager.getExtent().log(Status.INFO,
					"Typing in : " + objectType + " entered the value as : " + value);

			break;

		case "image":
		case "button":
		case "link":
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			ReportTestManager.getLog().info("Clicking on : " + objectType);
			ReportTestManager.getExtent().log(Status.INFO, "Clicking on : " + objectType);
			break;

		case "selectboxvalue":
			w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.visibilityOf(element));
			s = new Select(element);
			s.selectByValue(value);
			ReportTestManager.getLog().info("Clicking on Dropdown option by : " + objectType);
			ReportTestManager.getExtent().log(Status.INFO, "Clicking on Dropdown option by : " + objectType);
			break;

		case "selectboxvisibletext":
			s = new Select(element);
			s.selectByVisibleText(value);
			ReportTestManager.getLog().info("Clicking on Dropdown option by : " + objectType);
			ReportTestManager.getExtent().log(Status.INFO, "Clicking on Dropdown option by : " + objectType);

			break;

		case "wait":
			WebDriverWait w1 = new WebDriverWait(driver, 10);

			// ((JavascriptExecutor)
			// driver).executeScript("arguements[0].click();",element);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			break;

		case "validation":
			if (element.getText().equalsIgnoreCase(value)) {
				ReportTestManager.getLog()
						.info("Actual " + element.getText() + " Expected " + value + "------ Validation Successful");
				ReportTestManager.getExtent()
						.info("Actual " + element.getText() + " Expected " + value + "-------- Validation Successful");
			} else {

				String screenshot_path = "";
				try {
					// screenshot_path = ClickScreenshot.clickScreenshot(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// String image= logger.addScreenCapture(screenshot_path);
				// logger.log(LogStatus.FAIL, "Validation Failed",image);
			}
			break;
		case "partiallink":
			if (element.getText().contains(value)) {
				ReportTestManager.getLog()
						.info("Actual " + element.getText() + " Expected partialText " + value + "------ Validation Successful");
				ReportTestManager.getExtent()
						.info("Actual " + element.getText() + " Expected partialText " + value + "-------- Validation Successful");

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

	// Retrive the String -getText() from webelement from validation or String
	// -getAttribute() from webelement for value
	public String getMessage(WebElement element, String objectType, String value) {
		WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);
		String message = "";
		wait.until(ExpectedConditions.visibilityOf(element));
		if (objectType.equalsIgnoreCase("text")) {
			message = element.getText();
			ReportTestManager.getLog().info("Retrieving the webelement text : " + message + " for : " + "objecttype : "+objectType);
			ReportTestManager.getExtent().log(Status.INFO,
					"Retrieving the webelement text : " + message + " for : "+ "objecttype : "+objectType);
		} else if (objectType.equalsIgnoreCase("attribute")) {
			message = element.getAttribute(value);
			ReportTestManager.getLog().info(
					"Retrieving the attribute value : " + message + " for : " + element + " and attribute : " + value);
			ReportTestManager.getExtent().log(Status.INFO,
					"Retrieving the attribute value : " + message + " for : " + element + " and attribute : " + value);
		}
		else if(objectType.equalsIgnoreCase("partialtext"))
		{
			String completeText=element.getText().toLowerCase().trim();
			value.toLowerCase();
			if(completeText.contains(value))
			{
				
				message=completeText;
				System.out.println("element.getText()   "+message +"value    "+value);
				ReportTestManager.getLog().info(
						"Retrieving the complete text : " + completeText + " from partialtext : "+value +" for : " + element);
				ReportTestManager.getExtent().log(Status.INFO,
						"Retrieving the complete text : " + completeText + " from partialtext : "+value +" for : " + element);
			}
		}

		return message;

	}

	public boolean isElementPresent(WebElement element) {

		try {

			element.isDisplayed();
			ReportTestManager.getLog().info(
					"element is present " + element );
			ReportTestManager.getExtent().log(Status.INFO,
					"element is present " + element );
		
			return true;
		} catch (Exception e) {
			ReportTestManager.getLog().info(
					"element is NOT present " + element );
			ReportTestManager.getExtent().log(Status.INFO,
					"element is NOT present " + element );
			return false;
		}

	}
	
	/*  For Header and Footer operations
	 * Locating Header and footer webelements
	 * and performing actions on it
	 * Common sections present across the pages
	 */
	

	@FindBy(id = "account-actions-signin")
	private WebElement signIn;

	@FindBy(xpath = "//span[@class='MuiButton-label']//div[text()][@data-cs-mask='true']")
	private WebElement accountHi;

	@FindBy(css = "div[id='page-header'] div[id='dept-women']")
	private WebElement womenDept;

	@FindBy(css = "input[type='search']")
	private WebElement search;

	@FindBy(xpath = "//form//div[text()]/../div[text()]")
	private List<WebElement> searchList;

	@FindBy(xpath = "//form//div[contains(text(),'Departments')]/../../div[last()]")
	private WebElement seeMatching;

	@FindBy(xpath = "//form//div[text()]/../div[text()='Departments']/following-sibling::div//a")
	private List<WebElement> searchListDeptValues;
	
	@FindBy(xpath = "//form//div[text()]/../div[text()='Products']/following-sibling::div//a")
	private List<WebElement> searchListProdValues;

	
	public boolean getMtchingAlltext(String section) {
	
		String completeText= getMessage(seeMatching, "partialtext", section);
		System.out.println(section+"  getMtchingAlltext(String section)    "+completeText);
		
		return completeText.contains(section);
	}

	public List<WebElement> getsearchListValues(String section) {
		List<WebElement> requiredList=null;
		if (section.equalsIgnoreCase("departments")) {
			wait.until(ExpectedConditions.visibilityOfAllElements(searchListDeptValues));
			for (WebElement subele : searchListDeptValues) {
				//System.out.println(getMessage(subele, "validation", ""));
			}
			requiredList= searchListDeptValues;
		}
			else if(section.equalsIgnoreCase("Products"))
			{
				wait.until(ExpectedConditions.visibilityOfAllElements(searchListProdValues));
				for (WebElement subele : searchListProdValues) {
					//System.out.println(getMessage(subele, "validation", ""));
			}
				requiredList= searchListProdValues;
		}
		return requiredList;
	}

	public List<String> getsearchListTitleValues(String section) {
		wait = new WebDriverWait(DriverFactory.getDriver(), 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(searchList));
        List<String> reqSections=new ArrayList<String>();
		for (WebElement ele : searchList) {
				userActions(ele, "validation", section);
				reqSections.add(ele.getText());
			}
		
		return reqSections;
	}

	public void sendSearchText(String text) {

		userActions(search, "textbox", text);
		
	}

	public MaxHomePage openUrl(String url) {
		DriverFactory.getDriver().get(url);
		ReportTestManager.getLog().info("MAX Home Page Opened");
		ReportTestManager.getExtent().log(Status.INFO, " MAX Home Page Opened");
		return (MaxHomePage) openPage(MaxHomePage.class);
	}

	public MaxSignInPage gotToSignIn() {
		userActions(signIn, "link", "");
		return (MaxSignInPage) openPage(MaxSignInPage.class);
	}

	/*
	 * public MaxWomenPage goToWomen() { userActions(womenDept, "button", "");
	 * return (MaxWomenPage) openPage(MaxWomenPage.class); }
	 */

	public MaxSearchedPage clickOnSeeAll()
	{
		userActions(seeMatching, "link", "");
		return (MaxSearchedPage) openPage(MaxSearchedPage.class);
	}
	public String getMsg() {

		return getMessage(accountHi, "validation", "");

	}

}
