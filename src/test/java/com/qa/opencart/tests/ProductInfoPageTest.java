package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass

	public void producInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void productHeaderTest() {
		searchPage = accountsPage.doSearch("macBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		String actualHeader = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actualHeader, "MacBook Pro");
	}

	@Test()
	public void productImagecountTest() {
		searchPage = accountsPage.doSearch("macBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		int actualImagecount = productInfoPage.getImageCount();
		Assert.assertEquals(actualImagecount, 4);
	}

}