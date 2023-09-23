package dev.archimedes.Service;

import dev.archimedes.dtos.ClubDTO;
import dev.archimedes.models.Club;
import dev.archimedes.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService{
    private ClubRepository repository;

    @Autowired
    public ClubServiceImpl(ClubRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClubDTO> findAllClubs() {
        List<Club> clubs = repository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    private ClubDTO mapToClubDto(Club club){
        ClubDTO clubDTO = ClubDTO.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDTO;
    }
}
