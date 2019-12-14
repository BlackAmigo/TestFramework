package ru.autotests.cucumber.yandex.steps;

import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.Тогда;
import ru.autotests.pages.yandex.MainPage;
import ru.autotests.webdriver.WebDriverManager;

import static ru.autotests.webdriver.WebDriverManager.DriverType.CHROME;

public class StepDefinition {

    private MainPage mainPage;

    @Допустим("^, что открыта страница https://yandex\\.ru$")
    public void чтоОткрытаСтраницаHttpsYandexRu() {
        WebDriverManager.setupWebDriver(CHROME);
        mainPage = new MainPage();
        mainPage.open();
    }

    @Если("^ввести в поле поиска \"([^\"]*)\"$")
    public void ввестиВПолеПоиска(String query) {
        mainPage.setSearchField(query);
    }

    @Если("^нажать на кнопку 'Найти'$")
    public void нажатьНаКнопкуНайти() {
        mainPage.clickSearchButton();
    }

    @Тогда("^искать ссылку с текстом \"([^\"]*)\", на первых \"([^\"]*)\" страницах$")
    public void искатьСсылкуСТекстомНаПервыхСтраницах(String siteName, int count) {
        mainPage.findPageInResponse(siteName, count);
    }
}
