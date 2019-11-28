package ru.autotests;

public abstract class BasePage {

    protected WebDriver webDriver = WebDriver.getInstance();

    public void closeAll(){
        webDriver.quit();
    }

    public void log (String msg){
        WebDriver.getLogger().trace(msg);
    }
}
