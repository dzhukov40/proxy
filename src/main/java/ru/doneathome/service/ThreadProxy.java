package ru.doneathome.service;

import java.io.*;
import java.net.Socket;


/**
 * Ограничение: сервер что-то возвращает назад как понять кому
 *   - разрешено только одно подключение!
 */
public class ThreadProxy extends Thread {

    private Socket sClient;
    private final String SERVER_URL;
    private final int SERVER_PORT;

    private final int BUF_REQUEST_SIZE = 4096;
    private final int BUF_RESPONSE_SIZE = 4096;


    ThreadProxy(Socket sClient, String ServerUrl, int ServerPort) {
        this.SERVER_URL = ServerUrl;
        this.SERVER_PORT = ServerPort;
        this.sClient = sClient;
        System.out.println("New client [" + sClient + "]->[" + ServerUrl + ":" + ServerPort +"]");
        this.start();
    }

    @Override
    public void run() {
        try {
            final byte[] request = new byte[BUF_REQUEST_SIZE];
            byte[] reply = new byte[BUF_RESPONSE_SIZE];
            final InputStream inFromClient = sClient.getInputStream();
            final OutputStream outToClient = sClient.getOutputStream();
            Socket client = null;
            Socket server = null;

            // connects a socket to the server
            try {
                server = new Socket(SERVER_URL, SERVER_PORT);
            } catch (IOException e) {
                PrintWriter out = new PrintWriter(new OutputStreamWriter(outToClient));
                out.flush();
                throw new RuntimeException(e);
            }

            // потоки от сервера
            final InputStream inFromServer = server.getInputStream();
            final OutputStream outToServer = server.getOutputStream();

            // делаем поток передачи данных CLIENT -> SERVER
            new Thread(() -> {
                int bytes_read;
                try {
                    while ((bytes_read = inFromClient.read(request)) != -1) {
                        outToServer.write(request, 0, bytes_read);
                        outToServer.flush();
                    }
                } catch (IOException e) {
                }
                try {
                    outToServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // делаем поток передачи данных SERVER -> CLIENT
            int bytes_read;
            try {
                while ((bytes_read = inFromServer.read(reply)) != -1) {
                    outToClient.write(reply, 0, bytes_read);
                    outToClient.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (server != null)
                        server.close();
                    if (client != null)
                        client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outToClient.close();
            sClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
