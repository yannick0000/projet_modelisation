package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeToJsonFile(Object object, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T readFromJsonFile(String filePath, Type type) {
        File file = new File(filePath);
        if (!file.exists()) {
            // Créer un fichier vide s'il n'existe pas
            writeToJsonFile(new ArrayList<>(), filePath);
        }
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
