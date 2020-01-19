package ru.autotests.pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class SendMailPage extends MainPage {

    private static Logger logger = LogManager.getLogger();

    public SendMailPage setMailAddress(String mailAddress) {
        String mailAddressField = "//*[@class='input--3slxg']//input";
        WebElement searchField = driver.findElementByXPath(mailAddressField);
        logger.info(String.format("Пишу адрес получателя письма: '%s'", mailAddress));
        searchField.sendKeys(mailAddress);
        return this;
    }

    public SendMailPage setLetterSubject(String letterSubject) {
        String letterSubjectField = "//*[@class='container--3QXHv']//input";
        WebElement searchField = driver.findElementByXPath(letterSubjectField);
        logger.info(String.format("Пишу тему письма: '%s'", letterSubject));
        searchField.sendKeys(letterSubject);
        return this;
    }

    public SendMailPage setLetterText(String letterText) {
        String letterTextAreaPath = "//*[contains(@class, 'editable-container')]/div/div";
        WebElement searchField = driver.findElementByXPath(letterTextAreaPath);
        logger.info(String.format("Пишу текст письма: '%s'", letterText));
        searchField.sendKeys(letterText);
        return this;
    }

    public SendMailPage clickSendButton(){
        logger.info("Кликаю по кнопке 'Отправить'");
        String sendButtonPath = "//*[@title='Отправить']";
        driver.findElementByXPath(sendButtonPath).click();
        return this;
    }

    public String getEmailSendingStatus() {
        logger.info("Получаю статус отправки письма");
        return getText("//*[@class='layer__header']/span");
    }

    public SendMailPage clickCloseInfoButton(){
        logger.info("Закрываю окно 'Письмо отправлено'");
        String closeButtonPath = "//*[@class='layer__controls']";
        driver.findElementByXPath(closeButtonPath).click();
        return this;
    }
}
