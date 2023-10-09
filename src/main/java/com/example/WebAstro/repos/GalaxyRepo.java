package com.example.WebAstro.repos;

import com.example.WebAstro.models.Galaxy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GalaxyRepo extends CrudRepository<Galaxy, Long> {


    List<Galaxy> findByName(@Param("name") String name);
}
