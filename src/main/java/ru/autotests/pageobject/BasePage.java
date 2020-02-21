package ru.autotests.pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.autotests.webdriver.CustomWebDriver;

public abstract class BasePage {

    protected CustomWebDriver customWebDriver = CustomWebDriver.getInstance();
    private static Logger logger = LogManager.getLogger();

    public static <Page extends BasePage> Page initPage(Class<Page> pageClass) {
        return PageFactory.initElements(CustomWebDriver.getInstance().getDriver(), pageClass);
    }

    public void open(String url) {
        customWebDriver.get(url);
    }

    public void closeAllWindows() {
        customWebDriver.closeAllWindows();
    }

    public String getText(String xPath) {
        WebElement element = customWebDriver.findElementByXPath(xPath);
        return element.getText();
    }

    public static String getCurrentUrl(){
        return CustomWebDriver.getInstance().getDriver().getCurrentUrl();
    }
}
