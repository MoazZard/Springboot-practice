package com.run.run.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

// spring controls the instance with this annotation
@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    // returns a specific run object identified by id (optional container object, returns isPresent bool)
    Optional<Run> findById(int id) {
        return runs.stream()
            .filter(run -> run.id() == id) // CONDITION
            .findFirst();
    }

    // returns all run objects
    List<Run> findAll() {
        return runs;
    }

    @PostConstruct // method only happens after dependency injection
    private void init () {
        runs.add(new Run(1, "First RUNNING", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
    }

}
