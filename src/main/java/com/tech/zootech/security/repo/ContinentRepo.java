package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContinentRepo extends JpaRepository<Continent, Long> {
    @Query("select c from Continent c where c.planet.id = :planet_id")
    Optional<List<Continent>> findByPlanet(Long planetId);
    Optional<Continent> findByName(String continentName);
}
