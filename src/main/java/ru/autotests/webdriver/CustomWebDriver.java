package ru.autotests.webdriver;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    public void setImplicitlyWait(long timeOutInSeconds) {
        getWebDriver().manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
    }

    public void get(String url) {
        logger.trace(String.format("Открываю сайт по адресу '%s'", url));
        getWebDriver().get(url);
    }

    public void close() {
        logger.trace("Закрываю последнее открытое окно");
        getWebDriver().close();
    }

    public void closeAllWindows() {
        logger.trace("Закрываю все окна");
        getWebDriver().quit();
    }

    public WebElement waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void switchFocusToPage(String newUrl) {
        String oldUrl = getWebDriver().getCurrentUrl();
        if (!oldUrl.equals(newUrl)) {
            logger.trace(String.format("Меняю фокус драйвера на '%s'", newUrl));
            // текущий дескриптор
            String oldDescriptor = getWebDriver().getWindowHandle();
            Set<String> windowsList = getWebDriver().getWindowHandles();
            for (String windowHandle : windowsList) {
                // меняем дескриптор
                getWebDriver().switchTo().window(windowHandle);
                String newDescriptor = getWebDriver().getWindowHandle();
                // текущий URL
                String currentUrl = getWebDriver().getCurrentUrl();
                // если дескриптор поменялся и URL дескриптора соответствует запросу
                if ((!oldDescriptor.equals(newDescriptor)) && currentUrl.equals(newUrl)) {
                    break;
                }
            }
            if (getWebDriver().getCurrentUrl().equals(newUrl))
                logger.trace(String.format("Фокус драйвера изменился на '%s'", newUrl));
            else {
                logger.trace("Фокус драйвера НЕ изменился!");
            }
        }
    }

    public WebElement findByPartialLinkText(String linkText) {
        logger.trace(String.format("Ищу элемент по тексту ссылки '%s'", linkText));
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(linkText)));
    }

    public List<WebElement> findElementsByXPath(String xpath) {
        logger.trace(String.format("Ищу список элементов по локатору '%s'", xpath));
        List<WebElement> elements = null;
        try {
            elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (Exception e) {
            createScreenshot();
            e.printStackTrace();
        }
        return elements;
    }

    public WebElement findElementByXPath(String xpath) {
        logger.trace(String.format("Ищу элемент по локатору '%s'", xpath));
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

//        ((JavascriptExecutor)driver).executeScript(
//                "arguments[0].scrollIntoView(true);",element);

            Actions scroll = new Actions(getWebDriver());
            scroll.moveToElement(element);
            scroll.perform();
        } catch (Exception e) {
            createScreenshot();
            e.printStackTrace();
        }
        return element;
    }

    private void createScreenshot() {
        logger.trace("Создаю скриншот ошибки");
        File file = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = String
                    .format("screenshots/%s-scr.jpg", new SimpleDateFormat("dd.MM_HH-mm-ss").format(new Date()));
            FileUtils.copyFile(file, new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
