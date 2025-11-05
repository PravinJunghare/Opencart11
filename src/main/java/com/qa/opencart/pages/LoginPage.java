package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	
	//1.Private Webdriver;
	private WebDriver driver;

	
	// 2. Private By locators
		private final By username = By.id("input-email");
		private final By password = By.id("input-password");
		private final By loginBtn = By.xpath("//input[@value='Login']");
		private final By forgottenPwdlink = By.id("input-email");
		private final By registerlink = By.linkText("Register");

		// 3. Page Constructor
		public LoginPage(WebDriver driver) {
			this.driver = driver;

		}

		// 4.Page Actions/Methods

		public String getLoginPageTitle() {
			String title = driver.getTitle();
			return title;
		}

		public String getLoginUrl() {
			String url = driver.getCurrentUrl();
			return url;
		}

		public boolean isForgotpwdLinkExits() {
			return driver.findElement(forgottenPwdlink).isDisplayed();
		}

		public boolean isregistetLinkExits() {
			return driver.findElement(registerlink).isDisplayed();

		}

		public String doLogin(String un, String pwd) throws InterruptedException {
			driver.findElement(username).sendKeys(un);
			driver.findElement(password).sendKeys(pwd);
			driver.findElement(loginBtn).click();
			Thread.sleep(5000);

			return driver.getTitle();

		}

	}


