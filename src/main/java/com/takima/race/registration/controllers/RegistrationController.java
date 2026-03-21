package com.takima.race.registration.controllers;

import org.springframework.http.HttpStatus;
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

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/races/{raceId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public Registration register(@PathVariable Long raceId, @RequestBody RegistrationRequest request) {
        return registrationService.register(raceId, request.runnerId());
    }

    record RegistrationRequest(Long runnerId) {}
}
