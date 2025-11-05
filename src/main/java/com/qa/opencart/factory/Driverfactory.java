package com.qa.opencart.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.BrowserException;

public class Driverfactory {
	public WebDriver driver;

	/**
	 * this method is initializing the browser on the basis of browser name
	 * 
	 * @param browserName
	 * @return this returns the driver
	 */
	public WebDriver initDriver(String browserName) {

		// String browserName = prop.getProperty("browser").trim();
		System.out.println("browsername is :" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.trim().equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.trim().equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Please enter the correct browserName" + browserName);
			throw new BrowserException("===Invalid Browser=====" + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

		return driver;

	}

}