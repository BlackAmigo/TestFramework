package ru.autotests.webdriver.customdrivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.autotests.CustomProperty;
import java.util.Properties;

public class CustomChromeDriver {

    private static CustomChromeDriver customChromeDriver;
    private ChromeDriver driver;
    private static Logger logger = LogManager.getLogger();
    private Properties property = CustomProperty.getInstance().getProperties();

    private CustomChromeDriver() {
        logger.info("Инициализирую Chrome Driver");
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        int width = Integer.parseInt(property.getProperty("webdriver.screensize.width"));
        int height = Integer.parseInt(property.getProperty("webdriver.screensize.height"));
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().deleteAllCookies();
    }

    public static CustomChromeDriver getInstance() {
        if (customChromeDriver == null) customChromeDriver = new CustomChromeDriver();
        return customChromeDriver;
    }

    public ChromeDriver getDriver() {
        return driver;
    }
}
