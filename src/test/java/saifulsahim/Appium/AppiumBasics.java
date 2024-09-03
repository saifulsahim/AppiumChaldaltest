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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		handleSplashScreen(driver, wait);
		//Clicking the search button

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Search\"]")));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Search\"]")).click();

		handleSplashScreen(driver, wait);

		//Search for the item “toothbrush” 
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Search Products\"]"))
				.sendKeys("toothbrush");
		driver.hideKeyboard();
		
		// Scroll down to an item 

		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(20).scrollIntoView(new UiSelector().text(\"Sensodyne Expert Brush\"));")));

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(20).scrollIntoView(new UiSelector().text(\"Sensodyne Expert Brush\"));"));

		WebElement plusButton = driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup"));

		for (int i = 0; i < 3; i++) {
			plusButton.click();

		}

		//Go to the cart screen from the top 
		WebElement cart = wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.xpath("(//android.widget.TextView[@text=\"৳579\"])[2]")));
		cart.click();
	}
	
	private static void handleSplashScreen(AndroidDriver driver, WebDriverWait wait) {
		try {
			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.id("com.chaldal.poached:id/action_bar_root")));
			System.out.println("Splash screen bypassed.");
		} catch (Exception e) {
			System.out.println("No splash screen to handle.");
		}
	}

}
