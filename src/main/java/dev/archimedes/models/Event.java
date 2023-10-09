package dev.archimedes.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String event_name;
    private String event_description;
    @NotNull
    private LocalDateTime event_start;
    @NotNull
    private LocalDateTime event_end;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
