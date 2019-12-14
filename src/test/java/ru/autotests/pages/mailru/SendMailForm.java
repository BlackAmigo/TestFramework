package ru.autotests.pages.mailru;

import org.openqa.selenium.WebElement;

public class SendMailForm extends MainPage {

    private String mailAddressField = "//div[@class='input--3slxg']//input";
    private String letterSubjectField = "//div[@class='container--3QXHv']//input";
    private String letterTextAreaPath = "//div[contains(@class, 'editable-container')]/div";
    private String sendButtonPath = "//span[@title='Отправить']";
    private String closeButtonPath = "//div[@class='layer__controls']";

    public void setMailAddress(String mailAddress) {
        WebElement searchField = driver.findElementByXPath(mailAddressField);
        logger.info(String.format("Пишу адрес получателя письма: '%s'", mailAddress));
        searchField.sendKeys(mailAddress);
    }

    public void setLetterSubjectField(String letterSubject) {
        WebElement searchField = driver.findElementByXPath(letterSubjectField);
        logger.info(String.format("Пишу тему письма: '%s'", letterSubject));
        searchField.sendKeys(letterSubject);
    }

    public void setLetterText(String letterText) {
        WebElement searchField = driver.findElementByXPath(letterTextAreaPath);
        logger.info(String.format("Пишу текст письма: '%s'", letterText));
        searchField.sendKeys(letterText);
    }

    public void clickSendButton(){
        logger.info("Кликаю по кнопке 'Отправить'");
        driver.findElementByXPath(sendButtonPath).click();
    }

    public void clickCloseInfoButton(){
        logger.info("Закрываю окно 'Письмо отправлено'");
        driver.findElementByXPath(closeButtonPath).click();
    }
}
