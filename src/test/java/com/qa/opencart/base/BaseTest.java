package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {

	Driverfactory df;
	WebDriver driver;
	protected Properties prop;

	protected LoginPage loginPage;
	protected AccountPage accountsPage;

	@BeforeTest
	public void setUp() {
		df = new Driverfactory();// created driver factory object
		// prop=df.initProp();
		driver = df.initDriver("chrome");// to call initDriver method to get driver
		// driver=df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}