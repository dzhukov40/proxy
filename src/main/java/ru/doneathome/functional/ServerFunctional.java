package ru.doneathome.functional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.doneathome.controllers.PipeController;
import ru.doneathome.exeptions.OpenServerException;
import ru.doneathome.model.Pipe;
import ru.doneathome.model.Profile;
import ru.doneathome.service.ServerServiceAPI;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Scope(value = "singleton")
@Component
public class ServerFunctional implements ServerFunctionalAPI {
    private static final Logger log = LoggerFactory.getLogger(ServerFunctional.class);

    @Autowired
    private ServerServiceAPI serverService;


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


