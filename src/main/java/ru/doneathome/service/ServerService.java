package ru.doneathome.service;


import ru.doneathome.exeptions.OpenServerException;

import java.io.*;
import java.net.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class ServerService {



    private Map<Integer,ServerThread> openServers = new ConcurrentHashMap<>();




    public static void main(String[] args) {

        ServerService serverService = new ServerService();

        // на виртуалке [192.168.56.101] запускаем сервер [ nc -k -l 4444 ]
        try {
            // serverService.startServer(3000, "192.168.56.101", 4444);
            serverService.startServer(3000, "localhost", 6000);
        } catch (OpenServerException e) {
            e.printStackTrace();
        }


        Boolean status = false;


/*        try {
            Thread.sleep(10000);
            System.out.println("Stop Server: " + 3000);
            serverService.stopServer(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }*/


        while (true) {
            try {


                Thread.sleep(2000);
               // status = serverService.isAliveConnection(3000);
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


    public void startServer(int localPort, String remoteAddress, int remotePort) throws OpenServerException {

        if(openServers.containsKey(localPort)){
            throw new OpenServerException("The server is already running");
        }

        ServerThread serverThread = new ServerThread(localPort, remoteAddress, remotePort);

        serverThread.start();

        openServers.put(localPort,serverThread);

        ServerSupport.getServerSupport(openServers).startProcess();

    }

    //TODO: не работает закрытие сервера
    public void stopServer(int localPort) throws Exception {
        openServers.get(localPort).interrupt();
    }

    public Boolean isServerRun(int localPort) {
        return openServers.get(localPort).isAlive();
    }

    public Boolean isAliveConnection(int localPort) {
        return openServers.get(localPort).getConnectionStatus();
    }



    class ServerThread extends Thread {

        final int localPort;
        final String remoteAddress;
        final int remotePort;
        volatile Boolean statusAliveConnection = false;

        ServerThread(int localPort, String remoteAddress, int remotePort) {
            this.localPort = localPort;
            this.remoteAddress = remoteAddress;
            this.remotePort = remotePort;
        }

        public Boolean getConnectionStatus(){
            return statusAliveConnection;
        }

        @Override
        public void run() {

            ServerSocket server = null;
            ThreadProxy threadProxy = null;
            try {
                System.out.println("Starting proxy for [" + localPort + "]->[" + remoteAddress + ":" + remotePort +"]");
                server = new ServerSocket(localPort);
                server.setSoTimeout(100);
            } catch (Exception e) {
                return;
            }


            while (true) {

                try {
                    if (!statusAliveConnection) {
                        threadProxy = new ThreadProxy(server.accept(), remoteAddress, remotePort);
                    }

                    statusAliveConnection = threadProxy.isAlive();

/*                    if(isInterrupted()){
                        statusAliveConnection = false;
                        threadProxy.interrupt();
                    }*/

                } catch (SocketTimeoutException ignored){

                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }




        }

    }


}

