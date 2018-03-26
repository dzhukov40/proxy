package ru.doneathome.model;

import java.util.List;
import java.util.Objects;

public class Profile {

    String name;

    List<Pipe> pipes;

    public Profile() {}

    public Profile(List<Pipe> pipes) {
        this.pipes = pipes;
    }

    public Profile(String name, List<Pipe> pipes) {
        this.name = name;
        this.pipes = pipes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public void setPipes(List<Pipe> pipes) {
        this.pipes = pipes;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", pipes=" + pipes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(name, profile.name) &&
                Objects.equals(pipes, profile.pipes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, pipes);
    }
}
