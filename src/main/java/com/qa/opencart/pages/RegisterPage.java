package com.qa.opencart.pages;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtility;

public class RegisterPage {


	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephoneNo = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	// [normalize-space()-will remove the space in locator]
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean registerUser(String fName, String lName, String telephone, String email,String pwd,
			String subscribe) {

		eleUtil.waitForElementVisible(firstName, AppConstant.DEFAULT_MEDIUM_TIMEOUT).sendKeys(fName);
		eleUtil.doSendKeys(lastName, lName);
		eleUtil.doSendKeys(emailId,email);
		eleUtil.doSendKeys(telephoneNo, telephone);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(confirmpassword, pwd);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doActionsCick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String successMsg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstant.DEFAULT_MEDIUM_TIMEOUT)
				.getText();
		System.out.println("User Registration Message :--->" + successMsg);
		if (successMsg.contains(AppConstant.USER_REG_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}

	}

}