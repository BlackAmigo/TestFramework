package ru.autotests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.autotests.pages.mailru.MainPage;
import ru.autotests.pages.mailru.SendMailForm;
import ru.autotests.pages.mailru.StartPage;
import ru.autotests.webdriver.WebDriverManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;

public class MailTest {

    private Properties properties;

    {
        properties = new Properties();
        try {
            properties.load(new FileReader(new File("src/test/resources/config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StartPage startPage;
    private MainPage mainPage;
    private SendMailForm sendMailForm;

    private String login = properties.getProperty("mailru.login");
    private String domain = properties.getProperty("mailru.domain.mail");
    private String password = properties.getProperty("mailru.password");

    private String recipientMailAddress = login + domain;
    private String letterSubject = "Тема письма";
    private String letterText = "Не первое письмо!";

    @BeforeClass
    public void setUp() {
        WebDriverManager.setupWebDriver(CHROME, 10, 2000);
        startPage = new StartPage();
        mainPage = new MainPage();
        sendMailForm = new SendMailForm();
    }

    @Test
    public void test1() {
        startPage.open();
        startPage.setLoginField(login);
        startPage.clickSubmitButton();
        startPage.setPasswordField(password);
        startPage.clickSubmitButton();
        String currentUserEmail = mainPage.getUserEmail();
        assertEquals(currentUserEmail, login + domain);
    }

    @Test
    public void test2() {
        mainPage.clickWriteLetterButton();
        sendMailForm.setMailAddress(recipientMailAddress);
        sendMailForm.setLetterSubjectField(letterSubject);
        sendMailForm.setLetterText(letterText);
        sendMailForm.clickSendButton();
        sendMailForm.clickCloseInfoButton();
    }

    @Test
    public void test3() {
        mainPage.clickSentMessageFolder();
        String letterId = mainPage.getLetterId(1);

        String letterSender = mainPage.getLetterSender(letterId);
        String currentLetterSubject = mainPage.getLetterSubject(letterId);
        assertEquals(letterSender, recipientMailAddress);
        assertTrue(currentLetterSubject.contains(letterSubject));
    }

    @Test
    public void test4() {
        mainPage.clickLogOutButton();
        String loginHeaderText = mainPage.getLoginHeaderText();
        assertEquals(loginHeaderText, "Войти в аккаунт");
    }

    @AfterClass
    public void cleanUp() {
        mainPage.closeAllWindows();
    }
}
