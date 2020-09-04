package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MaxSignInPage extends BasePage {

	@FindBy(css = "input#j_username")
	private WebElement email;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(id = "signin-form-submit")
	private WebElement submit;

	@FindBy(xpath = "//div[@id='j_password-error']/span")
	private WebElement passwordError;

	@FindBy(xpath = "//div[@id='j_username-error']/span")
	private WebElement emailError;

	@FindBy(xpath = "//legend[@class='MuiFormLabel-root Mui-error'][text()]")
	private WebElement invalidEmailPassword;

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(email);
	}

	public MaxSignInPage doSignInAsInvalidUser(String emailValue, String passwordValue) {
		userActions(email, "textbox", emailValue);
		userActions(password, "textbox", passwordValue);

		userActions(submit, "button", "");
		return this;

	}

	public boolean isErrorPresent(String errorExpected) {

		boolean flag =false;
		//errorExpected.
		switch (errorExpected) {
		case "Enter your email address and Enter Your Password":
			//System.out.println(getPassEmailBlankMsgs().contains(errorExpected.substring(0,errorExpected.indexOf("and")-1)));
			//System.out.println(getPassEmailBlankMsgs().contains(errorExpected.substring(errorExpected.indexOf("and")+3))); 
			//System.out.println(errorExpected.substring(errorExpected.indexOf("and")+4));
			List list=getPassEmailBlankMsgs();
			if(list.contains(errorExpected.substring(0,errorExpected.indexOf("and")-1)) && 
					 list.contains(errorExpected.substring(errorExpected.indexOf("and")+4)))
				 flag=true;
			 break;
		case "Your Username or Password is incorrect":
			//System.out.println("Your Username or Password is incorrect");
			flag=getInvalidPassEmailMsg().equalsIgnoreCase(errorExpected);
			break;
		case "Enter your email address":
			//System.out.println("Enter your email address");
			flag=getEmailBlankMsg().equalsIgnoreCase(errorExpected);
			break;
		case "Enter Your Password":
			//System.out.println("Enter Your Password");
			flag=getPassBlankMsg().equalsIgnoreCase(errorExpected);
		}
   return flag;
	}

	public String getPassBlankMsg() {
		return getMessage(passwordError, "validation","");
	}

	public String getEmailBlankMsg() {
		return getMessage(emailError, "validation","");
	}

	public String getInvalidPassEmailMsg() {
		return getMessage(invalidEmailPassword, "validation","");
	}

	public List<String> getPassEmailBlankMsgs()
	{
		List<String> list=new ArrayList<String>();
		list.add(getMessage(passwordError, "validation",""));
		list.add(getMessage(emailError, "validation",""));
		return list;
	}
	public MaxHomePage doSignInAsValidUser(String emailValue, String passwordValue) {
		userActions(email, "textbox", emailValue);
		userActions(password, "textbox", passwordValue);
		userActions(submit, "button", "");
		return new MaxHomePage();
	}

}
