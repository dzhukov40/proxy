package ru.doneathome.model;

import java.util.Objects;

public class Pipe {

    String name;
    int localhostPort;
    String remoteIP;
    int remotePort;


    public Pipe(){}

    public Pipe(String name, int localhostPort, String remoteIP, int remotePort) {
        this.name = name;
        this.localhostPort = localhostPort;
        this.remoteIP = remoteIP;
        this.remotePort = remotePort;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocalhostPort() {
        return localhostPort;
    }

    public void setLocalhostPort(int localhostPort) {
        this.localhostPort = localhostPort;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }


    @Override
    public String toString() {
        return "Pipe{" +
                "name='" + name + '\'' +
                ", localhostPort='" + localhostPort + '\'' +
                ", remoteIP='" + remoteIP + '\'' +
                ", remotePort='" + remotePort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pipe pipe = (Pipe) o;
        return localhostPort == pipe.localhostPort &&
                remotePort == pipe.remotePort &&
                Objects.equals(name, pipe.name) &&
                Objects.equals(remoteIP, pipe.remoteIP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, localhostPort, remoteIP, remotePort);
    }
}
