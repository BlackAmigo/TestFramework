package ru.autotests.testdata;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestData {

    private static Properties properties;

    static{
        properties = new Properties();
        try {
            properties.load(new FileReader(new File("src/main/resources/config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLogin() {
        return properties.getProperty("mailru.login");
    }

    public static String getMailDomain() {
        return properties.getProperty("mailru.domain.mail");
    }

    public static String getPassword() {
        return properties.getProperty("mailru.password");
    }

    public static String getLetterSubject(){
        return "Тема письма";
    }

    public static String generateLetterText(){
        String dateTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        return "Привет! Это письмо написано " + dateTime + ".";
    }
}
