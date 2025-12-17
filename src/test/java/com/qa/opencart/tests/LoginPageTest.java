package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginPageTest extends BaseTest {

	@Feature("F01: Opencart- login feature ")
	@Epic("Epic 01:Design pages for opencart application")
	@Story("US 01:Design LoginPage for Opencart application")

	@Severity(SeverityLevel.MINOR)
	@Description("Checking Login Page Title....")
	@Owner("PJ")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking LoginPage Title");
		Assert.assertEquals(actTitle, AppConstant.LOGIN_PAGE_TITLE_VALUE);

	}

	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains(AppConstant.LOGIN_PAGE_URL_FRACTION_VALUE));

	}

	@Severity(SeverityLevel.MINOR)
	@Test(priority = 3)
	public void forgotpassLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Short.MAX_VALUE, enabled = true) // enable true or false to run particular test
	public void loginTest() {
		// String actAccpageTitle = loginPage.doLogin("nov11@gmail.com", "1234");
		AccountsPage accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actAccpageTitle = accountsPage.getAccPageTitle();
		Assert.assertEquals(actAccpageTitle, AppConstant.ACCOUNTS_PAGE_TITLE_VALUE);

	}
}