package ru.netology.appium;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WayToCrossplatformTests {

    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void checkEmptyInput() {
        Elements elements = new Elements(driver);
        elements.inputField.sendKeys("  ");
        elements.buttonChange.click();
        Assertions.assertEquals("Hello UiAutomator!", elements.textToBeChanged.getText());
    }

    @Test
    public void checkTextInAnotherActivity() {
        Elements elements = new Elements(driver);
        String textInInputField = "netology";
        elements.inputField.sendKeys(textInInputField);
        elements.buttonActivity.click();
        Assertions.assertEquals(textInInputField,elements.textInAnotherActivity.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
