package ru.autotests.yandex;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.autotests.BasePage;

public final class MainPage extends BasePage {

    public void open() {
        String yandexPage = "https://yandex.ru/";
        webDriver.get(yandexPage);
    }

    public void clickSearchField(String query) {
        log("Открываю поле поиска");
        String searchFieldPath = "//input[@aria-label='Запрос']";
        webDriver.findElementByXPath(searchFieldPath).click();
    }

    public void setSearchField(String query) {
        log("Пишу запрос в поле поиска: " + query);
        String searchFieldPath = "//input[@aria-label='Запрос']";
        WebElement searchField = webDriver.findElementByXPath(searchFieldPath);
        searchField.sendKeys(query);
    }

    public void clickSearchButton() {
        log("Кликаю по кнопке Найти");
        String searchButtonPath = "//button[@type='submit']";
        webDriver.findElementByXPath(searchButtonPath).click();
    }

    public void findPageInResponse(String page, int countPage) {
        WebElement element = null;
        int currentCount = 0;
        for (int i = 0; i < countPage; i++) {
            try {
                currentCount = i;
                element = webDriver.findByPartialLinkText(page);
                break;
            } catch (TimeoutException te) {
                String nextButton = "//a[text() = 'дальше']";
                webDriver.findElementByXPath(nextButton).click();
            }
        }
        log("Колличество переходов - " + currentCount);
        if (element != null) element.click();
        else throw new NullPointerException(String.format("Страница %s не найдена", page));
    }

}
