package com.example.WebAstro.repos;

import com.example.WebAstro.models.Planet;
import com.example.WebAstro.models.Satellite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SatelliteRepo extends CrudRepository<Satellite, Long>{
    List<Satellite> findByName(@Param("name") String name);
}
