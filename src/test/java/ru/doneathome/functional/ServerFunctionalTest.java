package ru.doneathome.functional;


import org.junit.jupiter.api.Test;
import ru.doneathome.model.Configuration;
import ru.doneathome.model.Pipe;
import ru.doneathome.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerFunctionalTest {

    // Вспромогательный метод для создания экземпляра класса конфигурации
    Configuration createConfiguration() {

        final int LOCAL_PORT = 3000;
        final int REMOTE_PORT = 4000;

        int a = 0;
        int b = 0;

        List<Pipe> pipes1 = new ArrayList<>();
        pipes1.add(new Pipe("1_first", LOCAL_PORT + a++, "localhost", REMOTE_PORT + b++));
        pipes1.add(new Pipe("1_two", LOCAL_PORT + a++, "localhost", REMOTE_PORT + b++));
        pipes1.add(new Pipe("1_three", LOCAL_PORT + a++, "localhost", REMOTE_PORT + b++));
        pipes1.add(new Pipe("1_four", LOCAL_PORT + a++, "localhost", REMOTE_PORT + b++));

        List<Pipe> pipes2 = new ArrayList<>();
        pipes2.add(new Pipe("2_first", LOCAL_PORT + a++, "localhost", REMOTE_PORT + b++));
        pipes2.add(new Pipe("2_two", LOCAL_PORT + a++, "localhost", REMOTE_PORT + b++));
        pipes2.add(new Pipe("2_three", LOCAL_PORT + a++, "localhost", REMOTE_PORT + b++));
        pipes2.add(new Pipe("2_four", LOCAL_PORT + a, "localhost", REMOTE_PORT + b));

        Profile profile1 = new Profile("tort", pipes1);
        Profile profile2 = new Profile("Djo",pipes2);

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        return new Configuration(profiles);
    }


    @Test
    public void openPipesTest() {
        ServerFunctional serverFunctional = new ServerFunctional();

        Configuration configuration = createConfiguration();
        Profile profile = configuration.getProfiles().get(0);

        if (serverFunctional.getOpenPipes().size() != 0) {
            System.out.println("We have open servers");
            assert false;
        }

        serverFunctional.openProfilePipes(profile);

        if (serverFunctional.getOpenPipes().size() != profile.getPipes().size()) {
            System.out.println("Not all pipes was open");
            assert false;
        }

        serverFunctional.closeAllPipes();

        if (serverFunctional.getOpenPipes().size() != 0) {
            System.out.println("We have open servers.");
            assert false;
        }

    }


}
