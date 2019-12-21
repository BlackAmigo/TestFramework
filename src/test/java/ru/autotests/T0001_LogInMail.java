package ru.autotests;

import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;

import static org.testng.Assert.assertEquals;
import static ru.autotests.testdata.TestData.getLogin;
import static ru.autotests.testdata.TestData.getMailDomain;

public class T0001_LogInMail extends BaseTest {

    @Test
    public void logInMail() {
        steps.logIn();
        assertEquals(steps.getUserEmail(), getLogin() + getMailDomain());
    }
}
