package ru.autotests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;
import ru.autotests.pageobject.MainPage;

import static org.testng.Assert.assertEquals;

public class T0003_LogOutMail extends BaseTest {

    @Test
    public void logOutMail() {
        MainPage mainPage;
        try {
            mainPage = steps.logOut();
        }catch (NullPointerException | NoSuchElementException | TimeoutException e){
            e.printStackTrace();
            steps.logIn();
            mainPage = steps.logOut();
        }

        String loginHeaderText = mainPage.getLoginHeaderText();
        assertEquals(loginHeaderText, "Войти в аккаунт");
    }
}
