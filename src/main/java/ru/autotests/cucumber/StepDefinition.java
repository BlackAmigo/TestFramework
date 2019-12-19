package ru.autotests.cucumber;

import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import ru.autotests.pageobject.MainPage;
import ru.autotests.pageobject.SendMailForm;
import ru.autotests.pageobject.StartPage;
import ru.autotests.webdriver.WebDriverManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;

public class StepDefinition {

    private Properties properties;
    {
        properties = new Properties();
        try {
            properties.load(new FileReader(new File("src/main/resources/config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StartPage startPage;
    private MainPage mainPage;
    private SendMailForm sendMailForm;
    private String login;
    private String domain;
    private String password;
    private String recipientMailAddress;
    private String letterSubject;
    private String letterText;
    private String letterId;

    {
        WebDriverManager.setupWebDriver(CHROME);
        startPage = new StartPage();
        mainPage = new MainPage();
        sendMailForm = new SendMailForm();

        login = properties.getProperty("mailru.login");
        domain = properties.getProperty("mailru.domain.mail");
        password = properties.getProperty("mailru.password");

        recipientMailAddress = login + domain;
        letterSubject = "Тема письма";
        letterText = "Не первое письмо!";
    }

    @Допустим("^, что открыта страница https://mail\\.ru$")
    public void чтоОткрытаСтраницаHttpsMailRu() {
        startPage.open();
    }

    @Если("^ввести в поле 'Почта' логин$")
    public void ввестиВПолеПочтаЛогин() {
        startPage.setLoginField(login);
    }

    @Если("^нажать на кнопку 'Ввести пароль'$")
    public void нажатьНаКнопкуВвестиПароль() {
        startPage.clickSubmitButton();
    }

    @Если("^ввести в поле пароль$")
    public void ввестиВПолеПароль() {
        startPage.setPasswordField(password);
    }

    @Если("^нажать на кнопку 'Войти'$")
    public void нажатьНаКнопкуВойти() {
        startPage.clickSubmitButton();
    }

    @Тогда("^на странице отображается почтовый адресс вошедшего пользователя$")
    public void наСтраницеОтображаетсяПочтовыйАдрессВошедшегоПользователя() {
        String currentUserEmail = mainPage.getUserEmail();
        assertEquals(currentUserEmail, login + domain);
    }

    @Если("^нажать на кнопку 'Написать письмо'$")
    public void нажатьНаКнопкуНаписатьПисьмо() {
        mainPage.clickWriteLetterButton();
    }

    @Если("^ввести аддрес получателя$")
    public void ввестиАддресПолучателя() {
        sendMailForm.setMailAddress(recipientMailAddress);
    }

    @Если("^ввести тему письма$")
    public void ввестиТемуПисьма() {
        sendMailForm.setLetterSubjectField(letterSubject);
    }

    @Если("^ввести текст письма$")
    public void ввестиТекстПисьма() {
        sendMailForm.setLetterText(letterText);
    }

    @Если("^нажать на кнопку 'Отправить'$")
    public void нажатьНаКнопкуОтправить() {
        sendMailForm.clickSendButton();
    }

    @То("^появилось сообщение 'отправлено'$")
    public void появилосьСообщениеОтправлено() {
        String emailStatus = sendMailForm.getEmailSendingStatus();
        assertEquals(emailStatus, "отправлено");
        sendMailForm.clickCloseInfoButton();
    }

    @Допустим("^, что открыта папка 'Отправленные'$")
    public void чтоОткрытаПапкаОтправленные() {
        mainPage.clickSentMessageFolder();
    }

    @Тогда("^у первого письма в списке, адрес почты получателя = адресу почты отправителя$")
    public void уПервогоПисьмаВСпискеАдресПочтыПолучателяАдресуПочтыОтправителя() {
        letterId = mainPage.getLetterId(1);
        String letterSender = mainPage.getLetterSender(letterId);
        assertEquals(letterSender, recipientMailAddress);
    }

    @Тогда("^тема письма = теме отправленного письма$")
    public void темаПисьмаТемеОтправленногоПисьма() {
        String currentLetterSubject = mainPage.getLetterSubject(letterId);
        assertTrue(currentLetterSubject.contains(letterSubject));
    }

    @Если("^нажать на кнопку 'Выход'$")
    public void нажатьНаКнопкуВыход() {
        mainPage.clickLogOutButton();
    }

    @Тогда("^появилось сообщение 'Войти в аккаунт'$")
    public void появилосьСообщениеВойтиВАккаунт() {
        String loginHeaderText = mainPage.getLoginHeaderText();
        assertEquals(loginHeaderText, "Войти в аккаунт");
        mainPage.closeAllWindows();
    }
}
