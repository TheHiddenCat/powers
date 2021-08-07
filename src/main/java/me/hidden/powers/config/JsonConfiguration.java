package me.hidden.powers.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonConfiguration {
    protected final File folder;
    protected File file;
    protected JSONObject data;

    public JsonConfiguration(File folder, String fileName) {
        this.folder = folder;
        this.file = new File(this.folder, fileName);
        this.data = null;
    }

    public JSONObject getData() {
        return data;
    }

    public void create(String data) {
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try(var writer = new FileWriter(file.getAbsolutePath())) {
            writer.write(data);
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try (var reader = new FileReader(file)) {
            var parser = new JSONParser();
            data = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
