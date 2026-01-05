package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

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

// Test case Using DataProvider
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "MacBook", "MacBook Air", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 },

		};
	}

	@Test(dataProvider = "getProductImagesTestData")
	public void productImagecountTest(String searchkey, String ProdcutName, int imagecount) {
		searchPage = accountsPage.doSearch(searchkey);
		productInfoPage = searchPage.selectProduct(ProdcutName);
		int actualImagecount = productInfoPage.getImageCount();
		Assert.assertEquals(actualImagecount, imagecount);
	}

	// Test case Using Excel file using Data Provider
	@DataProvider
	public Object[][] getProductexcelImagesTestData() {
		return ExcelUtil.getTestData(AppConstant.PRODUCT_SHEET_NAME);
	};

	@Test(dataProvider = "getProductexcelImagesTestData")
	public void producteExcelImagecountTest(String searchkey, String productname, String imagecount) {
		searchPage = accountsPage.doSearch(searchkey);
		productInfoPage = searchPage.selectProduct(productname);
		int actualImagecount = productInfoPage.getImageCount();
		//Assert.assertEquals(actualImagecount, imagecount);
		//Assert.assertSame(actualImagecount, imagecount);
		Assert.assertEquals(String.valueOf(actualImagecount), (imagecount));
	}

	// Test case Using CSV file using Data Provider
	@DataProvider
	public Object[][] getProductCSVImagesTestData() {
		return CSVUtil.csvData("product");
	};

	@Test(dataProvider = "getProductCSVImagesTestData")
	public void producteCSVImagecountTest(String searchkey, String productname, String imagecount) {
		searchPage = accountsPage.doSearch(searchkey);
		productInfoPage = searchPage.selectProduct(productname);
		int actualImagecount = productInfoPage.getImageCount();
		//Assert.assertEquals(actualImagecount, imagecount);
		Assert.assertEquals(String.valueOf(actualImagecount), (imagecount));
	}

	@Test
	public void productInfoTest() {
		searchPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actualproductInfoMap = productInfoPage.getProductInfo();
		// System.out.println(actualproductInfoMap);

		softAssert.assertEquals(actualproductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualproductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualproductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actualproductInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertAll();

		// Hard Assertions
		/*
		 * Assert.assertEquals(actualproductInfoMap.get("Brand"), "Apple");
		 * Assert.assertEquals(actualproductInfoMap.get("Product Code"), "Product 18");
		 * Assert.assertEquals(actualproductInfoMap.get("productname"), "MacBook Pro");
		 * Assert.assertEquals(actualproductInfoMap.get("productprice"), "$2,000.
		 */
	}

}