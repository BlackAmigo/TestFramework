package ru.autotests.cucumber;

import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.WebElement;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.pageobject.SendMailPage;
import ru.autotests.pageobject.StartPage;
import ru.autotests.testdata.TestData;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StepDefinition {
    private static TestData data;

    static {
        data = TestData.getInstance();
    }

    private StartPage startPage = BasePage.initPage(StartPage.class);
    private MainPage mainPage = BasePage.initPage(MainPage.class);
    private SendMailPage sendMailPage = BasePage.initPage(SendMailPage.class);

    @Допустим("^, что открыта страница https://mail\\.ru$")
    public void openMailRuPage() {
        startPage.open();
    }

    @Если("^ввести в поле 'Почта' логин$")
    public void enterLogin() {
        startPage.setLoginField(data.getLogin());
    }

    @Если("^нажать на кнопку 'Ввести пароль'$")
    public void clickEnterPasswordButton() {
        startPage.clickSubmitButton();
    }

    @Если("^ввести в поле пароль$")
    public void enterPassword() {
        startPage.setPasswordField(data.getPassword());
    }

    @Если("^нажать на кнопку 'Войти'$")
    public void clickEnterButton() {
        startPage.clickSubmitButton();
    }

    @Тогда("^на странице отображается почтовый адресс вошедшего пользователя$")
    public void userEmailDisplayed() {
        assertEquals(mainPage.getUserEmail(), data.getLogin() + data.getMailDomain());
    }

    @Если("^нажать на кнопку 'Написать письмо'$")
    public void clickWriteMailButton() {
        mainPage.clickWriteLetterButton();
    }

    @Если("^ввести аддрес получателя$")
    public void enterRecipientAddress() {
        sendMailPage.setMailAddress(data.getLogin() + data.getMailDomain());
    }

    @Если("^ввести тему письма$")
    public void enterLetterSubject() {
        sendMailPage.setLetterSubject(data.getLetterSubject());
    }

    @Если("^ввести текст письма$")
    public void enterLetterText() {
        sendMailPage.setLetterText(data.generateLetterText());
    }

    @Если("^нажать на кнопку 'Отправить'$")
    public void clickSendButton() {
        sendMailPage.clickSendMailButton();
    }

    @То("^появилось сообщение 'отправлено'$")
    public void messageSentAppeared() {
        String status = sendMailPage.getEmailSendingStatus();
        assertEquals(status, "отправлено");
        sendMailPage.clickCloseInfoButton();
    }

    @Допустим("^, что открыта папка 'Отправленные'$")
    public void openSendFolder() {
        mainPage.clickSentMessageFolder();
    }

    @Тогда("^у (\\d+) письма в списке, адрес почты получателя = адресу почты отправителя$")
    public void checkRecipientEmailEqualsSenderEmail(int letterNumber) {
        WebElement letter = mainPage.getLettersList().get(letterNumber - 1);
        String letterSender = mainPage.getLetterRecipient(letter);
        assertEquals(letterSender, data.getLogin() + data.getMailDomain());
    }

    @Тогда("^у (\\d+) письма в списке, тема письма = теме отправленного письма$")
    public void checkLetterSubjectEqualsSentLetterSubject(int letterNumber) {
        WebElement letter = mainPage.getLettersList().get(letterNumber - 1);
        String currentLetterSubject = mainPage.getLetterSubject(letter);
        assertTrue(currentLetterSubject.contains(data.getLetterSubject()));
    }

    @Если("^нажать на кнопку 'Выход'$")
    public void clickExitButton() {
        mainPage.clickLogOutButton();
    }

    @Тогда("^появилось сообщение 'Войти в аккаунт'$")
    public void messageEnterToAccountAppeared() {
        String loginHeaderText = mainPage.getAuthLink();
        assertEquals(loginHeaderText, "Вход");
    }
}
