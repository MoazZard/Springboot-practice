package com.run.run.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jakarta.annotation.PostConstruct;

// NOTE: spring controls the instance with this annotation (detects beans) so putting it in param makes bean e.g in RunController
// NOTE: this is the model aspect of the MVC architecture. it communicates with the database
@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    private final JdbcClient jdbcClient; // DEPENDENCY INJECTION

    public RunRepository(JdbcClient jdbcClient) { 
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run")
        .query(Run.class)
        .list();
    }

    public Optional<Run> findById(int id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id")
        .param("id", id) // SPECIFIES WHAT IS TO BE PARAMETERISED, IN THIS CASE ITS ID
        .query(Run.class) // MAPPED TO A RUN (basically turns into a run object)
        .optional(); // RETURNS AN OPTIONAL (object container) WITH RUN INSIDE
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
        .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString())) // SPECIFIES WHATS TO BE PARAMETERISED
        .update(); // Function used in CREATE, UPDATE and DELETE and returns how many rows affected

        Assert.state(updated == 1, "Failed to CREATE run: " + run.title() ); // we want to make sure only 1 row is affected, otherwise message is sent
    }

    public void update(Run run, int id) {
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
        .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
        .update();

        Assert.state(updated == 1, "Failed to UPDATE run: " + run.title() );
    }

    public void delete(int id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
        .params("id", id)
        .update();

        Assert.state(updated == 1, "Failed to DELETE run: " + id );
    }

    public int count() { return jdbcClient.sql("select * from run").query().listOfRows().size(); } // HAVE TO QUERY
    
    



    // NOTE: Commented parts are an in memory representation

    // returns a specific run object identified by id (optional container object, returns isPresent bool)
    /*
    Optional<Run> findById(int id) {
        return runs.stream()
            .filter(run -> run.id() == id) // CONDITION
            .findFirst();
    }
    */

    // returns all run objects
    /*
    List<Run> findAll() {
        return runs;
    }
    */

    // --- Post (Create) ---
    /*
    void create(Run run) {
        runs.add(run);  
    }
    */

    // --- Put (update) ---
    /*
    void update(Run run, int id) {
        Optional<Run> existingRun = findById(id);

        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }

    }
    */

    // --- Delete ---
    /*
    void delete(int id) {
        runs.removeIf(run -> run.id().equals(id));
    }
    */

    @PostConstruct // method only happens after dependency injection
    private void init () {
        runs.add(new Run(2, "First RUNNING", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR));
    }

}
