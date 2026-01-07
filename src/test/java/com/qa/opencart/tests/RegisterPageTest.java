package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	@BeforeClass
	public void regPagesetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	public static String getRandomEmail() {
		// *********By using Random class for unique email genration**********
		// Random random = new Random();
		// String email = "automationtest" + random.nextInt(1000) + "@gmail.com";

		// By using System.curretTimeMillSec()//Permanat solution
		String email = "automationtest" + System.currentTimeMillis() + "@opencart.com";
		return email;
	}

	// Case 3: Register Multiple user Using Data Provider Fetching Data From Excel and unique email generation
	@DataProvider
	public Object[][] getRegExcelTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstant.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegExcelTestData")
	public void userRegTest(String fName, String lName, String telephone, String password,
			String subscribe) {
		registerPage.registerUser(fName, lName, getRandomEmail(), telephone, password, subscribe);
	}

	// Case 2: Register Multiple user Using Data Provider Hard coded Data
	/*
	 * @DataProvider public Object[][] getUserRegTestData() { return new Object[][]
	 * { { "test", "user", "test@gmail.com", "9020202020", "testww@123", "yes" }, {
	 * "test", "user1", "usera1@1231111.com", "9020202020", "testww@123", "yes" }, {
	 * "test2", "user2", "useraa2@1231111.com", "9020202020", "testww@123", "yes" }
	 * }; }
	 * 
	 * @Test(dataProvider = "getUserRegTestData") public void userRegTest(String
	 * fName, String lName, String emailId, String telephone, String password,
	 * String subscribe) { registerPage.registerUser(fName, lName, emailId,
	 * telephone, password, subscribe); }
	 * 
	 * 
	 * 
	 * 
	 * /* Case 1: Register Single User hard coded Data
	 * 
	 * @Test() public void userRegTest() { //registerPage.registerUser("test",
	 * "user", "user@1231111.com","9020202020","testww@123", "yes"); }
	 */

}
