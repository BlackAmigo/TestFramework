package ru.autotests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;

import java.util.List;

import static org.testng.Assert.*;

public class T0004_DeleteMail extends BaseTest {

    @BeforeTest
    public void logInMail() {
        steps.logIn();
    }

    @Test
    public void deleteMail() {
        String letterSender = null;
        String letterSubject = null;
        String verifiableLetterSender = null;
        String verifiableLetterSubject = null;
        boolean isLetterExist = false;

        WebElement firstLetter = steps.clickInboxMessageFolder().getLetter(1);
        letterSender = steps.getLetterRecipient(firstLetter);
        letterSubject = steps.getLetterSubject(firstLetter);

        steps.clickLetterCheckbox(firstLetter).clickDeleteButton();

        List<WebElement> letterList = steps.getLettersList(3);
        for (int i = 0; i < letterList.size(); i++) {
            WebElement letter = letterList.get(i);
            verifiableLetterSender = steps.getLetterRecipient(letter);
            verifiableLetterSubject = steps.getLetterSubject(letter);
            if(letterSender.equals(verifiableLetterSender) && letterSubject.equals(verifiableLetterSubject)){
                isLetterExist = true;
                break;
            }
        }

        assertFalse(isLetterExist);
    }

    @AfterTest
    public void afterTest() {
        steps.logOut();
    }
}
