package com.takima.race.race.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;

@Service
public class RaceService {
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAll() { //get all races
        return raceRepository.findAll();
    }
}
