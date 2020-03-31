package ru.autotests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;

import static org.testng.Assert.assertEquals;

public class T0003_LogOutMail extends BaseTest {

    @BeforeTest
    public void beforeTest() {
        steps.logIn();
    }

    @Test
    public void logOut(){
        String loginHeaderText = steps.logOut().getAuthLink();
        assertEquals(loginHeaderText, "Вход");
    }
}
