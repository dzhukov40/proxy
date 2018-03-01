package ru.doneathome.functional;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.doneathome.model.Configuration;
import ru.doneathome.model.Pipe;

public class ConfigurationFunctional {


    public Configuration readConfiguration (String fileName) throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get(fileName));

        ObjectMapper objectMapper = new ObjectMapper();
        Configuration configuration = objectMapper.readValue(jsonData, Configuration.class);
        return configuration;
    }

    public void writeConfiguration (String fileName, Configuration configuration) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        FileWriter fileWriter = new FileWriter(fileName);
        objectMapper.writeValue(fileWriter, configuration);
    }



}
