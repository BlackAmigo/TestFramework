package ru.autotests.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import ru.autotests.webdriver.CustomWebDriver;
import ru.autotests.webdriver.WebDriverManager;

public abstract class BasePage {

    protected CustomWebDriver driver = WebDriverManager.getWebDriver();
    protected Logger logger = CustomWebDriver.getLogger();

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

}
