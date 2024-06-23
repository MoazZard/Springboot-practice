package com.run.run.run;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



 // ANNOTATION & simplifies API calls e.g all mapping starts with /api/runs already
@RestController
@RequestMapping("/api/runs")
public class RunController {
    // Response body JSON by default


    /* map method to an endpoint (when someone accesses the endpoint, this method is executed)
    controller should take in request and return a response, nothing else
    */
    String home() {
        return "Hello, Runners!";
    }

    private RunRepository runRepository;

    // Spring is an inversion of control framework. Only want one RunRepository (we want spring to control instances of classes)
    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        //to get all the runs, we need an instance of runRepository since it has 
        return runRepository.findAll();
    }

    //uses the pathvariable
    @GetMapping("/{id}") 
    Run findById(@PathVariable int id) {
        Optional<Run> run = runRepository.findById(id);

        if (run.isEmpty()) { // checking if container object is empty or not
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return run.get();
    }

    //post


    //put


    //delete

    
}
