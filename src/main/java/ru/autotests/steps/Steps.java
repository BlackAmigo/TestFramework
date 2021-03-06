package ru.autotests.steps;

import org.openqa.selenium.WebElement;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.pageobject.SendMailPage;
import ru.autotests.pageobject.StartPage;
import ru.autotests.testdata.TestData;

import java.util.List;

public class Steps {

    private StartPage startPage;
    private MainPage mainPage;
    private SendMailPage sendMailPage;
    private TestData data;

    public Steps() {
        data = TestData.getInstance();
        startPage = BasePage.initPage(StartPage.class);
        mainPage = BasePage.initPage(MainPage.class);
        sendMailPage = BasePage.initPage(SendMailPage.class);
    }

    public Steps logIn() {
        startPage.open()
                .setLoginField(data.getLogin())
                .clickSubmitButton()
                .setPasswordField(data.getPassword())
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

    public String getAuthLink() {
        return mainPage.getAuthLink();
    }

    public String getUserEmail() {
        return mainPage.getUserEmail();
    }

    public Steps createEmail(String recipientMailAddress, String letterSubject, String letterText) {
        mainPage.clickWriteLetterButton();
        sendMailPage.setMailAddress(recipientMailAddress)
                .setLetterSubject(letterSubject)
                .setLetterText(letterText);
        return this;
    }

    public Steps clickSendMailButton() {
        sendMailPage.clickSendMailButton();
        return this;
    }

    public Steps clickSaveMailButton() {
        sendMailPage.clickSaveMailButton();
        return this;
    }

    public String getEmailSendingStatus() {
        return sendMailPage.getEmailSendingStatus();
    }

    public Steps clickCloseInfoButton() {
        sendMailPage.clickCloseInfoButton();
        return this;
    }

    public Steps clickCloseEmailFormButton() {
        sendMailPage.clickCloseEmailFormButton();
        return this;
    }

    public Steps clickSentMessageFolder() {
        mainPage.clickSentMessageFolder();
        return this;
    }

    public Steps clickDraftsFolder() {
        mainPage.clickDraftsFolder();
        return this;
    }

    public WebElement getLetter(int letterNumber) {
        return mainPage.getLettersList().get(letterNumber - 1);
    }

    public List<WebElement> getLettersList() {
        return mainPage.getLettersList();
    }

    public List<WebElement> getLettersList(int size) {
        List<WebElement> list = mainPage.getLettersList();
        if (list.size() > size)
            return list.subList(0, size);
        else
            return list;
    }

    public String getLetterRecipient(WebElement letter) {
        return mainPage.getLetterRecipient(letter);
    }

    public String getLetterSubject(WebElement letter) {
        return mainPage.getLetterSubject(letter);
    }

    public Steps clickLetterCheckbox(WebElement letter) {
        mainPage.clickLetterCheckbox(letter);
        return this;
    }


    public Steps clickInboxMessageFolder() {
        mainPage.clickInboxMessageFolder();
        return this;
    }

    public Steps clickDeleteButton() {
        mainPage.clickDeleteButton();
        return this;
    }
}
