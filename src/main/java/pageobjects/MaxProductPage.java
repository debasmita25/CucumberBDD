package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MaxProductPage extends BasePage {

	
	@FindBy(css="ol#breadcrumb li")
	public List<WebElement> trackAllLinks;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOfAllElements(trackAllLinks);
	}

}
