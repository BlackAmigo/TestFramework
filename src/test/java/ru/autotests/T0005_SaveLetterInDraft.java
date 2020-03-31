package ru.autotests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;

import static org.testng.Assert.*;

public class T0005_SaveLetterInDraft extends BaseTest {

    private String recipientMailAddress = data.getLogin() + data.getMailDomain();
    private String letterSubject = "Тема:" + data.generateRandomString(5);
    private String letterText = data.generateLetterText();

    @BeforeTest
    public void logInMail() {
        steps.logIn();
    }

    @Test
    public void saveLetter() {
        steps.createEmail(recipientMailAddress, letterSubject, letterText)
                .clickSaveMailButton()
                .clickCloseEmailFormButton();
    }

    @Test(dependsOnMethods = "saveLetter")
    public void checkRecipientMailAddress() {
        WebElement letter = steps.clickDraftsFolder()
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
    public void afterTest() {
        steps.logOut();
    }
}
