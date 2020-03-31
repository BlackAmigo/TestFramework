package ru.autotests.pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    private static Logger logger = LogManager.getLogger();

    public MainPage clickWriteLetterButton() {
        logger.info("Кликаю по кнопке 'Написать письмо'");
        customWebDriver.findElementByXPath("//*[@class='compose-button__wrapper']").click();
        return this;
    }

    public MainPage clickSentMessageFolder() {
        logger.info("Кликаю по папке 'Отправленные'");
        customWebDriver.findElementByXPath("//*[@title='Отправленные']").click();
        return this;
    }

    public MainPage clickInboxMessageFolder() {
        logger.info("Кликаю по папке 'Входящие'");
        customWebDriver.findElementByXPath("//*[@title='Входящие']").click();
        return this;
    }

    public MainPage clickDraftsFolder() {
        logger.info("Кликаю по папке 'Черновики'");
        customWebDriver.findElementByXPath("//*[@title='Черновики']").click();
        return this;
    }

    public String getLetterRecipient(WebElement letter) {
        WebElement element = letter.findElement(By.xpath("//*[@id='PH_user-email']"));
        logger.info("Получаю адрес получателя");
        return element.getText();
    }

    public String getLetterSubject(WebElement letter) {
        WebElement element = letter.findElement(By.className("llc__subject"));
        logger.info("Получаю тему письма");
        return element.getText();
    }

    public String getLetterText(WebElement letter) {
        WebElement element = letter.findElement(By.className("ll-sp__normal"));
        logger.info("Получаю текст письма");
        return element.getText();
    }

    public MainPage clickDeleteButton(){
        logger.info("Кликаю по кнопке 'Удалить'");
        customWebDriver.findElementByXPath("//*[@title='Удалить']").click();
        return this;
    }

    public MainPage clickLogOutButton() {
        String xPath = "//*[@id='PH_logoutLink']";
        logger.info("Кликаю по кнопке 'Выход'");
        customWebDriver.findElementByXPath(xPath).click();
        return this;
    }

    public String getUserEmail() {
        logger.info("Получаю адрес почты текущего пользователя");
        String xPath = "//*[@id='PH_user-email']";
        WebElement element = customWebDriver.findElementByXPath(xPath);
        return element.getText();
    }

    public String getAuthLink() {
        logger.info("Получаю текст элемента авторизации");
        String xPath = "//*[@id='PH_authLink']";
        WebElement element = customWebDriver.findElementByXPath(xPath);
        return element.getText();
    }

    public String getLoginHeaderText(){
        logger.info("Получаю сообщение об авторизации");
        String textXPath = "//*[@class='login-header']";
        String iFrameXPath = "//*[@class='ag-popup__frame__layout__iframe']";
        customWebDriver.getDriver().switchTo().frame(customWebDriver.findElementByXPath(iFrameXPath));
        return customWebDriver.findElementByXPath(textXPath).getText();
    }

    public List<WebElement> getLettersList(){
        String xPath = "//*[@class='dataset__items']//a";
        logger.info("Получаю список писем");
        int count = 0;
        List<WebElement> list = new ArrayList<>();
        while (count < 5) {
            list.clear();
            try {
            list = customWebDriver.findElementsByXPath(xPath);
            } catch (StaleElementReferenceException e) {
                logger.error("StaleElementReferenceException thrown " + ++count + " times");
            }
            count++;
        }
         return list;
    }

    public MainPage clickLetterCheckbox (WebElement letter){
        String xPath = ".//*[@class='ll-av__container']";
        logger.info("Кликаю по чекбоксу");
        letter.findElement(By.xpath(xPath)).click();
        return this;
    }

}
