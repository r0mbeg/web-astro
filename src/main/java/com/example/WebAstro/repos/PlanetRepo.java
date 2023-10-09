package com.example.WebAstro.repos;

import com.example.WebAstro.models.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanetRepo extends CrudRepository<Planet, Long> {
    List<Planet> findByName(@Param("name") String name);
}
