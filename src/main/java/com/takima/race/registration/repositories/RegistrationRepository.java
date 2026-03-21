package com.takima.race.registration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takima.race.registration.entities.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByRaceId(Long raceId); //list  all participants from a race
    List<Registration> findByRunnerId(Long runnerId); //list all races of a runner
    long countByRaceId(Long raceId);  //participants count
    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId);  //check if runner is already registered
}
