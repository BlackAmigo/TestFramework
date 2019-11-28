package ru.autotests;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class WebDriver {

    private static WebDriver webDriver;
    private static ChromeDriver driver;
    private static Logger logger;
    private static WebDriverWait wait;

    public static WebDriver getInstance() {
        if (webDriver == null) webDriver = new WebDriver();
        return webDriver;
    }

    public void close() {
        logger.trace("Закрываю последнее открытое окно");
        driver.close();
    }

    public void quit() {
        logger.trace("Закрываю все окна");
        driver.quit();
    }

    private WebDriver() {
        logger = LogManager.getLogger();
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 8, 250);
    }

    public void get(String url) {
        logger.trace(String.format("Открываю сайт по адресу '%s'", url));
        driver.get(url);
    }

    public void isElementPresent(String xpath) {
        logger.trace(String.format("Проверяю наличие элемента '%s'", xpath));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void switchFocusToPage(String newUrl) {
        String oldUrl = driver.getCurrentUrl();
        if (!oldUrl.equals(newUrl)) {
            logger.trace(String.format("Меняю фокус драйвера на '%s'", newUrl));
            //текущий дескриптор
            String oldDescriptor = driver.getWindowHandle();
            Iterator<String> windowIterator = driver.getWindowHandles().iterator();
            while (windowIterator.hasNext()) {
                String windowHandle = windowIterator.next();
                //меняем дескриптор
                driver.switchTo().window(windowHandle);
                String newDescriptor = driver.getWindowHandle();
                String currentUrl = driver.getCurrentUrl();
                //если дескриптор поменялся и URL дескриптора соответствует запросу
                if ((!oldDescriptor.equals(newDescriptor)) && currentUrl.equals(newUrl)) {
                    break;
                }
            }
        }
    }

    public WebElement findByPartialLinkText(String linkText) {
        logger.trace(String.format("Ищу элемент по локатору '%s'", linkText));
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(linkText)));
    }

    public WebElement findElementByXPath(String xpath) {
        logger.trace(String.format("Ищу элемент по локатору '%s'", xpath));
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

//        ((JavascriptExecutor)driver).executeScript(
//                "arguments[0].scrollIntoView(true);",element);

            Actions scroll = new Actions(driver);
            scroll.moveToElement(element);
            scroll.perform();
        } catch (Exception e) {
            createScreenshot();
        }
        return element;
    }

    private void createScreenshot(){
        logger.trace("Создаю скриншот ошибки");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = String
                    .format("screenshots/%s-scr.jpg", new SimpleDateFormat("dd.MM_HH-mm-ss").format(new Date()));
            FileUtils.copyFile(file, new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
