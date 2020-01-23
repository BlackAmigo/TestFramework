package ru.autotests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.autotests.basetest.BaseTest;

import static org.testng.Assert.assertNotEquals;

public class T0004_DeleteMail extends BaseTest {

    @BeforeTest
    public void logInMail() {
        steps.logIn();
    }

    @Test
    public void deleteMail(){
        String letterSender = "";
        String letterSender2 = "";
        while (letterSender.equals(letterSender2)) {
            WebElement letter = steps.clickInboxMessageFolder()
                    .getLetter(1);

            letterSender = steps.getLetterRecipient(letter);

            steps.clickLetterCheckbox(letter);
            steps.clickDeleteButton();

            WebElement letter2 = steps.getLetter(1);
            letterSender2 = steps.getLetterRecipient(letter2);
        }
        assertNotEquals(letterSender, letterSender2);
    }

    @AfterTest
    public void afterTest(){
        steps.logOut();
    }
}
