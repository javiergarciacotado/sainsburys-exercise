package uk.co.sainsburys.exercise.parser.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.sainsburys.exercise.model.RipeFruit;
import uk.co.sainsburys.exercise.parser.Parser;
import uk.co.sainsburys.exercise.parser.RipeFruitsHelper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Specific Ripe Fruits parser for JSOUP Document
 */
public class RipeFruitsParser implements Parser {

    /**
     * Parses the document given
     * @param document Document to parse
     * @return List of ripe fruits processed
     */
    @Override
    public List<RipeFruit> parse(Document document) {
        if (document != null) {
            final Optional<Element> productsDiv = Optional.ofNullable(document.select("ul.productLister.listView").first());
            if (productsDiv.isPresent()) {
                final Optional<Elements> products = Optional.ofNullable(productsDiv.get().select("div.product"));
                if (products.isPresent()) {
                    return products.get().stream().map(RipeFruitsHelper::parseRipeFruits).collect(Collectors.toList());
                }
            }
        }
        return Collections.emptyList();
    }

}
