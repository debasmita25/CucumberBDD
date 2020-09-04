package roughSteps;

import org.testng.Assert;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class RoughSteps {
	


@Before
public void setUp()
{
	System.out.println("INSIDE BEFORE");
}

@After
public void tearDown(Scenario scn)
{
	System.out.println("INSIDE AFTER");
	if(scn.isFailed())
	{
		System.out.println("INSIDE AFTER FAILURE");
	}
}
	
	@Given("User navigates to URL")
	public void user_navigates_to_URL() {
	   System.out.println("User navigates to URL");
	}

	@When("User clicks on link")
	public void user_clicks_on_link() {
	    System.out.println("User clicks on link");
	   
	}

	@Then("User gets failure message")
	public void user_gets_failure_message() {
	 Assert.fail();
	}



}
