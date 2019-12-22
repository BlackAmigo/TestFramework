package ru.autotests.steps;

import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.pageobject.SendMailPage;
import ru.autotests.pageobject.StartPage;

import static ru.autotests.testdata.TestData.getLogin;
import static ru.autotests.testdata.TestData.getPassword;

public class Steps {

    public StartPage logIn() {
        StartPage startPage = BasePage.initPage(StartPage.class);
        return startPage.open()
                .setLoginField(getLogin())
                .clickSubmitButton()
                .setPasswordField(getPassword())
                .clickSubmitButton();
    }

    public MainPage logOut() {
        MainPage mainPage = BasePage.initPage(MainPage.class);
       return mainPage.clickLogOutButton();
    }

    public String getUserEmail() {
        MainPage mainPage = BasePage.initPage(MainPage.class);
        return mainPage.getUserEmail();
    }

    public SendMailPage sendEmail(String recipientMailAddress, String letterSubject, String letterText) {
        MainPage mainPage = BasePage.initPage(MainPage.class);
        mainPage.clickWriteLetterButton();
        SendMailPage sendMailPage = BasePage.initPage(SendMailPage.class);
        return sendMailPage.setMailAddress(recipientMailAddress)
                .setLetterSubject(letterSubject)
                .setLetterText(letterText)
                .clickSendButton();
    }
}
