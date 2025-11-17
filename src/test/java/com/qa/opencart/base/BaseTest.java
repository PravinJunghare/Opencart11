package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	Driverfactory df;
	WebDriver driver;
	protected Properties prop;


	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultPage searchPage;
	protected ProductInfoPage productInfoPage;


	protected SoftAssert softAssert;
	// protected RegistrationPage registrationPage;

	@BeforeTest
	public void setUp() {
		df = new Driverfactory();// created driver factory object
		prop = df.initProp();// df.initProp() will give prop reference
		// driver = df.initDriver("chrome");// to call initDriver method to get driver
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}