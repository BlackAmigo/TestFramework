package ru.autotests.cucumber;

import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.WebElement;
import ru.autotests.basetest.BaseTest;
import ru.autotests.pageobject.BasePage;
import ru.autotests.pageobject.MainPage;
import ru.autotests.pageobject.SendMailPage;
import ru.autotests.pageobject.StartPage;
import ru.autotests.testdata.TestData;
import ru.autotests.webdriver.WebDriverManager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;

public class StepDefinition {

    static {
        WebDriverManager.setupWebDriver(CHROME);
    }

    private StartPage startPage = BasePage.initPage(StartPage.class);
    private MainPage mainPage = BasePage.initPage(MainPage.class);
    private SendMailPage sendMailPage = BasePage.initPage(SendMailPage.class);

    @Допустим("^, что открыта страница https://mail\\.ru$")
    public void чтоОткрытаСтраницаHttpsMailRu() {
        startPage.open();
    }

    @Если("^ввести в поле 'Почта' логин$")
    public void ввестиВПолеПочтаЛогин() {
        startPage.setLoginField(TestData.getLogin());
    }

    @Если("^нажать на кнопку 'Ввести пароль'$")
    public void нажатьНаКнопкуВвестиПароль() {
        startPage.clickSubmitButton();
    }

    @Если("^ввести в поле пароль$")
    public void ввестиВПолеПароль() {
        startPage.setPasswordField(TestData.getPassword());
    }

    @Если("^нажать на кнопку 'Войти'$")
    public void нажатьНаКнопкуВойти() {
        startPage.clickSubmitButton();
    }

    @Тогда("^на странице отображается почтовый адресс вошедшего пользователя$")
    public void наСтраницеОтображаетсяПочтовыйАдрессВошедшегоПользователя() {
        assertEquals(mainPage.getUserEmail(), TestData.getLogin() + TestData.getMailDomain());
    }

    @Если("^нажать на кнопку 'Написать письмо'$")
    public void нажатьНаКнопкуНаписатьПисьмо() {
        mainPage.clickWriteLetterButton();
    }

    @Если("^ввести аддрес получателя$")
    public void ввестиАддресПолучателя() {
        sendMailPage.setMailAddress(TestData.getLogin() + TestData.getMailDomain());
    }

    @Если("^ввести тему письма$")
    public void ввестиТемуПисьма() {
        sendMailPage.setLetterSubject(TestData.getLetterSubject());
    }

    @Если("^ввести текст письма$")
    public void ввестиТекстПисьма() {
        sendMailPage.setLetterText(TestData.generateLetterText());
    }

    @Если("^нажать на кнопку 'Отправить'$")
    public void нажатьНаКнопкуОтправить() {
        sendMailPage.clickSendButton();
    }

    @То("^появилось сообщение 'отправлено'$")
    public void появилосьСообщениеОтправлено() {
        String status = sendMailPage.getEmailSendingStatus();
        assertEquals(status, "отправлено");
        sendMailPage.clickCloseInfoButton();
    }

    @Допустим("^, что открыта папка 'Отправленные'$")
    public void чтоОткрытаПапкаОтправленные() {
        mainPage.clickSentMessageFolder();
    }

    @Тогда("^у (\\d+) письма в списке, адрес почты получателя = адресу почты отправителя$")
    public void уПисьмаВСпискеАдресПочтыПолучателяАдресуПочтыОтправителя(int letterNumber) {
        WebElement letter = mainPage.getLettersList().get(letterNumber - 1);
        String letterSender = mainPage.getLetterRecipient(letter);
        assertEquals(letterSender, TestData.getLogin() + TestData.getMailDomain());
    }

    @Тогда("^у (\\d+) письма в списке, тема письма = теме отправленного письма$")
    public void уПисьмаВСпискеТемаПисьмаТемеОтправленногоПисьма(int letterNumber) {
        WebElement letter = mainPage.getLettersList().get(letterNumber - 1);
        String currentLetterSubject = mainPage.getLetterSubject(letter);
        assertTrue(currentLetterSubject.contains(TestData.getLetterSubject()));
    }

    @Если("^нажать на кнопку 'Выход'$")
    public void нажатьНаКнопкуВыход() {
        mainPage.clickLogOutButton();
    }

    @Тогда("^появилось сообщение 'Войти в аккаунт'$")
    public void появилосьСообщениеВойтиВАккаунт() {
        String loginHeaderText = mainPage.getLoginHeaderText();
        assertEquals(loginHeaderText, "Войти в аккаунт");
    }
}
