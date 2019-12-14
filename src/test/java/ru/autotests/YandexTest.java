package ru.autotests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.autotests.pages.yandex.MainPage;
import ru.autotests.webdriver.WebDriverManager;

import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;
import static ru.autotests.webdriver.WebDriverManager.DriverType.FIREFOX;

public class YandexTest {
    private MainPage mainPage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.setupWebDriver(FIREFOX);
        mainPage = new MainPage();
    }

    @Test
    public void test1() {
        mainPage.open();
        mainPage.setSearchField("услуги по тестированию по");
        mainPage.clickSearchButton();
        mainPage.findPageInResponse("performance-lab", 20);
    }

    @AfterClass
    public void cleanUp(){
        mainPage.closeAllWindows();
    }
}
