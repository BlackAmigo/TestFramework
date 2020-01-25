package ru.autotests.pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.autotests.webdriver.CustomWebDriver;
import ru.autotests.webdriver.WebDriverManager;

public abstract class BasePage {

    protected CustomWebDriver driver = WebDriverManager.getWebDriver();
    private static Logger logger = LogManager.getLogger();

    public static <Page extends BasePage> Page initPage(Class<Page> pageClass) {
        return PageFactory.initElements(WebDriverManager.getWebDriver().getCurrentWebDriver(), pageClass);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void closeAllWindows() {
        driver.closeAllWindows();
    }

    public String getText(String xPath) {
        WebElement element = driver.findElementByXPath(xPath);
        return element.getText();
    }

    public static String getCurrentUrl(){
        return WebDriverManager.getWebDriver().getCurrentWebDriver().getCurrentUrl();
    }
}
