package ru.doneathome.functional;

import ru.doneathome.model.Configuration;

import java.io.IOException;

public interface ConfigurationFunctionalAPI {

    Configuration readConfiguration (String fileName) throws IOException;
    void writeConfiguration (String fileName, Configuration configuration) throws IOException;
    void deleteConfiguration (String fileName) throws IOException;

}
