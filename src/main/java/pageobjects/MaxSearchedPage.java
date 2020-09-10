package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MaxSearchedPage extends BasePage {

	@FindBy(xpath="//h1")
	public WebElement searchedText;
	@FindBy(xpath="//button[@id='price-range-selector']/../..//button//div[text()]")
	public List<WebElement> filters;
	@FindBy(css="div[id*='product-']")
	public List<WebElement> products;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(searchedText);
	}
	
	public List<WebElement> getProducts()
	{
	  	
	 return products;
	}
	
	public MaxProductPage clickProduct(String ordinal)
	{
		for(int i=0;i<products.size();i++)
		  {
			  int ordinalNum=Integer.parseInt(ordinal);
			  if((i+1)==ordinalNum)
			  {
				products.get(i).click();
			  }
		  }
		
		return (MaxProductPage)openPage(MaxProductPage.class);
		
	}
	
	public List<WebElement> verifyTotalFilters()
	{
		return filters;
	}
	
	public  String verifyTitleMsg()
	{
		String reqString=getMessage(searchedText, "text", "");
		userActions(searchedText, "validation",reqString);
		return reqString;
		
	}
	

}
