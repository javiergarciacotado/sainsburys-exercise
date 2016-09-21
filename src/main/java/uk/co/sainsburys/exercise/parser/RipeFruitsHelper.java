package uk.co.sainsburys.exercise.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.sainsburys.exercise.model.RipeFruit;
import uk.co.sainsburys.exercise.page.WebPage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util class which processes each ripe fruit.
 * Selects the elements in the order that appear in the grocery site
 * The class cannot be extended neither instantiated
 */
public final class RipeFruitsHelper {

    private RipeFruitsHelper() {}

    /**
     * Parses each Ripe fruit in the document given
     * @param element Node to process
     * @return RipeFruit POJO
     */
    public static RipeFruit parseRipeFruits(final Element element) {
        try {
            Elements productInner = element.select("div.productInner");
            Elements productInfo = productInner.select("div.productInfoWrapper > div.productInfo");
            Elements productTrolley = productInner.select("div.addToTrolleytabBox");

            Element product = productInfo.get(0);
            String title = getTitle(product);
            String description = getDescription(product);
            String size = getSizeDetailsPage(product);
            double unitPrice = getUnitPrice(productTrolley.get(0));
            return new RipeFruit(title, description, unitPrice, size);
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return new RipeFruit("", "", 0.0D, "");
    }

    /**
     * To retrieve the first title of the product
     * @param product product element
     * @return the title of the product
     */
    private static String getTitle(Element product) {
        final Element title = product.select("h3 > a").first();
        return title != null ? title.text() : "";
    }

    /**
     * To retrieve the unit price of the product
     * @param product product element
     * @return the unit price of the product
     */
    private static double getUnitPrice(Element product) {
        try {
            final Elements pricing = product.getElementsByClass("pricing");
            final Elements pricePerUnit = pricing.get(0).getElementsByClass("pricePerUnit");

            Pattern p = Pattern.compile("\\d*\\.\\d+");
            Matcher m = p.matcher(pricePerUnit.get(0).text());
            if (m.find()) return Double.parseDouble(m.group());
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            exception.printStackTrace();
        }
        return 0.0D;
    }

    /**
     * To retrieve the description of the product
     * @param product product element
     * @return the description of the product
     */
    private static String getDescription(Element product) {
        String description = "";
        try {
            final Document document = getLinkedDocument(product);
            final Elements productText = document.getElementsByClass("productText");
            description = productText != null && productText.size() > 0 ? productText.get(0).text() : "";

        } catch (IOException | IndexOutOfBoundsException | NullPointerException exception) {
            exception.printStackTrace();
        }
        return description;
    }

    /**
     * To retrieve the size details of the linked web page
     * @param product product element
     * @return the size of the linked web page
     */
    private static String getSizeDetailsPage(Element product) {
        try {
            final Document document = getLinkedDocument(product);
            final int size = document.toString().length() / 1024;
            return size + "kb";
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return "";
    }

    /**
     * Util function
     * @param product product element
     * @return Document retrieved
     * @throws IOException While retrieving the webpage
     */
    private static Document getLinkedDocument(final Element product) throws IOException {
        final String href = product.select("h3 > a").attr("href");
        return href != null ? new WebPage().connectTo(href) : new Document("");
    }
}
