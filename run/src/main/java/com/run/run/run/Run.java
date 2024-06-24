package com.run.run.run;

import java.time.LocalDateTime;

public record Run(
    Integer id,
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    Integer miles,
    Location location
) {}

// DONT FORGET TO ADD VALIDATION DEPENDENCIES FOR NEW CONSTRAINTS e.g. @Positive or @Valid