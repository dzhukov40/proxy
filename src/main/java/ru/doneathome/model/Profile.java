package ru.doneathome.model;

import java.util.List;

public class Profile {

    String name;

    List<Pipe> pipes;

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
}
