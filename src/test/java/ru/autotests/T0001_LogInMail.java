package ru.autotests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;

import static org.testng.Assert.assertEquals;

public class T0001_LogInMail extends BaseTest {

    @Test
    public void logInMail() {
        steps.logIn();
        assertEquals(steps.getUserEmail(), data.getLogin() + data.getMailDomain());
    }

    @AfterTest
    public void afterTest(){
        steps.logOut();
    }
}
