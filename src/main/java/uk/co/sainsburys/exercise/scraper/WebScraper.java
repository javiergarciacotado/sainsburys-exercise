package uk.co.sainsburys.exercise.scraper;

import org.jsoup.nodes.Document;
import uk.co.sainsburys.exercise.calculator.Calculator;
import uk.co.sainsburys.exercise.model.RipeFruit;
import uk.co.sainsburys.exercise.output.Output;
import uk.co.sainsburys.exercise.page.WebPage;
import uk.co.sainsburys.exercise.parser.Parser;
import uk.co.sainsburys.exercise.properties.PropertiesReader;

import java.io.IOException;
import java.util.List;

public class WebScraper {

    private static final String PROP_SAINSBURYS_URL = "sainsburys.scraper.url";
    private static final String PROP_OUTPUT_RESULT = "sainsburys.output.file.name";

    private final PropertiesReader propertiesReader;
    private final WebPage webPage;
    private final Parser parser;
    private final Calculator calculator;
    private final Output output;

    public WebScraper(PropertiesReader propertiesReader, Parser parser, WebPage webPage, Calculator calculator, Output output) {
        this.propertiesReader = propertiesReader;
        this.parser = parser;
        this.webPage = webPage;
        this.calculator = calculator;
        this.output = output;
    }

    public void scrape() {
        try {
            final Document document = webPage.connectTo(propertiesReader.get(PROP_SAINSBURYS_URL));
            final List<RipeFruit> ripeFruits = parser.parse(document);
            final double total = calculator.getTotalPrice(ripeFruits);
            output.writeTo(propertiesReader.get(PROP_OUTPUT_RESULT), total, ripeFruits);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
