package ru.autotests.yandex;


import ru.autotests.testPlatform.BasePage;
import ru.autotests.testPlatform.WebDriverManager;

import static ru.autotests.testPlatform.WebDriverManager.DriverType.FIREFOX;

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
