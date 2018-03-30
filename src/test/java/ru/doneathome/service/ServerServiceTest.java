package ru.doneathome.service;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import java.net.ServerSocket;
import java.net.Socket;

public class ServerServiceTest {

    final int LOCAL_PORT = 5000;
    final String REMOTE_ADDRESS = "localhost";
    final int REMOTE_PORT = 6000;

    final int SERVER_START_TIME = 1000;
    final int SERVER_STOP_TIME = 1000;
    final int SERVER_PAUSE_TIME = 1000;

    @Test
    @RepeatedTest(5)
    public void startStopServer() {
        ServerService serverService = ServerService.getServerService();

        try {

            Thread.sleep(SERVER_START_TIME);

            serverService.startServer(LOCAL_PORT, REMOTE_ADDRESS, REMOTE_PORT);
            Thread.sleep(SERVER_START_TIME);
            if (!serverService.getServerStatus(LOCAL_PORT).equals(ServerStatus.WAIT_CONNECTION)) {
                throw new Exception("Server not started");
            }
            serverService.stopServer(LOCAL_PORT);
            Thread.sleep(SERVER_STOP_TIME);
            if (!serverService.getServerStatus(LOCAL_PORT).equals(ServerStatus.STOPPED)) {
                throw new Exception("Server not stopped");
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test
    public void StartStopConnectionServer() {

        ServerService serverService = ServerService.getServerService();

        ServerSocket serverSocket = null;
        Socket socket;

        try {
            serverService.startServer(LOCAL_PORT, REMOTE_ADDRESS, REMOTE_PORT);
            Thread.sleep(SERVER_START_TIME);
            if (!serverService.getServerStatus(LOCAL_PORT).equals(ServerStatus.WAIT_CONNECTION)) {
                throw new Exception("Server not started");
            }

            serverSocket = new ServerSocket(REMOTE_PORT);
            socket = new Socket(REMOTE_ADDRESS, LOCAL_PORT);
            Thread.sleep(SERVER_PAUSE_TIME);

            if (!serverService.getServerStatus(LOCAL_PORT).equals(ServerStatus.HAS_ACTIVE_CONNECTION)) {
                throw new Exception("Server can not get connection");
            }

            socket.close();
            Thread.sleep(SERVER_PAUSE_TIME);
            if (!serverService.getServerStatus(LOCAL_PORT).equals(ServerStatus.WAIT_CONNECTION)) {
                throw new Exception("Server mast be in wait connection status");
            }

            socket = new Socket(REMOTE_ADDRESS, LOCAL_PORT);
            Thread.sleep(SERVER_PAUSE_TIME);

            if (!serverService.getServerStatus(LOCAL_PORT).equals(ServerStatus.HAS_ACTIVE_CONNECTION)) {
                throw new Exception("Server can not get connection");
            }

            serverService.stopServer(LOCAL_PORT);
            Thread.sleep(SERVER_STOP_TIME);
            if (!serverService.getServerStatus(LOCAL_PORT).equals(ServerStatus.STOPPED)) {
                throw new Exception("Server not stopped");
            }

            serverSocket.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
}
