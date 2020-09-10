package stepsDefinitions;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.MaxHomePage;
import pageobjects.MaxProductPage;
import pageobjects.MaxSearchedPage;
import utilities.ReportTestManager;

public class MaxAddToBasketFunctionality extends BaseStep {
	
	Properties common;
	//FileInputStream fis;
	
	@Given("User opens a {string}")
	public void user_opens_a(String browser) {
		//System.out.println("BROWSER------"+browser);
		common=ReportTestManager.getProperyFile();
		//System.out.println("value for "+browser+" is : "+common.getProperty(browser));
	    getBrowser(common.getProperty(browser));
	}

	MaxHomePage mhp;
	MaxSearchedPage msp;
	MaxProductPage mpp;
	@Given("User navigates to Max Home {string}")
	public void user_navigates_to_Max_Home(String url) {
	   mhp = new MaxHomePage().openUrl(common.getProperty(url));
	}

	@When("User searches for product {string}")
	public void user_searches_for_product(String productname) {
		//System.out.println(common.getProperty(productname));
	    mhp.sendSearchText(productname);
	}

	@Then("User sees first section {string} list")
	public void user_sees_first_section_list(String section) {
		Assert.assertTrue(mhp.getsearchListTitleValues(section).contains(section));
		Assert.assertTrue(mhp.getsearchListValues(section).size()>0);
	   
	}
	

	@Then("User sees second section {string} list")
	public void user_sees_second_section_list(String section) {
	    Assert.assertTrue(mhp.getsearchListTitleValues(section).contains(section));
		Assert.assertTrue(mhp.getsearchListValues(section).size()>0);
	}
	
	@Then("User sees {string} link")
	public void user_sees_link(String text) {
	   Assert.assertTrue(mhp.getMtchingAlltext(text));
	  
	}
	
	@When("User clicks on {string} link")
	public void user_clicks_on_link(String text) {
		//Assert.assertTrue(mhp.getMtchingAlltext(text));
		System.out.println("user clicks on link   "+mhp.getMtchingAlltext(text));
		msp= mhp.clickOnSeeAll();
	}

	@Then("User is navigated to the {string} page")
	public void user_is_navigated_to_the_page(String string) {
		Assert.assertTrue(msp.verifyTitleMsg().contains(string));
	}
	
	@When("User checks for the available filters")
	public void user_checks_for_the_available_filters(io.cucumber.datatable.DataTable dataTable) {
		  
		  List<String> filters=dataTable.asList();
			int i=0;
			  for(WebElement ele:msp.filters) { 
				  Assert.assertTrue(filters.contains(ele.getText()));
			  }
		  //System.out.println(msp.filters.size());
		  Assert.assertTrue(msp.filters.size()>0);
	}

	@When("User and clicks on the {string} product")
	public void user_and_clicks_on_the_product(String ordinal) {
	mpp= msp.clickProduct(common.getProperty(ordinal));
	
		
	}


	  
	    
	  @Then("User adds the product to basket")
	  public void user_adds_the_product_to_basket()
	  {
		  for(WebElement ele:mpp.trackAllLinks)
		  {
			  System.out.println(ele.getText());
		  }
		  
	  }




}
