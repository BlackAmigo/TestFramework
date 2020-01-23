package ru.autotests.webdriver.customdrivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.autotests.webdriver.CustomWebDriver;

public class CustomFirefoxDriver extends CustomWebDriver {

    private static CustomFirefoxDriver customFirefoxDriver;
    private FirefoxDriver driver;
    private static Logger logger = LogManager.getLogger();

    private CustomFirefoxDriver() {
        logger.info("Инициализирую Firefox Driver");
        System.setProperty("webdriver.gecko.driver", "bin/geckodriver-v026.exe");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.manage().deleteAllCookies();
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