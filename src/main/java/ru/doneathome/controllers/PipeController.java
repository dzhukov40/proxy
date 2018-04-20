package ru.doneathome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.doneathome.model.Pipe;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/pipe")
public class PipeController {


    @RequestMapping(value = "/getPipe/{profileName}/{pipeName}", method = RequestMethod.GET)
    public @ResponseBody
    Pipe getPipe(@PathVariable String profileName, @PathVariable String pipeName) {


        return new Pipe();
    }

    // http://localhost:8080/pipe/getPipes
    @RequestMapping(value = "/getPipes", method = RequestMethod.GET)
    public @ResponseBody
    List<Pipe> getPipes() {

        List<Pipe> pipes = new LinkedList<>();

        pipes.add( new Pipe(5050, "192.168.56.102", 3030));
        pipes.add( new Pipe(5060, "192.168.56.104", 3050));


        return pipes;
    }

}
