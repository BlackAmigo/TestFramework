package ru.autotests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.autotests.testdata.TestData.*;

public class T0002_SendMail extends BaseTest {

    private String recipientMailAddress = getLogin() + getMailDomain();
    private String letterSubject = getLetterSubject();
    private String letterText = generateLetterText();

    @BeforeTest
    public void beforeTest(){
        steps.logIn();
    }

    @Test()
    public void sendMail() {
        String status = steps
                .sendEmail(recipientMailAddress, letterSubject, letterText)
                .getEmailSendingStatus();
        assertEquals(status, "отправлено");
        steps.clickCloseInfoButton();
    }

    @Test(dependsOnMethods = "sendMail")
    public void checkRecipientMailAddress() {
        WebElement letter = steps.clickSentMessageFolder()
                .getLetter(1);
        String letterSender = steps.getLetterRecipient(letter);
        assertEquals(letterSender, recipientMailAddress);
    }

    @Test(dependsOnMethods = "checkRecipientMailAddress")
    public void checkLetterSubject() {
        WebElement letter = steps.getLetter(1);
        String currentLetterSubject = steps.getLetterSubject(letter);
        assertTrue(currentLetterSubject.contains(letterSubject));
    }

    @AfterTest
    public void afterTest(){
        steps.logOut();
    }
}
