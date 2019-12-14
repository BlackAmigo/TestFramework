package ru.autotests.pages.mailru;

import org.openqa.selenium.WebElement;
import ru.autotests.pages.BasePage;

public class StartPage extends BasePage {

    String inputLoginPath = "//input[@id='mailbox:login']";
    String submitButtonPath = "//label[@id='mailbox:submit']";
    String inputPasswordPath = "//input[@id='mailbox:password']";

    public void open() {
        String mailruPage = "https://mail.ru/";
        open(mailruPage);
    }

    public void setLoginField(String login) {
        WebElement searchField = driver.findElementByXPath(inputLoginPath);
        logger.info(String.format("Пишу запрос в поле логин: '%s'", login));
        searchField.sendKeys(login);
    }

    public void setPasswordField(String password) {
        WebElement searchField = driver.findElementByXPath(inputPasswordPath);
        logger.info(String.format("Пишу запрос в поле пароль: '%s'", password));
        searchField.sendKeys(password);
    }

    public void clickSubmitButton() {
        driver.findElementByXPath(submitButtonPath).click();
        logger.info("Кликаю по кнопке");
    }
}
