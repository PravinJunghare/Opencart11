package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstant.DEFAULT_MEDIUM_TIMEOUT;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;
public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	private By ProductImages = By.cssSelector("ul.thumbnails img");
	private By productMetaDeta = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceDeta = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMsg = By.cssSelector("div.alert.alert-success");
	private Map<String, String> productInfoMap;

	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getProductHeaderValue() {
		String productHeaderValue = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header :" + productHeaderValue);
		return productHeaderValue;
	}

	public int getImageCount() {

		int imageCount = eleUtil.waitForElementsVisible(ProductImages,DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("ProdcutImages :" + imageCount);
		return imageCount;
	}
	public void enterQuantity(int qty) {
		System.out.println("Prodcut Quantity :" + qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}

	public String addProductTocart() {
		eleUtil.doClick(addToCartBtn);
		String successMsg = eleUtil.waitForElementVisible(cartSuccessMsg, AppConstant.DEFAULT_MEDIUM_TIMEOUT).getText();
		successMsg = successMsg.substring(0, successMsg.length() - 1).replace("\n", "");

		// As string is immutable so we need to use String builder here

		StringBuilder sb = new StringBuilder(successMsg);
		String message = sb.substring(0, successMsg.length() - 1).replace("\n", "").toString();
		System.out.println("Cart Success Message :" + message);

		return message;
		// Success: You have added MacBook Pro to your shopping cart!
	}

	public Map<String, String> getProductInfo() {
		// productInfoMap = new HashMap<String, String>(); // HashMap:not maintain the
		// order
		productInfoMap = new LinkedHashMap<String, String>(); // LinkedHashMap:maintain the order
		// productInfoMap = new TreeMap<String, String>(); // TreeMap: Sorted/
		// Alphabetical maintain the order

		// header
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productInfoMap);
		return productInfoMap;
	}

	private void getProductMetaData() {
		// Brand: Apple
		// Product Code: Product 18
		// Reward Points: 800
		// Availability: In Stock
		// here values are in form key and value so we are using map

		// METADATA
		List<WebElement> metaList = eleUtil.getElements(productMetaDeta);
		for (WebElement e : metaList) {
			String meta = e.getText();// Brand: Apple
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();// Brand
			String value = metaInfo[1].trim();// Apple
			productInfoMap.put(key, value);
		}

	}

	private void getProductPriceData() {
		// pricedata
		// $2,000.00
		// Ex Tax: $2,000.00
		List<WebElement> priceList = eleUtil.getElements(productPriceDeta);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String extaxVal = exTax.split(":")[1].trim();

		// here no key so we defined custom key
		productInfoMap.put("productprice", price);
		productInfoMap.put("exTax", extaxVal);

	}

}
	