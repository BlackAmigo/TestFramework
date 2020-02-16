package ru.autotests.testdata;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestData {

    private static TestData testData;
    private static Properties properties;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private TestData() {
       loadProp();
    }

    public static TestData getInstance(){
        if(testData == null)
            testData = new TestData();
        return testData;
    }

    public String getLogin() {
        return properties.getProperty("mailru.login");
    }

    public String getMailDomain() {
        return properties.getProperty("mailru.domain.mail");
    }

    public String getPassword() {
        return properties.getProperty("mailru.password");
    }

    public String getWebDriver() {
        return properties.getProperty("webdriver");
    }

    public String getLetterSubject(){
        return "Тема письма";
    }

    public String generateLetterText(){
        String dateTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        return "Привет! Это письмо написано " + dateTime + ".";
    }

    public String generateRandomString(int size){
       StringBuilder result = new StringBuilder(size);
       int alphabetSize = ALPHABET.length();
        for (int i = 0; i < size; i++) {
            int randomNumber = (int)(Math.random() * alphabetSize);
            result.append(ALPHABET.charAt(randomNumber));
        }
        return result.toString();
    }

    private void loadProp(){
        properties = new Properties();
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
