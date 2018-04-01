package ru.doneathome.functional;

import ru.doneathome.exeptions.OpenServerException;
import ru.doneathome.model.Pipe;
import ru.doneathome.service.ServerService;

import java.util.Collection;
import java.util.Set;

import static ru.doneathome.service.ServerService.getServerService;

public class ServerFunctional {

    private ServerService serverService = getServerService();


    public void openPipe(Pipe pipe) throws OpenServerException {
        serverService.startServer(pipe.getLocalhostPort(), pipe.getRemoteIP(), pipe.getRemotePort());
    }

    public void  closePipe(Pipe pipe) {
        serverService.stopServer(pipe.getLocalhostPort());
    }

    public void openPipes(Collection<Pipe> pipes) {

        pipes.forEach(pipe -> {
            try {
                openPipe(pipe);
            } catch (OpenServerException ignored) {}
        });

    }

    public void closePipes(Collection<Pipe> pipes) {
        pipes.forEach(this::closePipe);
    }

    public Set<Pipe> getPipes() {
        serverService.verifyOpenServers();


        // serverService.getOpenServers().stream().map();

        // TODO: как лямда выражениями преобразовывать коллекции
        return null;
    }






}


