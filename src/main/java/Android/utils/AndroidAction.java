package Android.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidAction {

	
	public AppiumDriverLocalService service;
	public void scrollToView(String e,AndroidDriver driver) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ e +"\"));"));
	}
	
	public AppiumDriverLocalService startService(String ipAddress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\visha\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		return service;
	}
	
	
	//tochange json file data to string and catch it in our actual test case
	public List<HashMap<String,String>> getdata(String jsonFilePath) throws IOException{
		
		String jsoncontent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
	}
	
	public String getScreenShot(String testName,AppiumDriver driver) throws IOException {
		
		File src = driver.getScreenshotAs(OutputType.FILE);
		String des = System.getProperty("user.dir")+"//Resources"+ testName + ".png";
		File destFile = new File(des);
		FileUtils.copyFile(src, destFile);
		return des;
	}
	
	
}
