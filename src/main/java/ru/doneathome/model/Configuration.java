package ru.doneathome.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Configuration {

    private List<Profile> profiles;


    public Configuration() {}

    public Configuration(List<Profile> profiles) {
        this.profiles = profiles;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(profiles, that.profiles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(profiles);
    }
}
