package ru.autotests.webdriver;

import ru.autotests.webdriver.customdrivers.CustomChromeDriver;
import ru.autotests.webdriver.customdrivers.CustomFirefoxDriver;

public class WebDriverManager {

    public enum DriverType {
        CHROME, FIREFOX
    }

    private static DriverType currentDriverType;
    private static CustomWebDriver customWebDriver;
    private static final long TIME_OUT_IN_SECONDS = 8;
    private static final long SLEEP_IN_MILLIS = 250;

    public static CustomWebDriver getWebDriver() {
        if (customWebDriver == null) throw new NullPointerException("Web driver not initialized!");
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
                break;

            case FIREFOX:
                currentDriverType = DriverType.FIREFOX;
                customWebDriver = CustomFirefoxDriver.getInstance();
                break;

            default:
                break;
        }
        customWebDriver.setWebDriverWait(TIME_OUT_IN_SECONDS, SLEEP_IN_MILLIS);
    }

    public static void setupWebDriver(DriverType driverType, long timeOutInSeconds, long sleepInMillis) {
        setupWebDriver(driverType);
        customWebDriver.setWebDriverWait(timeOutInSeconds, sleepInMillis);
    }
}
