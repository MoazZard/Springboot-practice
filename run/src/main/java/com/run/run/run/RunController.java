package com.run.run.run;

import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



 // ANNOTATION
@RestController
public class RunController {
    // Response body JSON by default


    /* map method to an endpoint (when someone accesses the endpoint, this method is executed)
    controller should take in request and return a response, nothing else
    */
    String home() {
        return "Hello, Runners!";
    }

    private RunRepository runRepository;

    // Spring is an inversion of control framework. 1000+ requests? Only want one RunRepository (we want spring to control instances of classes)
    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    // method to return a list of runs when the request is "hey, i need all the runs" (I DONT KNOW WHAT THIS DOES)
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/api/runs")
    List<Run> findAll() {
        //to get all the runs, we need an instance of runRepository since it has 
        return runRepository.findAll();
    }


}
