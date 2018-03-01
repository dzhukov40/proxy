package ru.doneathome.model;

import javafx.beans.property.SimpleStringProperty;

public class Pipe {

    private final SimpleStringProperty name;
    private final SimpleStringProperty localhostPort;
    private final SimpleStringProperty remoteIP;
    private final SimpleStringProperty remotePort;


    public Pipe(){
        this.name = new SimpleStringProperty();
        this.name.set("name");
        this.localhostPort = new SimpleStringProperty();
        this.localhostPort.set("localhostPort");
        this.remoteIP = new SimpleStringProperty();
        this.remoteIP.set("remoteIP");
        this.remotePort = new SimpleStringProperty();
        this.remotePort.set("remotePort");
    }

    public Pipe(SimpleStringProperty name, SimpleStringProperty localhostPort, SimpleStringProperty remoteIP, SimpleStringProperty remotePort) {
        this.name = name;
        this.localhostPort = localhostPort;
        this.remoteIP = remoteIP;
        this.remotePort = remotePort;
    }


    public Pipe(String name, String localhostPort, String remoteIP, String remotePort) {
        this.name = new SimpleStringProperty();
        this.name.set(name);
        this.localhostPort = new SimpleStringProperty();
        this.localhostPort.set(localhostPort);
        this.remoteIP = new SimpleStringProperty();
        this.remoteIP.set(remoteIP);
        this.remotePort = new SimpleStringProperty();
        this.remotePort.set(remotePort);
    }


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLocalhostPort() {
        return localhostPort.get();
    }

    public SimpleStringProperty localhostPortProperty() {
        return localhostPort;
    }

    public void setLocalhostPort(String localhostPort) {
        this.localhostPort.set(localhostPort);
    }

    public String getRemoteIP() {
        return remoteIP.get();
    }

    public SimpleStringProperty remoteIPProperty() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP.set(remoteIP);
    }

    public String getRemotePort() {
        return remotePort.get();
    }

    public SimpleStringProperty remotePortProperty() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort.set(remotePort);
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "name=" + name +
                ", localhostPort=" + localhostPort +
                ", remoteIP=" + remoteIP +
                ", remotePort=" + remotePort +
                '}';
    }
}
