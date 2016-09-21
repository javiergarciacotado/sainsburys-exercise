package uk.co.sainsburys.exercise.scraper;

import org.jsoup.nodes.Document;
import org.junit.Test;
import uk.co.sainsburys.exercise.calculator.Calculator;
import uk.co.sainsburys.exercise.output.Output;
import uk.co.sainsburys.exercise.output.impl.FileWriterOutput;
import uk.co.sainsburys.exercise.page.WebPage;
import uk.co.sainsburys.exercise.parser.Parser;
import uk.co.sainsburys.exercise.parser.impl.RipeFruitsParser;
import uk.co.sainsburys.exercise.properties.PropertiesReader;

import static org.mockito.Mockito.*;

public class WebScraperTest {

    @Test
    public void givenAWebScrapShouldWriteEmptyInFile() throws Exception {

        final PropertiesReader propertiesReader = mock(PropertiesReader.class);
        final Parser parser = mock(RipeFruitsParser.class);
        final WebPage webPage = mock(WebPage.class);
        final Calculator calculator = mock(Calculator.class);
        final Document document = mock(Document.class);
        final Output output = mock(FileWriterOutput.class);

        when(webPage.connectTo(anyString())).thenReturn(document);

        WebScraper webScraper = new WebScraper(propertiesReader, parser, webPage, calculator, output);
        webScraper.scrape();

        verify(propertiesReader, times(2)).get(anyString());
        verify(parser, times(1)).parse(document);
        verify(webPage, times(1)).connectTo(anyString());
        verify(calculator, times(1)).getTotalPrice(anyList());
        verify(output, times(1)).writeTo(anyString(), anyDouble(), anyList());

    }

}