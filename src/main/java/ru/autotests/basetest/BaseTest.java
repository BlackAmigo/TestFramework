package ru.autotests.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.steps.Steps;
import ru.autotests.webdriver.WebDriverManager;

import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;

public class BaseTest {

    protected Steps steps;
    private static Logger logger = LogManager.getLogger();

    @BeforeSuite
    public void tearDown() {
        WebDriverManager.setupWebDriver(CHROME);
        steps = new Steps();
    }

    @AfterSuite
    public void cleanUp() {
        BasePage.initPage(MainPage.class).closeAllWindows();
        logger.info("\n\n");
    }
}
