
package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstant.*;// 

import java.util.List;
public class AccountsPageTest extends BaseTest {
	// Precondition is that user should be login for AccountsPage

	@BeforeClass

	public void accPageSetup() {
		// accountsPage = loginPage.doLogin("hacaxa3208@craftapk.com", "Test@1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void accountsPageTitleTest() {
		String actTitle = accountsPage.getAccPageTitle();
		// Assert.assertEquals(actTitle, "My Account");
		Assert.assertEquals(actTitle,ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test
	public void accountsPageUrlTest() {
		String actUrl = accountsPage.getAccPageUrl();
		// Assert.assertTrue(actUrl.contains(""));
		Assert.assertTrue(actUrl.contains(ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	@Test
	public void accountHeaderCountTest() {
		List<String> actualAccHeaderList = accountsPage.getAccountPageHeadersList();
		System.out.println(actualAccHeaderList);
		// Assert.assertEquals(actualAccHeaderList.size(), 4);
		Assert.assertEquals(actualAccHeaderList.size(),ACCOUNTS_PAGE_HEADERCOUNT);
	}

	@Test
	public void accountHeaderValueTest() {
		List<String> actualAccHeaderList = accountsPage.getAccountPageHeadersList();
		//System.out.println("Expected AccPage header list " + EXPECTED_ACCOUNTPAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccHeaderList, EXPECTED_ACCOUNTPAGE_HEADERS_LIST);
	}






}
