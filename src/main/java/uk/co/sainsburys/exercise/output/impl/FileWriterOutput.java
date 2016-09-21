package uk.co.sainsburys.exercise.output.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uk.co.sainsburys.exercise.model.RipeFruit;
import uk.co.sainsburys.exercise.output.Output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to write the output to a file.
 */
public class FileWriterOutput implements Output {

    public void writeTo(String fileName, double total, List<RipeFruit> ripeFruitList) throws IOException {
        try (Writer writer = new FileWriter(fileName)) {
            Map<String, Object> map = new HashMap<>();
            map.put("results", ripeFruitList);
            map.put("total", total);
            Gson json = new GsonBuilder().setPrettyPrinting().create();
            writer.write(json.toJson(map));
        }
    }
}
