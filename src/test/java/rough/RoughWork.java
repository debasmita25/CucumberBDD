package rough;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoughWork {
	static WebDriver driver;
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "F:\\Selenium PreRequisites\\executables\\chromedriver.exe");
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		option.addArguments("--disable-extensions");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.maxfashion.in/");
		
		/*
		 * driver.findElement(By.cssSelector("input[type='search']")).
		 * sendKeys("jackets in kids"); driver.findElement(By.xpath(
		 * "//form//div[contains(text(),'Departments')]/../../div[last()]")).click();
		 * System.out.println(driver.findElement(By.xpath("//h1")).getText());
		 * List<WebElement> catgs=driver.findElements(By.xpath(
		 * "//button[@id='price-range-selector']/../..//button//div[text()]"));
		 * WebDriverWait wait=new WebDriverWait(driver,30);
		 * wait.until(ExpectedConditions.visibilityOfAllElements(catgs));
		 * ////button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained
		 * jss675 MuiButton-containedSecondary'] for(WebElement catg:catgs) {
		 * System.out.println(catg.getText()); }
		 */
	    
	
	}	
}
