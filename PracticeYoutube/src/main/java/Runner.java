import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.Level;
import org.openqa.selenium.remote.DesiredCapabilities;

import api.android.Android;
import core.ADB;
import core.MyLogger;
import core.UiObject;
import core.UiSelector;


public class Runner {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		AndroidDriver driver = null;
		 File appDir = new File("E:\\Banti\\WorkPrep\\Selenium practice\\apk");
	     File app = new File(appDir, "Amazon Shopping_8.7.0.100.apk");
		//MyLogger.log.setLevel(Level.INFO);		
		//new UiSelector().resourceId("hello").text("item5").makeUiObject();
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
	        cap.setCapability("platformName", "Android");
	        cap.setCapability("deviceName", "4d52b3da");
	        cap.setCapability("platformVersion", "5.0.1");
	        cap.setCapability("app", app.getAbsolutePath());
	        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	        Android.driver = driver;
	        Thread.sleep(5000);
	        ADB adb = new ADB("4d52b3da");
	        adb.openAppsActivity("com.amazon.mShop.android.shopping", "com.amazon.mShop.splashscreen.StartupActivity");
	        
	        UiObject testApiButton = new UiSelector().resourceId("com.amazon.mShop.android.shopping:id/sign_in_button").makeUiObject();
	        
		}finally{
			if(driver != null) driver.quit();
		}
	}

}
