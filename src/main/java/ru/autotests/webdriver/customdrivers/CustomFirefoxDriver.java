package ru.autotests.webdriver.customdrivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.autotests.CustomProperty;

import java.util.Properties;

public class CustomFirefoxDriver {

    private static CustomFirefoxDriver customFirefoxDriver;
    private FirefoxDriver driver;
    private static Logger logger = LogManager.getLogger();
    private Properties property = CustomProperty.getInstance().getProperties();

    private CustomFirefoxDriver() {
        logger.info("Инициализирую Firefox Driver");
        System.setProperty("webdriver.gecko.driver", "bin/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        int width = Integer.parseInt(property.getProperty("webdriver.screensize.width"));
        int height = Integer.parseInt(property.getProperty("webdriver.screensize.height"));
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().deleteAllCookies();
    }

    public static CustomFirefoxDriver getInstance() {
        if (customFirefoxDriver == null) customFirefoxDriver = new CustomFirefoxDriver();
        return customFirefoxDriver;
    }

    public FirefoxDriver getDriver() {
        return driver;
    }
}