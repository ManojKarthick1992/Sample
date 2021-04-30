package test.LG;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class StepDefs {
	WebDriver driver;
	String email = "";
	String userID = "";
	String domain = "";

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Given("User navigates to Email Site and generates random mail")
	public void generateRandomMail() throws Exception {
		String homePath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", homePath + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(35, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
		driver.get("https://emailfake.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		@SuppressWarnings("unused")
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		// waiting for the element and fetches the email ID from WebSite
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text'][@id='userName']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text'][@id='userName']")));
		Thread.sleep(5000);
		userID = driver.findElement(By.xpath("//input[@type='text'][@id='userName']")).getAttribute("value");
		System.out.println(userID);
		domain = driver.findElement(By.xpath("//input[@type='text'][@id='domainName2']")).getAttribute("value");
		System.out.println(userID);
		email = userID + "@" + domain;
		System.out.println(email);

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@And("User navigates to LG Registration SignUp Page and enters all of required details")
	public void navigateLGRegistration() throws InterruptedException {
		driver.get("https://sso.us.lg.com/oauth/page/signup?authorizeKey=68a10091-1149-4f7c-893c-a7160755a974");
		@SuppressWarnings("rawtypes")
		FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(35, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
		System.out.println(email);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='txtBoxPassword']")));
		driver.findElement(By.xpath("//input[@id='txtBoxPassword']")).sendKeys("Admin@123");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='txtBoxPasswordConfirm']")));
		driver.findElement(By.xpath("//input[@id='txtBoxPasswordConfirm']")).sendKeys("Admin@123");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='txtBoxFName']")));
		driver.findElement(By.xpath("//input[@id='txtBoxFName']")).sendKeys("Mike");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='txtBoxLName']")));
		driver.findElement(By.xpath("//input[@id='txtBoxLName']")).sendKeys("Nelson");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='calendar view']")));
		driver.findElement(By.xpath(" //button[text()='calendar view']")).click();
		Thread.sleep(1000);
		Select year = new Select(driver.findElement(By.xpath("//select[@aria-label='Year']")));
		year.selectByValue("1976");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='1']")));
		driver.findElement(By.xpath("//a[text()='1']")).click();
		Thread.sleep(1000);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@And("Selects the Terms and conditions for Account Creation")
	public void selectsCondition() throws InterruptedException {
		@SuppressWarnings("rawtypes")
		FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(35, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='checkbox-btn'])[2]")));
		driver.findElement(By.xpath("(//span[@class='checkbox-btn'])[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='checkbox-btn'])[3]")));
		driver.findElement(By.xpath("(//span[@class='checkbox-btn'])[3]")).click();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@When("Clicks on Signup Button")
	public void clickSignup() throws InterruptedException {
		@SuppressWarnings("rawtypes")
		FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(35, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='SIGN UP']")));
		driver.findElement(By.xpath("//button[text()='SIGN UP']")).click();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Then("LG Account should be created with provided email ID")
	public void checkEmailCreate() throws InterruptedException {
		@SuppressWarnings("rawtypes")
		FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(35, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text em']/strong")));
		String actualEmail = driver.findElement(By.xpath("//p[@class='text em']/strong")).getText().trim();
		Assertions.assertEquals(email, actualEmail, "LG Account creation success Message not displayed");
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@And("Activate the account from Mailbox")
	public void activateAccount() throws InterruptedException {
		driver.get("https://emailfake.com/");
		FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(35, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text'][@id='userName']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text'][@id='userName']")));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='VERIFY ACCOUNT']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='VERIFY ACCOUNT']")));
		driver.findElement(By.xpath("//a[text()='VERIFY ACCOUNT']")).click();
		Thread.sleep(3000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		if (tabs2.size() > 1) {
			driver.switchTo().window(tabs2.get(1));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='txt-wrap']/h1")));
			String actualMsg = driver.findElement(By.xpath("//div[@class='txt-wrap']/h1")).getText().trim();
			Assertions.assertEquals("Verification Complete", actualMsg, "Account verification Message not Displayed");
		} else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='txt-wrap']/h1")));
			String actualMsg = driver.findElement(By.xpath("//div[@class='txt-wrap']/h1")).getText().trim();
			Assertions.assertEquals("Verification Complete", actualMsg, "Account verification Message not Displayed");
		}
		driver.quit();
	}

}
