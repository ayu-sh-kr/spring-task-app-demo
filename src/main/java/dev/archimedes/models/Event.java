package dev.archimedes.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String event_name;
    private String event_description;
    private LocalDateTime event_start;
    private LocalDateTime event_end;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;
}
