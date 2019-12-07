package ru.autotests.testPlatform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class CustomWebDriver {

    private static Logger logger = LogManager.getLogger();
    protected WebDriverWait wait;

    public abstract WebDriver getWebDriver();

    public static Logger getLogger() {
        return logger;
    }

    public void setWebDriverWait(long timeOutInSeconds, long sleepInMillis) {
        wait = new WebDriverWait(getWebDriver(), timeOutInSeconds, sleepInMillis);
    }

    public void isElementPresent(String xpath) {
        logger.trace(String.format("Проверяю наличие элемента '%s'", xpath));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void get(String url) {
        logger.trace(String.format("Открываю сайт по адресу '%s'", url));
        getWebDriver().get(url);
    }

    public void close() {
        logger.trace("Закрываю последнее открытое окно");

    }

    public void quit() {
        logger.trace("Закрываю все окна");
    }
}
