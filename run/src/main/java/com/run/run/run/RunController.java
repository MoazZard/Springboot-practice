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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;




 // ANNOTATION & simplifies API calls e.g all mapping starts with /api/runs already
@RestController
@RequestMapping("/api/runs")
public class RunController {
    // Response body JSON by default


    /* map method to an endpoint (when someone accesses the endpoint, this method is executed)
    controller should take in request and return a response, nothing else
    */

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


    //post (create) Run will be coming to us from the REQUEST body in a restAPI architecture also returned response gives HttpStatus CREATED
    @ResponseStatus(HttpStatus.CREATED) 
    @PostMapping("") 
    void create(@RequestBody Run run) { 
        runRepository.create(run);  
    }
 

    //put (update)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable int id) {
        runRepository.update(run,id);
    }

    //delete


}
