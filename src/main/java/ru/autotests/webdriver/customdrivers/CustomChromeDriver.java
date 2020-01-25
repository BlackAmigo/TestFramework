package ru.autotests.webdriver.customdrivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.autotests.webdriver.CustomWebDriver;

import java.util.HashMap;
import java.util.Map;

public class CustomChromeDriver extends CustomWebDriver {

    private static CustomChromeDriver customChromeDriver;
    private ChromeDriver driver;
    private static Logger logger = LogManager.getLogger();

    private CustomChromeDriver() {
        logger.info("Инициализирую Chrome Driver");
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().deleteAllCookies();
    }

    public static CustomChromeDriver getInstance() {
        if (customChromeDriver == null) customChromeDriver = new CustomChromeDriver();
        return customChromeDriver;
    }

    @Override
    public WebDriver getCurrentWebDriver() {
        return driver;
    }
}
