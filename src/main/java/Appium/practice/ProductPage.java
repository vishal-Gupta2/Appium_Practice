package Appium.practice;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Android.utils.AndroidAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends AndroidAction{

	AndroidDriver driver;
	public ProductPage(AndroidDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> products;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	
	public void selectProduct(String productName) {
		scrollToView(productName, driver);
		
		for(int i=0;i<products.size();i++) {
			if(products.get(i).getText().contains("Jordan Lift Off")){
				addToCart.get(i).click();
			}
		}
	}
	
	public CartPage goToCart() {
		cartButton.click();
		return new CartPage(driver);
	}
}
