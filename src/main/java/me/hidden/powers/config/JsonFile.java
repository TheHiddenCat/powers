package me.hidden.powers.config;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonFile {
    protected final File folder;
    protected File file;
    protected JSONArray data;

    public JsonFile(File folder, String fileName) {
        this.folder = folder;
        this.file = new File(this.folder, fileName);
        this.data = null;
    }

    public void create() {
        try {
            if (!file.exists()) {
                file.createNewFile();
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
