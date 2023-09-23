package dev.archimedes.Service;

import dev.archimedes.dtos.ClubDTO;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();
}
