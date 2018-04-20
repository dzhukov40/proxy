package ru.doneathome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.doneathome.model.Configuration;
import ru.doneathome.model.Pipe;
import ru.doneathome.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*") //разрешает делать запрос с других доменов
@RequestMapping("/configuration")
public class ConfigurationController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Configuration getConfiguration() {

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

}
