package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest {

	@BeforeClass

	public void accPageSetup() {
		// accountsPage = loginPage.doLogin("hacaxa3208@craftapk.com", "Test@1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void searchTest() {
		searchPage = accountsPage.doSearch("airtel");
		int actResultCount = searchPage.getSearchProductCount();
		Assert.assertEquals(actResultCount, 0);
	}

}