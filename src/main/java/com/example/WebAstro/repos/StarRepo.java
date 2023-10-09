package com.example.WebAstro.repos;



import com.example.WebAstro.models.Star;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StarRepo extends CrudRepository<Star, Long> {
    List<Star> findByName(@Param("name") String name);
}
