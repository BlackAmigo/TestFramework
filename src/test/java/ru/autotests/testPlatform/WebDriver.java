package ru.autotests.testPlatform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebDriver{

    private static Logger logger = LogManager.getLogger();
    protected static WebDriverWait wait;
    protected long timeOutInSeconds;
    protected long sleepInMillis;

    public WebDriver() {

    }

    public void get(String url) {
        logger.trace(String.format("Открываю сайт по адресу '%s'", url));
    }

    public static Logger getLogger() {
        return logger;
    }

    public WebDriver setTimeOutInSeconds(long timeOutInSeconds) {
        this.timeOutInSeconds = timeOutInSeconds;
        return this;
    }

    public WebDriver setSleepInMillis(long sleepInMillis) {
        this.sleepInMillis = sleepInMillis;
        return this;
    }
}
