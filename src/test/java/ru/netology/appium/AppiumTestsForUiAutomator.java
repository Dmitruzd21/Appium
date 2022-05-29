package ru.netology.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppiumTestsForUiAutomator {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\79168\\Desktop\\SoftwareTestingEngineer\\MobileHomeWorks\\AndroidStudioProjects\\2.2 UI Automator\\sample\\app\\build\\intermediates\\apk\\debug\\app-debug.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void checkEmptyInput() {
        MobileElement inputField = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        inputField.sendKeys("  ");
        MobileElement buttonChange = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        buttonChange.click();
        MobileElement textToBeChanged = (MobileElement) driver.findElementById("textToBeChanged");
        Assertions.assertEquals("Hello UiAutomator!", textToBeChanged.getText());
    }

    @Test
    public void checkTextInAnotherActivity() {
        String textInInputField = "netology";
        MobileElement inputField = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        inputField.sendKeys(textInInputField);
        MobileElement buttonActivity = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        buttonActivity.click();
        MobileElement textInAnotherActivity = (MobileElement) driver.findElementById("text");
        Assertions.assertEquals(textInInputField,textInAnotherActivity.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
