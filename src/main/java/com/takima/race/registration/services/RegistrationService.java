package com.takima.race.registration.services;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.services.RunnerService;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RunnerService runnerService;
    private final RaceService raceService;

    public RegistrationService(RegistrationRepository registrationRepository, RunnerService runnerService, RaceService raceService) {
        this.registrationRepository = registrationRepository;
        this.runnerService = runnerService;
        this.raceService = raceService;
    }

    public Registration register(Long raceId, Long runnerId) {
        runnerService.getById(runnerId); //404 if runner doesnt exist

        Race race = raceService.getById(raceId); //404 if race doesnt exist

        if (registrationRepository.existsByRunnerIdAndRaceId(runnerId, raceId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Runner already registered for this race");
        }
        if (registrationRepository.countByRaceId(raceId) >= race.getMaxParticipants()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Race is full");
        }

        Registration registration = new Registration();  //assemble the registration body
        registration.setRunnerId(runnerId);
        registration.setRaceId(raceId);
        registration.setRegistrationDate(LocalDate.now());
        return registrationRepository.save(registration);
    }
}