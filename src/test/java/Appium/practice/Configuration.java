package Appium.practice;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Android.utils.AndroidAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Configuration extends AndroidAction{



	public AndroidDriver driver;
	
	@BeforeClass
	public void startServer() throws URISyntaxException, IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\data\\resources\\data.properties");
		prop.load(fis);
		
		//dynamic way of giving ip adress from cmd if not given than it will fetch from json file
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		service = startService(ipAddress,Integer.parseInt(port));
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("deviceName"));
		options.setApp(System.getProperty("user.dir")+"\\Resources\\General-Store.apk");
		driver = new AndroidDriver(service.getUrl(), options);
		
	}
	
	@AfterClass
	public void stopServer() {

		driver.quit();
		service.stop();
	
	}
}
