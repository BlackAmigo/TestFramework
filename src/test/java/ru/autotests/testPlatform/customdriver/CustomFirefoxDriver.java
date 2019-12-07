package ru.autotests.testPlatform.customdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.autotests.testPlatform.CustomWebDriver;

public class CustomFirefoxDriver extends CustomWebDriver {

    private static CustomFirefoxDriver customFirefoxDriver;
    private FirefoxDriver driver;

    private CustomFirefoxDriver() {
        getLogger().info("Инициализирую Firefox Driver");
        System.setProperty("webdriver.gecko.driver", "bin/geckodriver-v026.exe");
        FirefoxOptions options = new FirefoxOptions();

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    public static CustomFirefoxDriver getInstance() {
        if (customFirefoxDriver == null) customFirefoxDriver = new CustomFirefoxDriver();
        return customFirefoxDriver;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }
}