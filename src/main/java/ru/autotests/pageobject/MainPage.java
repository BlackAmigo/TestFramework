package ru.autotests.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    public MainPage clickWriteLetterButton() {
        logger.info("Кликаю по кнопке 'Написать письмо'");
        driver.findElementByXPath("//*[@class='compose-button__wrapper']").click();
        return this;
    }

    public MainPage clickSentMessageFolder() {
        logger.info("Кликаю по папке 'Отправленные'");
        driver.findElementByXPath("//*[text() = 'Отправленные']").click();
        return this;
    }

    public String getLetterRecipient(WebElement letter) {
        WebElement element = letter.findElement(By.xpath("//*[@class='ll-crpt']"));
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

    public MainPage clickLogOutButton() {
        String xPath = "//*[@id='PH_logoutLink']";
        logger.info("Кликаю по кнопке 'Выход'");
        driver.findElementByXPath(xPath).click();
        return this;
    }

    public String getUserEmail() {
        logger.info("Получаю адрес почты текущего пользователя");
        String xPath = "//*[@id='PH_user-email']";
        WebElement element = driver.findElementByXPath(xPath);
        return element.getText();
    }

    public String getLoginHeaderText(){
        logger.info("Получаю сообщение об авторизации");
        String textXPath = "//*[@class='login-header']";
        String iFrameXPath = "//*[@class='ag-popup__frame__layout__iframe']";
        driver.getWebDriver().switchTo().frame(driver.findElementByXPath(iFrameXPath));
        return driver.findElementByXPath(textXPath).getText();
    }

    public List<WebElement> getLettersList(){
        String xPath = "//*[@class='dataset__items']/*";
        logger.info("Получаю список писем");
        int count = 0;
        List<WebElement> list = new ArrayList<>();
        while (count < 5) {
            list.clear();
            try {
            list = driver.findElementsByXPath(xPath);
            } catch (StaleElementReferenceException e) {
                logger.error("StaleElementReferenceException thrown " + ++count + " times");
            }
            count++;
        }
         return list;
    }
}