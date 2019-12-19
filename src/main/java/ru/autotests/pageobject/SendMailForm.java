package ru.autotests.pageobject;

import org.openqa.selenium.WebElement;

public class SendMailForm extends MainPage {

    public void setMailAddress(String mailAddress) {
        String mailAddressField = "//div[@class='input--3slxg']//input";
        WebElement searchField = driver.findElementByXPath(mailAddressField);
        logger.info(String.format("Пишу адрес получателя письма: '%s'", mailAddress));
        searchField.sendKeys(mailAddress);
    }

    public void setLetterSubjectField(String letterSubject) {
        String letterSubjectField = "//div[@class='container--3QXHv']//input";
        WebElement searchField = driver.findElementByXPath(letterSubjectField);
        logger.info(String.format("Пишу тему письма: '%s'", letterSubject));
        searchField.sendKeys(letterSubject);
    }

    public void setLetterText(String letterText) {
        String letterTextAreaPath = "//div[contains(@class, 'editable-container')]/div";
        WebElement searchField = driver.findElementByXPath(letterTextAreaPath);
        logger.info(String.format("Пишу текст письма: '%s'", letterText));
        searchField.sendKeys(letterText);
    }

    public void clickSendButton(){
        logger.info("Кликаю по кнопке 'Отправить'");
        String sendButtonPath = "//span[@title='Отправить']";
        driver.findElementByXPath(sendButtonPath).click();
    }

    public String getEmailSendingStatus() {
        logger.info("Получаю статус отправки письма");
        return getText("//div[@class='layer__header']/span[text()='отправлено']");
    }

    public void clickCloseInfoButton(){
        logger.info("Закрываю окно 'Письмо отправлено'");
        String closeButtonPath = "//div[@class='layer__controls']";
        driver.findElementByXPath(closeButtonPath).click();
    }
}
