package saifulsahim.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.testng.annotations.Test;
//import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppiumBasics {
	@Test

	public void ChalDalAutomation() throws MalformedURLException, InterruptedException {
		
		// Setting up and starting the Appium server programmatically
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\Sahim\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Honor LLY-LX2");
		options.setApp("C:\\Users\\Sahim\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\Chaldal.apk");
		options.setAutoGrantPermissions(true);
		
		// Initialize the driver
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Open the Chaldal Application
        System.out.println("Application launched successfully");

		
	}

}
