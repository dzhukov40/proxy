package ru.doneathome.service;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerService {



    private Map<Integer,ThreadProxy> openServers = new HashMap<>();

    public static void main(String[] args) {

        ServerService serverService = new ServerService();

        // на виртуалке [192.168.56.101] запускаем сервер [ nc -k -l 4444 ]
        serverService.startServer(3000, "192.168.56.101", 4444);

    }


    public void startServer(int localPort, String remoteAddress, int remotePort) {

        try {
            // Print a start-up message
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


}

