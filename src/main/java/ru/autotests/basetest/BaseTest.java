package ru.autotests.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.steps.Steps;
import ru.autotests.testdata.TestData;
import ru.autotests.webdriver.CustomWebDriver;

public class BaseTest {

    protected Steps steps;
    private static Logger logger = LogManager.getLogger();
    public TestData data;

    public BaseTest() {
        data = TestData.getInstance();
    }

    @BeforeSuite
    public void tearDown() {
        CustomWebDriver.getInstance();
        steps = new Steps();
    }

    @AfterSuite
    public void cleanUp() {
        BasePage.initPage(MainPage.class).closeAllWindows();
        logger.info("Test end\n\n");
    }
}
