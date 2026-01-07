package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstant {

	// TIMEOUT
	public static final int DEFAULT_SHORT_TIMEOUT = 5;
	public static final int DEFAULT_MEDIUM_TIMEOUT = 10;
	public static final int DEFAULT_LONG_TIMEOUT = 20;

	//********* PAGE CONSTAENTS*****************
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";

	public static final String ACCOUNTS_PAGE_TITLE_VALUE = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION_VALUE = "route=account/account";
	public static final int ACCOUNTS_PAGE_HEADERCOUNT = 4;
	
	public static final List<String> EXPECTED_ACCOUNTPAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");
	public static final String USER_REG_SUCCESS_MSG = "Your Account Has Been Created";
	
	
	//*************SHEET NAMES**************************
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_SHEET_NAME = "product";
}