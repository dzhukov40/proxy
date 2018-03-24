package ru.doneathome.service;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.State.TERMINATED;

public class ServerService {



    private Map<Integer,ThreadProxy> openServers = new ConcurrentHashMap<>();



    public static void main(String[] args) {

        ServerService serverService = new ServerService();

        // на виртуалке [192.168.56.101] запускаем сервер [ nc -k -l 4444 ]
        serverService.startServer(3000, "192.168.56.101", 4444);


        Boolean status;

        while (true) {
            try {

                Thread.sleep(2000);
                status = serverService.isServerRun(3000);
                System.out.println("isServerRun? " + status);

                if (status.equals(false)) {
                    serverService.startServer(3000, "192.168.56.101", 4444);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //TODO: вот тут не ясно в map навернулся сервачек, надо его закрыть
    public void startServer(int localPort, String remoteAddress, int remotePort) {

        try {
            System.out.println("Starting proxy for [" + localPort + "]->[" + remoteAddress + ":" + remotePort +"]");
            ServerSocket server = new ServerSocket(localPort);

            ThreadProxy threadProxy;

            while (!openServers.containsKey(localPort)) {
                threadProxy = new ThreadProxy(server.accept(), remoteAddress, remotePort);
                openServers.put(localPort, threadProxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Boolean isServerRun(int localPort) {

        return openServers.get(localPort).isAlive();

    }


}

