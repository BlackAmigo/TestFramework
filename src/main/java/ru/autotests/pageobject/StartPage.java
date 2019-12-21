package ru.autotests.pageobject;

import org.openqa.selenium.WebElement;

public class StartPage extends BasePage {

    public StartPage open() {
        String mailruPage = "https://mail.ru/";
        open(mailruPage);
        return this;
    }

    public StartPage setLoginField(String login) {
        WebElement loginField = driver.findElementByXPath("//*[@id='mailbox:login']");
        logger.info(String.format("Ввожу логин: '%s'", login));
        loginField.sendKeys(login);
        return this;
    }

    public StartPage setPasswordField(String password) {
        WebElement passwordField = driver.findElementByXPath("//*[@id='mailbox:password']");
        logger.info("Ввожу пароль");
        passwordField.sendKeys(password);
        return this;
    }

    public StartPage clickSubmitButton() {
        driver.findElementByXPath("//*[@id='mailbox:submit']").click();
        logger.info("Кликаю по кнопке");
        return this;
    }
}
