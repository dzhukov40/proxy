package ru.doneathome.service;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import ru.doneathome.exeptions.OpenServerException;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerServiceTest {

    final int LOCAL_PORT = 5000;
    final String LOCAL_ADDRESS = "localhost";
    final String REMOTE_ADDRESS = "localhost";
    final int REMOTE_PORT = 6000;

    final int SERVER_START_TIME = 1;
    final int REMOTE_SERVER_START_TIME = 80;
    final int SERVER_STOP_TIME = 1;
    final int SERVER_PAUSE_TIME = 80;
    final int SERVER_CLOSE_SOCKET_TIME = 80;

    final int COUNT = 5;


    @Test
    public  void oneShotStartStopServer() {
        startStopServer();
    }

    @RepeatedTest(5)
    public void repeatStartStopServer() {
        startStopServer();
    }

    @Test
    public  void oneStartStopConnectionServer() {
        startStopConnectionServer();
    }
    @RepeatedTest(5)
    public void repeatStartStopConnectionServer() {
        startStopConnectionServer();
    }



    public void startStopConnectionServer() {

        ServerService serverService = ServerService.getServerService();

        ServerSocket serverSocket = null;
        Socket socket = null;

        int count = COUNT;

        ServerStatus serverStatus;

        try {
            serverSocket = new ServerSocket(REMOTE_PORT);
            Thread.sleep(REMOTE_SERVER_START_TIME);

            serverService.startServer(LOCAL_PORT, REMOTE_ADDRESS, REMOTE_PORT);
            Thread.sleep(SERVER_START_TIME);
            serverStatus = serverService.getServerStatus(LOCAL_PORT);
            if (!serverStatus.equals(ServerStatus.WAIT_CONNECTION)) {
                throw new Exception("Server not started. ServerStatus: " + serverStatus);
            }

            while (count-- > 0) {

                socket = new Socket(LOCAL_ADDRESS, LOCAL_PORT);
                Thread.sleep(SERVER_PAUSE_TIME);

                serverStatus = serverService.getServerStatus(LOCAL_PORT);
                if (!serverStatus.equals(ServerStatus.HAS_ACTIVE_CONNECTION)) {
                    throw new Exception("Server can not get connection. ServerStatus: " + serverStatus);
                }

                socket.close();
                Thread.sleep(SERVER_CLOSE_SOCKET_TIME);
                serverStatus = serverService.getServerStatus(LOCAL_PORT);
                if (!serverStatus.equals(ServerStatus.WAIT_CONNECTION)) {
                    throw new Exception("Server mast be in wait connection status. ServerStatus: " + serverStatus);
                }

            }


            serverService.stopServer(LOCAL_PORT);
            Thread.sleep(SERVER_STOP_TIME);
            serverStatus = serverService.getServerStatus(LOCAL_PORT);
            if (!serverStatus.equals(ServerStatus.STOPPED)) {
                throw new Exception("Server not stopped. ServerStatus: " + serverStatus);
            }


        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        } finally {
            try {
                serverService.stopServer(LOCAL_PORT);
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                assert false;
            }

        }
    }



    public void startStopServer() {
        ServerService serverService = ServerService.getServerService();
        ServerStatus serverStatus;

        try {

            Thread.sleep(SERVER_START_TIME);

            serverService.startServer(LOCAL_PORT, REMOTE_ADDRESS, REMOTE_PORT);
            Thread.sleep(SERVER_START_TIME);
            serverStatus = serverService.getServerStatus(LOCAL_PORT);
            if (!serverStatus.equals(ServerStatus.WAIT_CONNECTION)) {
                throw new Exception("Server not started. ServerStatus: " + serverStatus);
            }
            serverService.stopServer(LOCAL_PORT);
            Thread.sleep(SERVER_STOP_TIME);
            serverStatus = serverService.getServerStatus(LOCAL_PORT);
            if (!serverStatus.equals(ServerStatus.STOPPED)) {
                throw new Exception("Server not stopped. ServerStatus: " + serverStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        } finally {
            serverService.stopServer(LOCAL_PORT);
        }
    }
}
