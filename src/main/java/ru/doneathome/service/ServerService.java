package ru.doneathome.service;


import ru.doneathome.exeptions.OpenServerException;

import java.io.*;
import java.net.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class ServerService {

    private static volatile ServerService serverService;
    private static Map<Integer,ServerThread> openServers = new ConcurrentHashMap<>();
    private static ServerSupport serverSupport = ServerSupport.getServerSupport(openServers);


    private ServerService(){}

    public static ServerService getServerService() {
        if (serverService == null){
            synchronized (ServerService.class) {
                if (serverService == null) {
                    serverService = new ServerService();
                    // serverSupport.startProcess();
                }
            }
        }
        return serverService;
    }


    public void startServer(int localPort, String remoteAddress, int remotePort) throws OpenServerException {

        if (openServers.containsKey(localPort)) {
            throw new OpenServerException("The server is already running");
        }

        ServerThread serverThread = new ServerThread(localPort, remoteAddress, remotePort);

        serverThread.start();

        openServers.put(localPort, serverThread);


        while (getServerStatus(localPort).equals(ServerStatus.STOPPED));

/*        if (getServerStatus(localPort).equals(ServerStatus.WAIT_CONNECTION) && openServers.get(localPort) != null) {
            serverSupport.startProcess();
        }*/
    }

    //TODO: не работает закрытие сервера
    public void stopServer(int localPort) {
        ServerThread serverThread = openServers.get(localPort);

        if (serverThread == null) {
            return;
        }

        serverThread.interrupt();
        openServers.remove(localPort);

        //serverSupport.stopProcess();
        //serverSupport.interrupt();

/*        for (Map.Entry<Integer,ServerService.ServerThread> openServer : openServers.entrySet()) {
            if(openServer.getValue().getCanKill()) {
                openServers.remove(openServer.getKey());
            }
        }*/

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

    /**
     * Это inner класс который отвечает за один проборос портов
     */
    class ServerThread extends Thread {

        final int localPort;
        final String remoteAddress;
        final int remotePort;
        final int CHECK_STATUS_PAUSE = 1;
        final int WAIT_CLOSE_PAUSE = 100;

        volatile Boolean statusAliveConnection = false;
        volatile Boolean canKill = false;


        ServerThread(int localPort, String remoteAddress, int remotePort) {
            this.localPort = localPort;
            this.remoteAddress = remoteAddress;
            this.remotePort = remotePort;
        }

        public Boolean getStatusAliveConnection() {
            return statusAliveConnection;
        }

        public Boolean getCanKill() {
            return canKill;
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
                canKill = true;
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


            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (threadProxy != null) {
                threadProxy.interrupt();
            }
            canKill = true;
        }

    }


}

