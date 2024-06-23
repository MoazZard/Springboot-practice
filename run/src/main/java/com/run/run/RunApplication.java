package com.run.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.run.run.run.Location;
import com.run.run.run.Run;

@SpringBootApplication
public class RunApplication {

	private static final Logger log = LoggerFactory.getLogger(RunApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
		
		Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
		log.info("run" + run);
	}	


}
