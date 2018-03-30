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




    public static void main(String[] args) {

        ServerService serverService = new ServerService();

        // на виртуалке [192.168.56.101] запускаем сервер [ nc -k -l 4444 ]
        try {
            // serverService.startServer(3000, "192.168.56.101", 4444);
            serverService.startServer(3000, "localhost", 6000);
        } catch (OpenServerException e) {
            e.printStackTrace();
        }


        ServerStatus status;


        try {
            Thread.sleep(10000);
            System.out.println("Stop Server: " + 3000);
            serverService.stopServer(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }


        while (true) {
            try {


                Thread.sleep(2000);
                status = serverService.getServerStatus(3000);
                System.out.println("isAliveConnection? " + status);


            } catch (InterruptedException e) {
                e.printStackTrace();
           // } catch (SocketException e) {
          //      e.printStackTrace();
           // } catch (OpenServerException e) {
           //     e.printStackTrace();
            }
        }

    }


    private ServerService(){}

    public static ServerService getServerService() {
        if (serverService == null){
            synchronized (ServerService.class) {
                if (serverService == null) {
                    serverService = new ServerService();
                    serverSupport.startProcess();
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


/*        if (getServerStatus(localPort).equals(ServerStatus.WAIT_CONNECTION) && openServers.get(localPort) != null) {
            serverSupport.startProcess();
        }*/
    }

    //TODO: не работает закрытие сервера
    public void stopServer(int localPort) throws Exception {
        openServers.get(localPort).interrupt();

        //serverSupport.stopProcess();
        //serverSupport.interrupt();

        // while (!getServerStatus(localPort).equals(ServerStatus.STOPPED));
    }

    public void stopServer() {
        serverSupport.stopProcess();
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
        final int CHECK_STATUS_PAUSE = 100;

        volatile Boolean statusAliveConnection = false;

        ServerThread(int localPort, String remoteAddress, int remotePort) {
            this.localPort = localPort;
            this.remoteAddress = remoteAddress;
            this.remotePort = remotePort;
        }

        public Boolean getStatusAliveConnection(){
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
                    if(isInterrupted()){
                        statusAliveConnection = false;
                        if (threadProxy != null) {
                            threadProxy.interrupt();
                        }
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }




        }

    }


}

