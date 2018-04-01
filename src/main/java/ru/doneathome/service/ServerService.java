package ru.doneathome.service;


import ru.doneathome.exeptions.OpenServerException;

import java.io.*;
import java.net.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class ServerService {

    private static volatile ServerService serverService;
    private static Map<Integer,ServerThread> openServers = new ConcurrentHashMap<>();
    // private static ServerSupport serverSupport = ServerSupport.getServerSupport(openServers);


    private ServerService(){}

    public static ServerService getServerService() {
        if (serverService == null){
            synchronized (ServerService.class) {
                if (serverService == null) {
                    serverService = new ServerService();
                }
            }
        }
        // serverService.verifyOpenServers();
        return serverService;
    }


    public void startServer(int localPort, String remoteAddress, int remotePort) throws OpenServerException {
        //verifyOpenServers();
        if (openServers.containsKey(localPort)) {
            throw new OpenServerException("The server is already running");
        }

        ServerThread serverThread = new ServerThread(localPort, remoteAddress, remotePort);
        serverThread.start();
        openServers.put(localPort, serverThread);

        while (getServerStatus(localPort).equals(ServerStatus.STOPPED));
    }

    public void stopServer(int localPort) {
        //verifyOpenServers();
        ServerThread serverThread = openServers.get(localPort);
        if (serverThread == null) {
            return;
        }
        serverThread.interrupt();
        openServers.remove(localPort);

        while (!getServerStatus(localPort).equals(ServerStatus.STOPPED) && openServers.get(localPort) == null);
    }

    public ServerStatus getServerStatus(int localPort) {
        ServerThread serverThread = openServers.get(localPort);

        if (serverThread == null || !serverThread.isAlive()) {
            return ServerStatus.STOPPED;
        } else if (serverThread.getStatusAliveConnection()) {
            return ServerStatus.HAS_ACTIVE_CONNECTION;
        } else {
            return ServerStatus.WAIT_CONNECTION;
        }
    }

    public void verifyOpenServers() {
        openServers.forEach((key, value) -> {
            if (!value.isAlive())
                openServers.remove(key);
        });
    }

    public Set<ServerThread> getOpenServers() {
        Set<ServerThread> openServerThreads = new HashSet<>();

        for (ServerThread serverThread : openServerThreads) {
            if (serverThread.isAlive()) {
                openServerThreads.add(serverThread);
            }
        }

        return openServerThreads;
    }



    /**
     * Это inner класс который отвечает за один проборос портов
     */
    class ServerThread extends Thread {

        final int localPort;
        final String remoteAddress;
        final int remotePort;
        final int CHECK_STATUS_PAUSE = 1;

        volatile Boolean statusAliveConnection = false;


        ServerThread(int localPort, String remoteAddress, int remotePort) {
            this.localPort = localPort;
            this.remoteAddress = remoteAddress;
            this.remotePort = remotePort;
        }

        public Boolean getStatusAliveConnection() {
            return statusAliveConnection;
        }


        @Override
        public void run() {

            ServerSocket server = null;
            ThreadProxy threadProxy = null;
            try {
                System.out.println("Starting proxy for [" + localPort + "]->[" + remoteAddress + ":" + remotePort +"]");
                server = new ServerSocket(localPort);
                server.setSoTimeout(CHECK_STATUS_PAUSE);
            } catch (Exception e) {
                return;
            }

            while (true) {
                Socket socket;
                try {
                    if (!statusAliveConnection) {
                        socket = server.accept();
                        if (socket != null) {
                            threadProxy = new ThreadProxy(socket, remoteAddress, remotePort);
                            socket = null;
                        }
                    }

                    statusAliveConnection = threadProxy.isAlive();

                    if (isInterrupted()) {
                        throw new SocketTimeoutException();
                    }

                } catch (SocketTimeoutException ignored){
                    // Исключение как закончится тайм аут у "server.accept()" + попадаем при прерывании
                    if(isInterrupted()) {
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
            }

            // что - то отвалилось, вырубаем поток, связывающий два сокета
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (threadProxy != null) {
                threadProxy.interrupt();
                while (threadProxy.isAlive());
            }
        }
    }

}

