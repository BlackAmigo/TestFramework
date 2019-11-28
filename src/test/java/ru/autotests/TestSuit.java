package ru.autotests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.autotests.yandex.MainPage;

public class TestSuit {
    private MainPage mainPage;

    @BeforeClass
    public void setUp(){
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
        mainPage.closeAll();
    }
}
