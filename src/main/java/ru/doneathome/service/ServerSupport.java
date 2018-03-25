package ru.doneathome.service;

import java.util.Map;

public class ServerSupport extends Thread {

    private static volatile ServerSupport serverSupport;
    private Map<Integer,ServerService.ServerThread> openServers;

    private ServerSupport(){}

    public static ServerSupport getServerSupport(Map<Integer,ServerService.ServerThread> openServers) {
        if (serverSupport == null) {
            synchronized (ServerSupport.class) {
                if (serverSupport == null)
                    serverSupport = new ServerSupport();
            }
        }
        serverSupport.openServers = openServers;
        return serverSupport;
    }

    public void startProcess() {
        if(!this.isAlive()) {
            this.start();
        }
    }

    public void stopProcess() {
        if(this.isAlive()) {
            this.interrupt();
        }
    }

    private void verifyOpenServers() {
        for (Map.Entry<Integer,ServerService.ServerThread> openServer : openServers.entrySet()) {
            if(!openServer.getValue().isAlive()) {
                openServers.remove(openServer.getKey());
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            verifyOpenServers();
            if(interrupted()) {
                break;
            }
        }
    }
}