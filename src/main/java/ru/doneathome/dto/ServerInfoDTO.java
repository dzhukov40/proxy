package ru.doneathome.dto;

import java.util.Objects;

public class ServerInfoDTO {

    private int localPort;
    private String remoteAddress;
    private int remotePort;
    private boolean isAlive = false;


    public ServerInfoDTO(int localPort, String remoteAddress, int remotePort) {
        this.localPort = localPort;
        this.remoteAddress = remoteAddress;
        this.remotePort = remotePort;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerInfoDTO that = (ServerInfoDTO) o;
        return localPort == that.localPort &&
                remotePort == that.remotePort &&
                Objects.equals(remoteAddress, that.remoteAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(localPort, remoteAddress, remotePort);
    }

    @Override
    public String toString() {
        return "ServerInfoDTO{" +
                "localPort=" + localPort +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", remotePort=" + remotePort +
                '}';
    }
}
