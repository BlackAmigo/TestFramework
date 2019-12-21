package ru.autotests.basetest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.steps.Steps;
import ru.autotests.webdriver.WebDriverManager;

import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;

public class BaseTest {

    protected Steps steps;

    public BaseTest() {
        steps = new Steps();
    }

    @BeforeSuite
    public void tearDown() {
        WebDriverManager.setupWebDriver(CHROME);
    }

    @AfterSuite
    public void cleanUp() {
        BasePage.initPage(MainPage.class).closeAllWindows();
    }
}
