package Appium.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Android.utils.AndroidAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Homepage extends AndroidAction {

	AndroidDriver driver;
	public Homepage(AndroidDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(className = "android.widget.TextView")
	private WebElement homeHeader;
	
	@AndroidFindBy(id ="android:id/text1")
	private WebElement countries;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField; 
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement male;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement female;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submit;
	
	public String header() {

		String text = homeHeader.getText();
		return text;
	}
	
	public void selectCountry(String countryName) {
		countries.click();
		scrollToView(countryName, driver);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
		
	}
	
	public void enterName(String name) {
		nameField.sendKeys(name);
		//to hide the keyboard after entering values
		driver.hideKeyboard();
	}
	
	public void selectGender(String gender) {
		if(gender.contains("male"))
			male.click();
		else
			female.click();
	}
	
	public ProductPage submit() {

		 submit.click();
		 return new ProductPage(driver);
		
	}
	
}
