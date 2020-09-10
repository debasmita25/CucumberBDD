package stepsDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.MaxHomePage;
import pageobjects.MaxSignInPage;
import utilities.DriverFactory;
import utilities.ExtentManager;
import utilities.ReportTestManager;

public class MaxSignInFunctionality extends BaseStep {

	MaxHomePage mhp;
	MaxSignInPage msip;

	Properties common;

	@Given("^User opens \"([^\"]*)\"$")
	public void user_opens(String browser) throws Throwable {
		common = ReportTestManager.getProperyFile();

		getBrowser(common.getProperty(browser));
	}

	@Given("^User navigates to URL \"([^\"]*)\"$")
	public void user_navigates_to_URL(String url) throws Throwable {

		mhp = new MaxHomePage().openUrl(common.getProperty(url));

	}

	@Given("^User clicks on SignIn link$")
	public void user_clicks_on_SignIn_link() throws Throwable {
		msip = mhp.gotToSignIn();

	}

	@When("^User enters \"([^\"]*)\" and \"([^\"]*)\" and clicks on SignIn$")
	public void user_enters(String emailValue, String passwordValue) throws Throwable {
		msip.doSignInAsValidUser(emailValue, passwordValue);
	}

	@When("^User enters invalid \"([^\"]*)\" and invalid \"([^\"]*)\" and clicks on SignIn$")
	public void user_enters_invalid_and_invalid_and_clicks_on_SignIn(String emailValue, String passwordValue)
			throws Throwable {
		msip.doSignInAsInvalidUser(emailValue, passwordValue);
	}

	@Then("^User should be able to SignIn Successfully$")
	public void user_should_be_able_to_SignIn_Successfully() throws Throwable {
		// System.out.println(msip.getInvalidPassEmailMsg());
		Assert.assertEquals(mhp.getMsg(), "Hi, Debasmita");
	}

	@When("User enters Username and Password using datatable and clicks on SignIn")
	public void user_enters_Username_and_Password_using_datatable_and_clicks_on_SignIn(DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		List<Map<String, String>> list = dataTable.asMaps();
		msip.doSignInAsValidUser(list.get(0).get("username"), list.get(0).get("password"));
		// System.out.println(list.get(0).get("username")+"
		// "+list.get(0).get("password"));

	}

	@Then("^User should get error \"([^\"]*)\"$")
	public void user_should_get_error(String expected) throws Throwable {
		System.out.println("expected  " + expected);
		Assert.assertTrue(msip.isErrorPresent(expected));
	}
}
