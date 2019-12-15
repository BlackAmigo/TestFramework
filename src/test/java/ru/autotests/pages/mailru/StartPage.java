package ru.autotests.pages.mailru;

import org.openqa.selenium.WebElement;
import ru.autotests.pages.BasePage;

public class StartPage extends BasePage {

    public void open() {
        String mailruPage = "https://mail.ru/";
        open(mailruPage);
    }

    public void setLoginField(String login) {
        WebElement searchField = driver.findElementByXPath("//input[@id='mailbox:login']");
        logger.info(String.format("Пишу запрос в поле логин: '%s'", login));
        searchField.sendKeys(login);
    }

    public void setPasswordField(String password) {
        WebElement searchField = driver.findElementByXPath("//input[@id='mailbox:password']");
        logger.info(String.format("Пишу запрос в поле пароль: '%s'", password));
        searchField.sendKeys(password);
    }

    public void clickSubmitButton() {
        driver.findElementByXPath("//label[@id='mailbox:submit']").click();
        logger.info("Кликаю по кнопке");
    }
}
