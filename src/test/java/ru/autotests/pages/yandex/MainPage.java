package ru.autotests.pages.yandex;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.autotests.pages.BasePage;

public final class MainPage extends BasePage {

    public void open() {
        String yandexPage = "https://yandex.ru/";
        open(yandexPage);
    }

    public void clickSearchField(String query) {
        String searchFieldPath = "//input[@aria-label='Запрос']";
        driver.findElementByXPath(searchFieldPath).click();
        logger.info("Кликаю по полю поиска");
    }

    public void setSearchField(String query) {
        String searchFieldPath = "//input[@aria-label='Запрос']";
        WebElement searchField = driver.findElementByXPath(searchFieldPath);
        logger.info(String.format("Пишу запрос в поле поиска: '%s'", query));
        searchField.sendKeys(query);
    }

    public void clickSearchButton() {
        String searchButtonPath = "//button[@type='submit']";
        driver.findElementByXPath(searchButtonPath).click();
        logger.info("Кликаю по кнопке Найти");
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
        if (element != null) {
            element.click();
            logger.info(String.format("Страница %s найдена", page));
        }
        else throw new NullPointerException(String.format("Страница %s НЕ найдена!", page));
    }
}
