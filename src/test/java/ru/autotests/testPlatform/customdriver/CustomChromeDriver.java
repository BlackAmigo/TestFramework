package ru.autotests.testPlatform.customdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.autotests.testPlatform.CustomWebDriver;

public class CustomChromeDriver extends CustomWebDriver {

    private static CustomChromeDriver customChromeDriver;
    private ChromeDriver driver;

    private CustomChromeDriver() {
        getLogger().info("Инициализирую Chrome Driver");
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public static CustomChromeDriver getInstance() {
        if (customChromeDriver == null) customChromeDriver = new CustomChromeDriver();
        return customChromeDriver;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }
}
