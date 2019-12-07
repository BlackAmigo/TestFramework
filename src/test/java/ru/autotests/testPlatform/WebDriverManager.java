package ru.autotests.testPlatform;

import ru.autotests.testPlatform.customdriver.CustomChromeDriver;
import ru.autotests.testPlatform.customdriver.CustomFirefoxDriver;

public  class WebDriverManager {

    public enum DriverType {
        CHROME, FIREFOX
    }

    private static DriverType currentDriverType;
    private static CustomWebDriver customWebDriver;

    public static CustomWebDriver getWebDriver(){
        if(customWebDriver == null) throw new NullPointerException("Web driver not initialized!");
        return customWebDriver;
    }

    public static DriverType getCurrentDriverType() {
        return currentDriverType;
    }

    public static void setupWebDriver(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                currentDriverType = DriverType.CHROME;
                customWebDriver = CustomChromeDriver.getInstance();

            case FIREFOX:
                currentDriverType = DriverType.FIREFOX;
                customWebDriver = CustomFirefoxDriver.getInstance();
                break;

            default:break;
        }
    }

    public static void setupWebDriver(DriverType driverType, long timeOutInSeconds , long sleepInMillis) {
        setupWebDriver(driverType);
        customWebDriver.setWebDriverWait(timeOutInSeconds, sleepInMillis);
    }
}
