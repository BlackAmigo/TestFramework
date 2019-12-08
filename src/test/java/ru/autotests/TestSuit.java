package ru.autotests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.autotests.pages.yandex.MainPage;
import ru.autotests.webdriver.WebDriverManager;

import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;

public class TestSuit {
    private MainPage mainPage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.setupWebDriver(CHROME);
        mainPage = new MainPage();
    }

    @Test
    public void test1() {
        mainPage.open();
        mainPage.setSearchField("тестированиe по");
        mainPage.clickSearchButton();
        mainPage.findPageInResponse("performance-lab", 20);
    }

    @AfterClass
    public void cleanUp(){
//        mainPage.close;
    }
}
