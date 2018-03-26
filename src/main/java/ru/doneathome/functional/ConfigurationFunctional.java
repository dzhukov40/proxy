package ru.doneathome.functional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.doneathome.model.Configuration;
import ru.doneathome.model.Pipe;

/**
 * читаем/записываем конфигурацию в файл
 * - формат JSON
 */
public class ConfigurationFunctional {

    public Configuration readConfiguration (String fileName) throws IOException {

        String filePath = System.getProperty("user.dir").concat(System.getProperty("path.separator")).concat(fileName);
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, Configuration.class);
    }

    public void writeConfiguration (String fileName, Configuration configuration) throws IOException {

        String filePath = System.getProperty("user.dir").concat(System.getProperty("path.separator")).concat(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        FileWriter fileWriter = new FileWriter(filePath);
        objectMapper.writeValue(fileWriter, configuration);
    }

    public void deleteConfiguration (String fileName) throws IOException {
        String filePath = System.getProperty("user.dir").concat(System.getProperty("path.separator")).concat(fileName);
        if (!new File(filePath).delete()) {
            throw new IOException("file " + filePath + " not deleted!");
        }

    }



}
