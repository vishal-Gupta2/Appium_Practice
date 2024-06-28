package Appium.practice;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {

	AndroidDriver driver;
	public CartPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private WebElement product;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private WebElement productPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement cartValue;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkTerms;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	
	public String verifyProduct() {
		String productName = product.getText();
		return productName;
	}
	
	public void productPrice() {
		String[] productValue = productPrice.getText().split("$");
		 
	}
	
	public String cartValue() {
		String totalCartValue = cartValue.getText().split("$")[2].trim();
		return totalCartValue;
	}
	
	public void checkTerms() {
		checkTerms.click();
	}
	
	public void proceed() {
		proceed.click();
	}
	
	public void startActivity() throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder("adb", "root");
        Process process = processBuilder.start();
        process.waitFor();
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
		
	}
	
}
