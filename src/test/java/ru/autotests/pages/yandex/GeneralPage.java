package ru.autotests.pages.yandex;


import ru.autotests.pages.BasePage;
import ru.autotests.webdriver.WebDriverManager;

import static ru.autotests.webdriver.WebDriverManager.DriverType.FIREFOX;

public class GeneralPage extends BasePage {

    public void open() {
        String yandexPage = "https://yandex.ru/";
        driver.get(yandexPage);
    }

    public static void main(String[] args) {

        WebDriverManager.setupWebDriver(FIREFOX, 22,600);
        new GeneralPage().open();

    }
}
