package ru.autotests.pages.yandex;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.autotests.pages.BasePage;

public final class MainPage extends BasePage {

    public void open() {
        String yandexPage = "https://yandex.ru/";
        driver.get(yandexPage);
    }

    public void clickSearchField(String query) {
        logger.info("Открываю поле поиска");
        String searchFieldPath = "//input[@aria-label='Запрос']";
        driver.findElementByXPath(searchFieldPath).click();
    }

    public void setSearchField(String query) {
        logger.info("Пишу запрос в поле поиска: " + query);
        String searchFieldPath = "//input[@aria-label='Запрос']";
        WebElement searchField = driver.findElementByXPath(searchFieldPath);
        searchField.sendKeys(query);
    }

    public void clickSearchButton() {
        logger.info("Кликаю по кнопке Найти");
        String searchButtonPath = "//button[@type='submit']";
        driver.findElementByXPath(searchButtonPath).click();
    }

    public void findPageInResponse(String page, int countPage) {
        WebElement element = null;
        int currentCount = 0;
        for (int i = 0; i < countPage; i++) {
            try {
                currentCount = i;
                element = driver.findByPartialLinkText(page);
                break;
            } catch (TimeoutException te) {
                String nextButton = "//a[text() = 'дальше']";
                driver.findElementByXPath(nextButton).click();
            }
        }
        logger.info("Колличество переходов - " + currentCount);
        if (element != null) element.click();
        else throw new NullPointerException(String.format("Страница %s не найдена", page));
    }
}
