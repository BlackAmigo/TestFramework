package ru.autotests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.pageobject.SendMailPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.autotests.testdata.TestData.*;

public class T0002_SendMail extends BaseTest {

    private String recipientMailAddress = getLogin() + getMailDomain();
    private String letterSubject = getLetterSubject();
    private String letterText = generateLetterText();

    @BeforeTest
    public void testPreparation(){
        String url = BasePage.getCurrentUrl();
        String currentUrl = "https://e.mail.ru/";
        if (!url.contains(currentUrl)) steps.logIn();
    }

    @Test
    public void sendMail() {

        SendMailPage sendMailPage = steps
                .sendEmail(recipientMailAddress, letterSubject, letterText);
        String status = sendMailPage.getEmailSendingStatus();

        assertEquals(status, "отправлено");
        sendMailPage.clickCloseInfoButton();

        MainPage mainPage = BasePage.initPage(MainPage.class);
        mainPage.clickSentMessageFolder();
        WebElement letter = mainPage.getLettersList().get(0);
        String letterSender = mainPage.getLetterRecipient(letter);
        String currentLetterSubject = mainPage.getLetterSubject(letter);

        assertEquals(letterSender, recipientMailAddress);

        assertTrue(currentLetterSubject.contains(letterSubject));
    }
}
