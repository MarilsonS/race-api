package com.takima.race.race.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;
import com.takima.race.registration.services.RegistrationService;
@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;
    private final RegistrationService registrationService;

    public record ParticipantCount(long count) {}

    public RaceController(RaceService raceService, RegistrationService registrationService) {
        this.raceService = raceService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Race> getAll(@RequestParam(required = false) String location) {
        return raceService.getAll(location);
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Race create(@RequestBody Race race) {
        return raceService.create(race);
    }

    @PutMapping("/{id}")
    public Race update(@PathVariable Long id, @RequestBody Race race) {
        return raceService.update(id, race);
    }

    @GetMapping("/{raceId}/participants/count")
    public ParticipantCount countParticipants(@PathVariable Long raceId) {
        return new ParticipantCount(registrationService.countParticipants(raceId));
    }

}
