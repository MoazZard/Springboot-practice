package com.run.run.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

// NOTE: spring controls the instance with this annotation (detects beans) so putting it in param makes bean e.g in RunController
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

    // --- Post (Create) ---

    void create(Run run) {
        runs.add(run);  
    }

    // --- Put (update) ---
    void update(Run run, int id) {
        Optional<Run> existingRun = findById(id);

        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }

    }

    // --- Delete ---
    void delete(int id) {
        runs.removeIf(run -> run.id().equals(id));
    }


    @PostConstruct // method only happens after dependency injection
    private void init () {
        runs.add(new Run(1, "First RUNNING", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
    }

}
