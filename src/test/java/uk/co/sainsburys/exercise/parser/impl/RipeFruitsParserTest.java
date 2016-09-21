package uk.co.sainsburys.exercise.parser.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import uk.co.sainsburys.exercise.model.RipeFruit;
import uk.co.sainsburys.exercise.parser.Parser;
import uk.co.sainsburys.exercise.parser.RipeFruitsHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( RipeFruitsHelper.class)
public class RipeFruitsParserTest {

    private static final String EMPTY_PRODUCT_FILE = "src/test/resources/html/empty_product.html";
    private Parser ripeFruitsParser;

    @Before
    public void setUp() {
        ripeFruitsParser = new RipeFruitsParser();
    }

    @Test
    public void givenANullHtmlDocumentReturnEmpty() {
        final List<RipeFruit> ripeFruits = ripeFruitsParser.parse(null);
        assertTrue(ripeFruits.isEmpty());
    }

    @Test
    public void givenAnEmptyHtmlDocumentReturnEmpty() {
        final Document htmlDocument = parseDocument("");
        final List<RipeFruit> ripeFruits = ripeFruitsParser.parse(htmlDocument);
        assertTrue(ripeFruits.isEmpty());
    }

    @Test
    public void givenAValidHtmlEmptyProductDocumentShouldReturnEmpty() throws IOException {
        final String content = getFile();
        Document htmlDocument = parseDocument(content);

        PowerMockito.mockStatic(RipeFruitsHelper.class);
        RipeFruit ripeFruit = mock(RipeFruit.class);
        Element element = mock(Element.class);
        when(RipeFruitsHelper.parseRipeFruits(element)).thenReturn(ripeFruit);

        final List<RipeFruit> ripeFruits = ripeFruitsParser.parse(htmlDocument);

        assertFalse(ripeFruits.isEmpty());
    }

    private String getFile() throws IOException {
        final Path path = Paths.get(EMPTY_PRODUCT_FILE);
        return new String(Files.readAllBytes(path));
    }

    private Document parseDocument(String content) {
        return Jsoup.parse(content);
    }
}