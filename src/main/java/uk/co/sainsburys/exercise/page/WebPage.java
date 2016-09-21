package uk.co.sainsburys.exercise.page;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Establishes the connection to the site
 */
public class WebPage {

    /**
     *
     * @param url Web site address
     * @return Document (html) obtained from the given url
     * @throws IOException Can't connect to the given url
     */
    public Document connectTo(final String url) throws IOException {
        final Connection connection = Jsoup.connect(url);
        return connection.get();
    }
}
