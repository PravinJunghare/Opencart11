package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");

	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains("route=account/login"));

	}

	@Test(priority = 3)
	public void forgotpassLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}

	@Test(priority=Short.MAX_VALUE)
	public void loginTest() throws InterruptedException {
		String actAccpageTitle = loginPage.doLogin("nov11@gmail.com", "1234");
		Assert.assertEquals(actAccpageTitle, "My Account");

	}
}