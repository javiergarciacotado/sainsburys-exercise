package uk.co.sainsburys.exercise.parser;

import org.jsoup.nodes.Document;
import uk.co.sainsburys.exercise.model.RipeFruit;

import java.util.List;

/**
 * Defines behaviour to parse the HTML Document
 */
public interface Parser {

    List<RipeFruit> parse(Document document);

}
