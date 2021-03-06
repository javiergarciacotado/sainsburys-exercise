package uk.co.sainsburys.exercise.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "uk.co.sainsburys.exercise.cucumber",
                 features = "src/test/resources/features/",
                 format = { "pretty",
                    "html:target/site/cucumber-pretty"
                 })
public class CucumberSuite {
}
