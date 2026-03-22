package com.takima.race.registration.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.services.RegistrationService;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;

    public record RegistrationRequest(Long runnerId) {}

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/races/{raceId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public Registration register(@PathVariable Long raceId, @RequestBody RegistrationRequest request) {
        return registrationService.register(raceId, request.runnerId());
    }

    @GetMapping("/races/{raceId}/registrations")
    public List<Registration> getByRace(@PathVariable Long raceId) {
        return registrationService.getParticipants(raceId);
    }
}
