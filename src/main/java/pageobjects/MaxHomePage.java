package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

import utilities.DriverFactory;
import utilities.ReportTestManager;

public class MaxHomePage extends BasePage {
	
	@FindBy(id="account-actions-signin")
	private WebElement signIn;
	

	@FindBy(xpath="//span[@class='MuiButton-label']//div[text()][@data-cs-mask='true']")
	private WebElement accountHi;
	
	@FindBy(css="div[id='page-header'] div[id='dept-women']")
	private WebElement womenDept;

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		
		return ExpectedConditions.visibilityOf(signIn);
	}
	
	public MaxHomePage openUrl(String url)
	{
		DriverFactory.getDriver().get(url);
		ReportTestManager.getLog().info("MAX Home Page Opened");
		ReportTestManager.getExtent().log(Status.INFO, " MAX Home Page Opened");
		return (MaxHomePage)openPage(MaxHomePage.class);
	}
	
	public MaxSignInPage gotToSignIn()
	{
		userActions(signIn, "link", "");
		return (MaxSignInPage) openPage(MaxSignInPage.class);
	}
	
	/*
	 * public MaxWomenPage goToWomen() { userActions(womenDept, "button", "");
	 * return (MaxWomenPage) openPage(MaxWomenPage.class); }
	 */
	
	public String getMsg()
	{
		
		return getMessage(accountHi, "validation","");
	
	}

}
