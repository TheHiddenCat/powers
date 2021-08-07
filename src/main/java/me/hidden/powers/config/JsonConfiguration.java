package me.hidden.powers.config;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonConfiguration {
    protected final File folder;
    protected File file;
    protected JSONArray data;

    public JsonConfiguration(File folder, String fileName) {
        this.folder = folder;
        this.file = new File(this.folder, fileName);
        this.data = null;
    }

    public JSONArray getData() {
        return data;
    }

    public void create(String data) {
        try(var writer = new FileWriter(file.getAbsolutePath())) {
            if (!file.exists()) {
                folder.mkdirs();
                writer.write(data);
                writer.flush();
                writer.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try (var reader = new FileReader(file)) {
            var parser = new JSONParser();
            data = (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
