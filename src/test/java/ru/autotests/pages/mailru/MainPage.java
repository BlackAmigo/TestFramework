package ru.autotests.pages.mailru;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import ru.autotests.pages.BasePage;

public class MainPage extends BasePage {

    public void clickWriteLetterButton() {
        logger.info("Кликаю по кнопке 'Написать письмо'");
        driver.findElementByXPath("//span[@title='Написать письмо']").click();
    }

    public void clickSentMessageFolder() {
        logger.info("Кликаю по папке 'Отправленные'");
        driver.findElementByXPath("//div[text() = 'Отправленные']").click();
    }

    /**
     * @param letterNumber - номер письма в папке
     * @return id письма
     */
    public String getLetterId(int letterNumber) {
        logger.info("Получаю ID письма");
        String xPath = String.format("//div[@class='dataset__items']/a[%d]", letterNumber);
        String id = null;
        int count = 0;
        while (count < 5) {
            try {
                WebElement element = driver.findElementByXPath(xPath);
                id = element.getAttribute("data-id");
                break;
            } catch (StaleElementReferenceException e) {
                logger.error("StaleElementReferenceException " + count + " times");
            }
            count++;
        }
        assert id != null;
        return id;
    }

    /**
     * @param letterId - id письма
     * @return адрес получателя
     */
    public String getLetterSender(String letterId) {
        logger.info("Получаю адрес получателя");
        String xPath = String.format("//a[@data-id='%s']//span[@class='ll-crpt']", letterId);
        WebElement element = driver.findElementByXPath(xPath);
        return element.getText();
    }

    /**
     * @param letterId - id письма
     * @return тема письма
     */
    public String getLetterSubject(String letterId) {
        logger.info("Получаю тему письма");
        String xPath = String.format("//a[@data-id='%s']//span[@class='llc__subject']", letterId);
        WebElement element = driver.findElementByXPath(xPath);
        return element.getText();
    }

    public void clickLogOutButton(){
        String xPath = "//a[@id='PH_logoutLink']";
        logger.info("Кликаю по кнопке 'Выход'");
        driver.findElementByXPath(xPath).click();
    }

    public String getUserEmail (){
        logger.info("Получаю адрес почты текущего пользователя");
        String xPath = "//i[@id='PH_user-email']";
        WebElement element = driver.findElementByXPath(xPath);
        return element.getText();
    }

    public String getLoginHeaderText(){
        logger.info("Получаю loginHeaderText");
        String textXPath = "//h1[@class='c0118 c0119']";
        String iFrameXPath = "//iframe[@class='ag-popup__frame__layout__iframe']";
        driver.getWebDriver().switchTo().frame(driver.findElementByXPath(iFrameXPath));
        return driver.findElementByXPath(textXPath).getText();
    }
}
