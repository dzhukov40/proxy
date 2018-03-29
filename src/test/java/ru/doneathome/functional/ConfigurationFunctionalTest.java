package ru.doneathome.functional;


import org.junit.jupiter.api.Test;
import ru.doneathome.model.Configuration;
import ru.doneathome.model.Pipe;
import ru.doneathome.model.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigurationFunctionalTest {

    // Вспромогательный метод для создания экземпляра класса конфигурации
    Configuration createConfiguration() {

        Random random = new Random();

        List<Pipe> pipes1 = new ArrayList<>();
        pipes1.add(new Pipe("1_first", random.nextInt(), "localhost", random.nextInt()));
        pipes1.add(new Pipe("1_two", random.nextInt(), "localhost", random.nextInt()));
        pipes1.add(new Pipe("1_three", random.nextInt(), "localhost", random.nextInt()));
        pipes1.add(new Pipe("1_four", random.nextInt(), "localhost", random.nextInt()));

        List<Pipe> pipes2 = new ArrayList<>();
        pipes2.add(new Pipe("2_first", random.nextInt(), "localhost", random.nextInt()));
        pipes2.add(new Pipe("2_two", random.nextInt(), "localhost", random.nextInt()));
        pipes2.add(new Pipe("2_three", random.nextInt(), "localhost", random.nextInt()));
        pipes2.add(new Pipe("2_four", random.nextInt(), "localhost", random.nextInt()));

        Profile profile1 = new Profile("tort", pipes1);
        Profile profile2 = new Profile("Djo",pipes2);

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        return new Configuration(profiles);
    }

    // Тест создаст инстанс конфигурации, запишет его в файл, прочтет, проверит и удалит файл
    @Test
    void writeReadDeleteTest() {

        ConfigurationFunctional configurationFunctional = new ConfigurationFunctional();
        String uniqueFileName = String.valueOf(System.currentTimeMillis()).concat(".txt");

        Configuration configurationWrite = createConfiguration();
        Configuration configurationRead = null;

        try {
            configurationFunctional.writeConfiguration(uniqueFileName, configurationWrite);
            configurationRead = configurationFunctional.readConfiguration(uniqueFileName);
            configurationFunctional.deleteConfiguration(uniqueFileName);
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }

        assertEquals(configurationWrite, configurationRead);
    }
}
