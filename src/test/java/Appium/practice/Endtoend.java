package Appium.practice;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Endtoend extends Configuration{
	


	@Test(dataProvider="getData")
	public void Test(HashMap<String,String> input) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Homepage home = new Homepage(driver);
		home.selectCountry(input.get("countryName"));
		home.enterName(input.get("name"));
		home.selectGender(input.get("gender"));
		ProductPage product = home.submit();
		product.selectProduct(input.get("productName"));
		CartPage cart = product.goToCart();
		String cartProductName = cart.verifyProduct();
		Assert.assertEquals(cartProductName, input.get("productName"));
		//String productValue = cart.productPrice();
		//String cartValue = cart.cartValue();
		//Assert.assertEquals(productValue, cartValue);
		cart.checkTerms();
		cart.proceed();
		
	}
	
	@BeforeMethod
	public void startActivity() throws InterruptedException, IOException {
		CartPage cart = new CartPage(driver);
		cart.startActivity();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//System.getProperty("user.dir")+"\\Resources\\data.json"
		List<HashMap<String,String>> data = getdata(System.getProperty("user.dir")+"\\Resources\\data.json");
		//return new Object[][] {{"Vishal Gupta","female","Bhutan","Jordan Lift Off"},{"tushar mittal","male","Argentina","Jordan Lift Off"}};	
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
