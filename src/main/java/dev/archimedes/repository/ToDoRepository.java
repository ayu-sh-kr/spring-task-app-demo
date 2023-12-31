package dev.archimedes.repository;

import dev.archimedes.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoList, Long> {
    boolean existsByTitle(String title);
}
