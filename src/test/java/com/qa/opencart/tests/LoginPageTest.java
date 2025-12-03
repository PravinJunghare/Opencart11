package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.pages.AccountsPage;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstant.LOGIN_PAGE_TITLE_VALUE);

	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains(AppConstant.LOGIN_PAGE_URL_FRACTION_VALUE));

	}

	@Test(priority = 3)
	public void forgotpassLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}

	@Test(priority = Short.MAX_VALUE,enabled = true)// enable true or false to run particular test
	public void loginTest()  {
		// String actAccpageTitle = loginPage.doLogin("nov11@gmail.com", "1234");
		AccountsPage accountsPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actAccpageTitle=accountsPage.getAccPageTitle();
		Assert.assertEquals(actAccpageTitle, AppConstant.ACCOUNTS_PAGE_TITLE_VALUE);

	}
}