package com.example.WebAstro.repos;

import com.example.WebAstro.models.StarSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StarSystemRepo extends CrudRepository<StarSystem, Long> {
    List<StarSystem> findByName(@Param("name") String name);
}

