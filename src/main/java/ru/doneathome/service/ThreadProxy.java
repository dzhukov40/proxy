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


    ThreadProxy(Socket sClient, String ServerUrl, int ServerPort) {
        this.SERVER_URL = ServerUrl;
        this.SERVER_PORT = ServerPort;
        this.sClient = sClient;
        System.out.println("New client [" + sClient + "]->[" + ServerUrl + ":" + ServerPort +"]");
        this.start();
    }

    @Override
    public void run() {

        Socket server = null;

        try {
            server = new Socket(SERVER_URL, SERVER_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 4-е потока наши
        InputStream inFromClient = null;
        OutputStream outToClient = null;

        InputStream inFromServer = null;
        OutputStream outToServer = null;

        try {
            inFromServer = server.getInputStream();
            outToServer = server.getOutputStream();

            inFromClient = sClient.getInputStream();
            outToClient = sClient.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }


        // делаем потоки передачи данных CLIENT -> SERVER и SERVER -> CLIENT
        StreamSocketIO streamSocketClientServer = new StreamSocketIO(inFromClient,outToServer);
        StreamSocketIO streamSocketServerClient = new StreamSocketIO(inFromServer,outToClient);

        System.out.println("Start Threads");
        streamSocketClientServer.start();
        streamSocketServerClient.start();

        // любой из потоков валится надо прекрывать лавочку
        boolean status = true;
        while (status) {
            status = streamSocketClientServer.isAlive() && streamSocketServerClient.isAlive();
        }

        System.out.println("Stop Threads");
        streamSocketClientServer.interrupt();
        streamSocketServerClient.interrupt();


    }

    /**
     * это мост соединящий входной и выходной поток
     */
    class StreamSocketIO extends Thread {

        private final int BUF_SIZE = 4096;
        final byte[] BUF = new byte[BUF_SIZE];

        final InputStream inputStream;
        final OutputStream outputStream;



        StreamSocketIO(InputStream inputStream, OutputStream outputStream) {
            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {

            int bytes_read;
            try {
                while ((bytes_read = inputStream.read(BUF)) != -1) {
                    outputStream.write(BUF, 0, bytes_read);
                    outputStream.flush();
                    if(isInterrupted()) {
                        break; // прерывание внешнее для закрытия потока
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
