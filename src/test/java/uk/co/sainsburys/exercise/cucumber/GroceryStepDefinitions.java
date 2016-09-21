package uk.co.sainsburys.exercise.cucumber;

import com.google.gson.Gson;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.sainsburys.exercise.calculator.Calculator;
import uk.co.sainsburys.exercise.model.RipeFruit;
import uk.co.sainsburys.exercise.output.impl.FileWriterOutput;
import uk.co.sainsburys.exercise.output.impl.RipeFruitWrapper;
import uk.co.sainsburys.exercise.page.WebPage;
import uk.co.sainsburys.exercise.parser.impl.RipeFruitsParser;
import uk.co.sainsburys.exercise.properties.PropertiesReader;
import uk.co.sainsburys.exercise.scraper.WebScraper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GroceryStepDefinitions {

    private PropertiesReader propertiesReader;
    private WebScraper webScraper;
    private RipeFruitWrapper ripeFruitWrapper;

    @Given("^there is no previous results$")
    public void outuputValidation() throws IOException {
        propertiesReader = new PropertiesReader("/application.properties");
        final String fileName = propertiesReader.get("sainsburys.output.file.name");
        final Path path = Paths.get(fileName);
        Files.deleteIfExists(path);
    }

    @Given("^the grocery site is loaded$")
    public void loadPage() throws IOException {
        webScraper = new WebScraper(propertiesReader, new RipeFruitsParser(), new WebPage(), new Calculator(), new FileWriterOutput());
    }

    @When("^the grocery site is parsed$")
    public void parsePage() {
        webScraper.scrape();
    }

    @SuppressWarnings("EmptyMethod")
    @When("^the grocery site is not parsed$")
    public void problemParsingPage() {}

    @Then("^there are (\\d+) products with a total of (\\d+.\\d+)$")
    public void checkProductsAndTotal(int productsSize, double total) throws Throwable {
        final String fileName = propertiesReader.get("sainsburys.output.file.name");
        final Path path = Paths.get(fileName);
        final String content = Files.exists(path) ? new String(Files.readAllBytes(path)) : "";
        ripeFruitWrapper = !content.isEmpty() ? new Gson().fromJson(content, RipeFruitWrapper.class) : new RipeFruitWrapper();

        assertEquals(productsSize, ripeFruitWrapper.getResults().size());
        assertEquals(total, ripeFruitWrapper.getTotal(), 0.0D);
    }

    @And("^containing the following data$")
    public void checkContainedData(List<RipeFruit> ripeFruitList) throws Throwable {
        assertThat(ripeFruitList, is(equalTo(ripeFruitWrapper.getResults())));
    }
}
