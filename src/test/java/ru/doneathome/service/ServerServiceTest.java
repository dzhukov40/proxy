package ru.doneathome.service;


import org.junit.jupiter.api.Test;
import ru.doneathome.exeptions.OpenServerException;

public class ServerServiceTest {

    final int LOCAL_PORT = 5000;
    final String REMOTE_ADDRESS = "localhost";
    final int REMOTE_PORT = 6000;

    final int SERVER_START_TIME = 1000;
    final int SERVER_STOP_TIME = 1000;

    @Test
    public void startStopServer() {

        ServerService serverService = new ServerService();


        try {
            serverService.startServer(LOCAL_PORT, REMOTE_ADDRESS, REMOTE_PORT);
            Thread.sleep(SERVER_START_TIME);
            if (serverService.isServerRun(LOCAL_PORT)) {
                throw new Exception("Server not started");
            }
            serverService.stopServer(LOCAL_PORT);
            Thread.sleep(SERVER_STOP_TIME);
            if (!serverService.isServerRun(LOCAL_PORT)) {
                throw new Exception("Server not stopped");
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }

    }
}
