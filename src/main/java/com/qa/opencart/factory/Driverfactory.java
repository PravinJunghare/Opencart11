package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.BrowserException;
import com.qa.opencart.exception.FrameworkException;

public class Driverfactory {
	public WebDriver driver;
	public Properties prop;
	OptionManager optionManger;
	public static String isHighlight;

	/**
	 * this method is initializing the browser on the basis of browser name
	 * 
	 * @param browserName
	 * @return this returns the driver
	 */
	// public WebDriver initDriver(String browserName)

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		System.out.println("browsername is :" + browserName);
		optionManger = new OptionManager(prop);
		isHighlight = prop.getProperty("highlight");

		if (browserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDrive()
			driver = new ChromeDriver(optionManger.getChromeOptions());
		} else if (browserName.trim().equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(optionManger.getFirefoxOptions());
		} else if (browserName.trim().equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionManger.getEdgeOptions());
		} else {
			System.out.println("Please enter the correct browserName" + browserName);
			throw new BrowserException("===Invalid Browser=====" + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.get(prop.getProperty("url"));

		return driver;

	}

	/**
	 * This method is reading properties from properties file
	 * 
	 * @return
	 */
	// mvn clean install -Denv="Qa"
	public Properties initProp()

	{

		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop=new Properties();
		try {
			if (envName == null) {
				System.err.println("env is null ,hence running on QA env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} else {
				System.out.println("Running test cases on environment  :" + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				default:
					throw new FrameworkException("==INVALID ENVIRONMENT NAME :"+envName);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}