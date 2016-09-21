package uk.co.sainsburys.exercise.output.impl;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.sainsburys.exercise.model.RipeFruit;
import uk.co.sainsburys.exercise.output.Output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class FileWriterOutputIT {

    private static final String TEST_FILE = "test.txt";
    private Output output;

    @Before
    public void setup() {
        output = new FileWriterOutput();
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test(expected = IOException.class)
    public void givenAnEmptyFileNameShouldThrowException() throws Exception {
        output.writeTo("", 0.0D, Collections.emptyList());
        fail("should have thrown FileNotFoundException");
    }

    @Test
    public void givenAFileNameShouldWriteToFile() throws IOException {

        RipeFruit ripeFruit1 = new RipeFruit("title 1", "description 1", 1.61D, "35kb");
        RipeFruit ripeFruit2 = new RipeFruit("title 2", "description 2", 2.22D, "40kb");
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        double total = Double.parseDouble(twoDForm.format(ripeFruit1.getUnitPrice() + ripeFruit2.getUnitPrice()));
        final List<RipeFruit> ripeFruits = Arrays.asList(ripeFruit1, ripeFruit2);

        output.writeTo(TEST_FILE, total, ripeFruits);

        final String content = new String(Files.readAllBytes(Paths.get(TEST_FILE)));
        RipeFruitWrapper ripeFruitWrapper = new Gson().fromJson(content, RipeFruitWrapper.class);
        assertEquals(3.83D, ripeFruitWrapper.getTotal(), 0.0D);
        assertThat(ripeFruits, hasItems(ripeFruit1, ripeFruit2));
    }
}