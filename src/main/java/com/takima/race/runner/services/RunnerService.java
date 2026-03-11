package com.takima.race.runner.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> getAll() { //get all runners
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {   //get runner by id
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }

    public Runner create(Runner runner) {   //create runner
        return runnerRepository.save(runner);
    }

    public void delete(Long id) {  //delete runner
        if (!runnerRepository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Runner %s not found", id)
                //i added this so it wouldn't give code 200 for non existent runners
            );
        }
        runnerRepository.deleteById(id);
    }
}
