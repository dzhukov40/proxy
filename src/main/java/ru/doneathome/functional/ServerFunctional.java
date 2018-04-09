package ru.doneathome.functional;

import ru.doneathome.exeptions.OpenServerException;
import ru.doneathome.model.Configuration;
import ru.doneathome.model.Pipe;
import ru.doneathome.model.Profile;
import ru.doneathome.service.ServerService;

import java.util.Collection;
import java.util.HashSet;
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

    public Set<Pipe> getOpenPipes() {
        Set<Pipe> pipes = new HashSet<>();

        serverService.getOpenServers().forEach(infoDTO -> {
            pipes.add(new Pipe(infoDTO.getLocalPort(), infoDTO.getRemoteAddress(), infoDTO.getRemotePort()));
        });

        return pipes;
    }

    public void openProfilePipes(Profile profile) {
        openPipes(profile.getPipes());
    }

    public void closeAllPipes() {
        serverService.closeAllServers();
    }






}


