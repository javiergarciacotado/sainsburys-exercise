package uk.co.sainsburys.exercise;

import uk.co.sainsburys.exercise.calculator.Calculator;
import uk.co.sainsburys.exercise.output.impl.FileWriterOutput;
import uk.co.sainsburys.exercise.page.WebPage;
import uk.co.sainsburys.exercise.parser.impl.RipeFruitsParser;
import uk.co.sainsburys.exercise.properties.PropertiesReader;
import uk.co.sainsburys.exercise.scraper.WebScraper;

import java.io.IOException;

/**
 * Setting an Application's Entry Point
 */
class Main {

    public static void main(String[] args) throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader("/application.properties");
        final WebScraper webScraper = new WebScraper(propertiesReader, new RipeFruitsParser(), new WebPage(), new Calculator(), new FileWriterOutput());
        webScraper.scrape();
        System.out.println("Sainsbury's web scraper finished");
        System.out.println("Please find the results at " + propertiesReader.get("sainsburys.output.file.name") + " file");
    }
}
