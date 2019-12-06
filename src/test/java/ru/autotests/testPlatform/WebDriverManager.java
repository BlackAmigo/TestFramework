package ru.autotests.testPlatform;

import ru.autotests.testPlatform.customdriver.CustomChromeDriver;

public class WebDriverManager {

    public enum DriverType {
        CHROME, FIREFOX
    }

    private static WebDriverManager webDriverManager;
    private static DriverType currentDriverType;
    private static WebDriver driver;

    public static WebDriverManager getInstance() {
        if (webDriverManager == null) webDriverManager = new WebDriverManager();
        return webDriverManager;
    }

    public static WebDriver setDriver(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                currentDriverType = DriverType.CHROME;
                return driver = CustomChromeDriver.getInstance();
//            case FIREFOX: return null;
            default:
                return null;
        }
    }



    public WebDriver getDriver() {
        return driver;
    }

    public static DriverType getCurrentDriverType() {
        return currentDriverType;
    }
}
