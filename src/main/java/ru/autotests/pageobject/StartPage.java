package ru.autotests.pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class StartPage extends BasePage {

    private static Logger logger = LogManager.getLogger();

    public StartPage open() {
        String mailruPage = "https://mail.ru/";
        open(mailruPage);
        return this;
    }

    public StartPage setLoginField(String login) {
        WebElement loginField = customWebDriver.findElementByXPath("//*[@id='mailbox:login']");
        logger.info(String.format("Ввожу логин: '%s'", login));
        loginField.sendKeys(login);
        return this;
    }

    public StartPage setPasswordField(String password) {
        WebElement passwordField = customWebDriver.findElementByXPath("//*[@id='mailbox:password']");
        logger.info("Ввожу пароль");
        passwordField.sendKeys(password);
        return this;
    }

    public StartPage clickSubmitButton() {
        customWebDriver.findElementByXPath("//*[@id='mailbox:submit']").click();
        logger.info("Кликаю по кнопке");
        return this;
    }
}
