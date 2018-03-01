package ru.doneathome.model;

import java.util.LinkedList;
import java.util.List;

public class Configuration {

    private List<Profile> profiles = new LinkedList<>();


    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "profiles=" + profiles +
                '}';
    }
}
