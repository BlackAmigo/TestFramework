package ru.autotests.testPlatform.customdriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.autotests.testPlatform.WebDriver;

public class CustomChromeDriver extends WebDriver {

    private static CustomChromeDriver myChromeDriver;
    private ChromeDriver driver;

    private CustomChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);

    }

    public static CustomChromeDriver getInstance(){
        if (myChromeDriver == null) myChromeDriver = new CustomChromeDriver();
        return myChromeDriver;
    }

    @Override
    public void get(String url) {
        super.get(url);
        driver.get(url);
    }
}
