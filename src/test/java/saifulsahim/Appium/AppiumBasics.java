package saifulsahim.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.testng.annotations.Test;

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

		//1. Initialize the driver
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

		//2. Open the Chaldal Application
		System.out.println("App launched successfully");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		handleSplashScreen(driver, wait);
		//3. Clicking the search button

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Search\"]")));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Search\"]")).click();

		handleSplashScreen(driver, wait);

		//4. Search for the item “toothbrush”
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Search Products\"]"))
				.sendKeys("toothbrush");
		driver.hideKeyboard();

		//5. Scroll down to an item

		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(20).scrollIntoView(new UiSelector().text(\"Sensodyne Expert Brush\"));")));

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(20).scrollIntoView(new UiSelector().text(\"Sensodyne Expert Brush\"));"));

		WebElement plusButton = driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup"));

		for (int i = 0; i < 3; i++) {
			plusButton.click();

		}

		//6.  Go to the cart screen from the top
		WebElement cart = wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.xpath("(//android.widget.TextView[@text=\"৳579\"])[2]")));
		cart.click();

		//7. Empty the cart

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.TextView[@text=\"\"])[1]")));
		driver.findElement(By.xpath("(//android.widget.TextView[@text=\"\"])[1]")).click();

		//8. Verify text "Nothing to see here" on the cart screen after removing items
		String emptyCartText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Nothing to see here\"]"))
				.getText();
		if (emptyCartText.equals("Nothing to see here")) {
			System.out.println("Test Passed: Cart is empty.");
		} else {
			System.out.println("Test Failed: Cart is not empty.");
		}
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Tap anywhere to resume shopping\"]"))
				.click();

		driver.quit();
		service.stop();
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
