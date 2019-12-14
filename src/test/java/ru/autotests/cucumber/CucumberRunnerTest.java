package ru.autotests.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/ru/autotests/cucumber/features",
        glue = "ru/autotests/cucumber/yandex/steps",
//        tags = "@withdrawal",
//        dryRun = false,
//        strict = false,
        snippets = SnippetType.CAMELCASE
//        name = "^Успешное|Успешная.*"
)
public class CucumberRunnerTest {
}
