package uk.co.sainsburys.exercise.page;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class WebPageIT {

    private static final String INVALID_URL = "invalid-url";
    private static final String VALID_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    private WebPage webPage;

    @Before
    public void setup() {
        webPage = new WebPage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnInvalidURLThrowException() throws IOException {
        webPage.connectTo(INVALID_URL);
        fail("Should have thrown an Exception");
    }

    @Test
    public void givenAValidURLShouldConnect() throws IOException {
        final Document resultDocument = webPage.connectTo(VALID_URL);

        assertNotNull(resultDocument);
        assertEquals("Ripe & ready | Sainsbury\'s", resultDocument.title());

    }
}
