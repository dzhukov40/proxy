package ru.doneathome.service;

import ru.doneathome.dto.ServerInfoDTO;
import ru.doneathome.enums.ServerStatus;
import ru.doneathome.exeptions.OpenServerException;

import java.util.Set;

public interface ServerServiceAPI {

    void startServer(int localPort, String remoteAddress, int remotePort) throws OpenServerException;
    void stopServer(int localPort);
    ServerStatus getServerStatus(int localPort);
    void verifyOpenServers();
    Set<ServerInfoDTO> getOpenServers();
    void closeAllServers();

}
