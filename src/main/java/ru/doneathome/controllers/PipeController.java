package ru.doneathome.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.doneathome.model.Pipe;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/pipe")
public class PipeController {
    private static final Logger log = LoggerFactory.getLogger(PipeController.class);


    @RequestMapping(value = "/getPipe/{profileName}/{pipeName}", method = RequestMethod.GET)
    public @ResponseBody
    Pipe getPipe(@PathVariable String profileName, @PathVariable String pipeName) {
        log.info("request one pipe. profileName: " + profileName + ", pipeName: " + pipeName);

        return new Pipe();
    }

    // http://localhost:8080/pipe/getPipes
    @RequestMapping(value = "/getPipes", method = RequestMethod.GET)
    public @ResponseBody
    List<Pipe> getPipes() {
        log.info("request all pipes");

        List<Pipe> pipes = new LinkedList<>();

        pipes.add( new Pipe(5050, "192.168.56.102", 3030));
        pipes.add( new Pipe(5060, "192.168.56.104", 3050));


        return pipes;
    }

}
