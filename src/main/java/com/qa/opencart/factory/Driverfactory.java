package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exception.BrowserException;
import com.qa.opencart.exception.FrameworkException;

public class Driverfactory {
	public WebDriver driver;
	public Properties prop;
	OptionManager optionManger;
	public static String isHighlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = LogManager.getLogger(Driverfactory.class);

	/**
	 * this method is initializing the browser on the basis of browser name
	 * 
	 * @param browserName
	 * @return this returns the driver
	 */
	// public WebDriver initDriver(String browserName)

	public WebDriver initDriver(Properties prop) {
		log.info("Properties" + prop);
		ChainTestListener.log("Properties used :" + prop.toString());

		String browserName = prop.getProperty("browser").trim();
		// System.out.println("browsername is :" + browserName);
		log.info("browser name" + browserName);
		optionManger = new OptionManager(prop);
		isHighlight = prop.getProperty("highlight");

		if (browserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDrive()

			// ThradLocal Concept is used as require static driver in screenshot method so
			// we created Threadlocal variable
			tlDriver.set(new ChromeDriver(optionManger.getChromeOptions()));

			// driver = new ChromeDriver(optionManger.getChromeOptions());
		} else if (browserName.trim().equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver(optionManger.getFirefoxOptions()));
			// driver = new FirefoxDriver(optionManger.getFirefoxOptions());
		} else if (browserName.trim().equalsIgnoreCase("edge")) {
			tlDriver.set(new EdgeDriver(optionManger.getEdgeOptions()));
			// driver = new EdgeDriver(optionManger.getEdgeOptions());
		} else {
			// System.out.println("Please enter the correct browserName" + browserName);
			log.error("Please Pass the vaild browserName" + browserName);
			throw new BrowserException("===Invalid Browser=====" + browserName);
		}

		// .manage().deleteAllCookies();
		// driver.manage().window().maximize();
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		// ******Replaced driver with get driver
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * getDriver: get the local thready copy of the driver
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();
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
		prop = new Properties();
		try {
			if (envName == null) {
				// System.out.println("env is null ,hence running on QA env");
				log.warn("env is null ,hence running on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} else {
				System.out.println("Running test cases on env  :" + envName);
				log.info("Running test cases on environment  : + envName");
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
					log.error("---Invalid Environment Name" + envName);
					throw new FrameworkException("==INVALID ENVIRONMENT NAME :" + envName);
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

	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}