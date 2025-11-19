package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstant.*;// Static Import:To remove Writing AppConstant. each time

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By locators
	private final By username = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgottenPwdlink = By.id("input-email");
	private final By registerlink = By.linkText("Register");

	// 2. Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	// 3.Page Actions/Methods

	// 3.Page Actions/Methods

		public String getLoginPageTitle() {
			// String title = driver.getTitle();
			String title = eleUtil.waitForTitleContainsAndFetch(DEFAULT_SHORT_TIMEOUT,LOGIN_PAGE_TITLE_VALUE);
			return title;
		}

		public String getLoginUrl() {
			// String url = driver.getCurrentUrl();
			String url = eleUtil.waitForURLContainsAndFetch(DEFAULT_MEDIUM_TIMEOUT,LOGIN_PAGE_URL_FRACTION_VALUE);
			return url;
		}

		public boolean isForgotpwdLinkExits() {

			// return driver.findElement(forgottenPwdlink).isDisplayed();
			return eleUtil.doElementIsDisplayed(forgottenPwdlink);

		}

		public boolean isregistertLinkExits() {
			// return driver.findElement(registerlink).isDisplayed();
			return eleUtil.doElementIsDisplayed(registerlink);

		}

		public AccountsPage doLogin(String un, String pwd) {
			// driver.findElement(username).sendKeys(un);
			// driver.findElement(password).sendKeys(pwd);
			// driver.findElement(loginBtn).click();

			eleUtil.waitForElementVisible(username,DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
			eleUtil.doSendKeys(password, pwd);
			eleUtil.doClick(loginBtn);
			return new AccountsPage(driver);

		}
		public RegisterPage navigateToRegisterPage() {
			{
				//eleUtil.doClick(registerlink);
				eleUtil.clickWhenReady(DEFAULT_MEDIUM_TIMEOUT, registerlink);
				return new RegisterPage(driver);
			}
		}
		

	}