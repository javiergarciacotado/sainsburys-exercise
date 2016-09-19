package uk.co.sainsburys.exercise.page;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WebPageTest {

    private static final String INVALID_URL = "http://some-url";
    private static final String VALID_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    private WebPage webPage;

    @Before
    public void setup() {
        webPage = mock(WebPage.class);
    }

    @Test(expected = UnknownHostException.class)
    public void givenAnInvalidURLThrowException() throws IOException {
        when(webPage.connectTo(INVALID_URL)).thenThrow(UnknownHostException.class);
        webPage.connectTo(INVALID_URL);
        fail("Should have thrown an Exception");
    }

    @Test
    public void givenAValidURLShouldConnect() throws IOException {

        final Document document = mock(Document.class);
        when(document.title()).thenReturn("Test");
        when(webPage.connectTo(VALID_URL)).thenReturn(document);

        final Document resultDocument = webPage.connectTo(VALID_URL);

        assertNotNull(resultDocument);
        assertEquals("Test", resultDocument.title());

    }



}
