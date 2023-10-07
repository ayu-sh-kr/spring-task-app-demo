package dev.archimedes.models;

import java.time.LocalDateTime;

public class Event {
    private String event_name;
    private String event_description;
    private LocalDateTime event_start;
    private LocalDateTime event_end;
    private User user;
}
