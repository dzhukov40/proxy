package ru.doneathome.functional;

import ru.doneathome.exeptions.OpenServerException;
import ru.doneathome.model.Pipe;
import ru.doneathome.model.Profile;

import java.util.Collection;
import java.util.Set;

public interface ServerFunctionalAPI {

    void openPipe(Pipe pipe) throws OpenServerException;
    void  closePipe(Pipe pipe);
    void openPipes(Collection<Pipe> pipes);
    void closePipes(Collection<Pipe> pipes);
    Set<Pipe> getOpenPipes();
    void openProfilePipes(Profile profile);
    void closeAllPipes();

}
