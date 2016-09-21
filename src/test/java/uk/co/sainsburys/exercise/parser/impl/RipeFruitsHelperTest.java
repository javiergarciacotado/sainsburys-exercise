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
import uk.co.sainsburys.exercise.parser.RipeFruitsHelper;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( RipeFruitsHelper.class)
public class RipeFruitsHelperTest {

    @Before
    public void setup() {
        PowerMockito.mockStatic(RipeFruitsHelper.class);
    }

    @Test
    public void givenAnEmptyProductElementReturnEmptyRipeFruit() throws Exception {

        final String emptyProduct = new String(Files.readAllBytes(Paths.get("src/test/resources/html/empty_product.html")));
        final Document document = Jsoup.parse(emptyProduct);
        final Element product = document.getElementsByClass("product").first();
        final RipeFruit expectedRipeFruit = new RipeFruit("","", 0.0D, "");

        when(RipeFruitsHelper.class, "parseRipeFruits", product).thenCallRealMethod();

        final RipeFruit ripeFruit = RipeFruitsHelper.parseRipeFruits(product);

        assertNotNull(ripeFruit);
        assertEquals(expectedRipeFruit, ripeFruit);
    }

    @Test
    public void givenAValidProductElementReturnRipeFruit() throws Exception {
        final String emptyProduct = new String(Files.readAllBytes(Paths.get("src/test/resources/html/valid_product.html")));
        final Document document = Jsoup.parse(emptyProduct);
        final Element product = document.getElementsByClass("product").first();
        final Element productInfo = product.select("div.productInfoWrapper > div.productInfo").first();
        final Element productTrolley = product.select("div.addToTrolleytabBox").first();

        final RipeFruit expectedRipeFruit = new RipeFruit("Sainsbury's Apricot Ripe & Ready x5","Apricots", 1.80D, "34kb");

        when(RipeFruitsHelper.class, "parseRipeFruits", product).thenCallRealMethod();
        when(RipeFruitsHelper.class, "getTitle", productInfo).thenCallRealMethod();
        when(RipeFruitsHelper.class, "getDescription", productInfo).thenReturn("Apricots");
        when(RipeFruitsHelper.class, "getUnitPrice", productTrolley).thenReturn(1.80D);
        when(RipeFruitsHelper.class, "getSizeDetailsPage", productInfo).thenReturn("34kb");

        final RipeFruit ripeFruit = RipeFruitsHelper.parseRipeFruits(product);

        assertNotNull(ripeFruit);
        assertEquals(expectedRipeFruit, ripeFruit);
    }

}