package ru.autotests.steps;

import org.openqa.selenium.WebElement;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.pageobject.SendMailPage;
import ru.autotests.pageobject.StartPage;

import java.util.HashMap;
import java.util.Map;

import static ru.autotests.testdata.TestData.getLogin;
import static ru.autotests.testdata.TestData.getPassword;

public class Steps {

    private StartPage startPage;
    private MainPage mainPage;
    private SendMailPage sendMailPage;

    public Steps() {
        startPage = BasePage.initPage(StartPage.class);
        mainPage = BasePage.initPage(MainPage.class);
        sendMailPage = BasePage.initPage(SendMailPage.class);
    }

    public Steps logIn() {
        startPage.open()
                .setLoginField(getLogin())
                .clickSubmitButton()
                .setPasswordField(getPassword())
                .clickSubmitButton();
        return this;
    }

    public Steps logOut() {
        mainPage.clickLogOutButton();
        return this;
    }

    public String getLoginHeaderText() {
        return mainPage.getLoginHeaderText();
    }

    public String getUserEmail() {
        return mainPage.getUserEmail();
    }

    public Steps sendEmail(String recipientMailAddress, String letterSubject, String letterText) {
        mainPage.clickWriteLetterButton();
        sendMailPage.setMailAddress(recipientMailAddress)
                .setLetterSubject(letterSubject)
                .setLetterText(letterText)
                .clickSendButton();
        return this;
    }

    public String getEmailSendingStatus() {
        return sendMailPage.getEmailSendingStatus();
    }

    public Steps clickCloseInfoButton() {
        sendMailPage.clickCloseInfoButton();
        return this;
    }

    public Steps clickSentMessageFolder() {
        mainPage.clickSentMessageFolder();
        return this;
    }

    public WebElement getLetter(int letterNumber) {
        return mainPage.getLettersList().get(letterNumber);
    }

    public String getLetterRecipient(WebElement letter) {
        return mainPage.getLetterRecipient(letter);
    }
    public String getLetterSubject(WebElement letter) {
        return mainPage.getLetterSubject(letter);
    }

}
