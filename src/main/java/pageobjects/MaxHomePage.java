package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import utilities.DriverFactory;
import utilities.ReportTestManager;

public class MaxHomePage extends BasePage {



	@FindBy(css="div#main-part button[id]")
	private List<WebElement> mainProductCategory;
	
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub

		return ExpectedConditions.visibilityOfAllElements(mainProductCategory);
	}



}
