package com.example.WebAstro.controllers;

import com.example.WebAstro.Validator;
import com.example.WebAstro.models.StarSystem;
import com.example.WebAstro.repos.StarSystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class StarSystemController {

    @Autowired
    private StarSystemRepo starSystemRepo;

    @GetMapping("/add/star-system")
    public String addStarSystem(Model model) {
        return "add/add-star-system";
    }


    @PostMapping("/add/star-system")
    private String addStarSystem(@RequestParam String name,
                                 @RequestParam String declination,
                                 @RequestParam String rightAscension,
                                 @RequestParam String distance,
                                 @RequestParam String galaxyName,
                                 Model model) {
        Validator.validate(name);
        StarSystem starSystem = new StarSystem(name, declination, rightAscension, distance, galaxyName);
        starSystemRepo.save(starSystem);
        return "redirect:/";
    }


    @GetMapping("/find/star-system")
    public String findStarSystemByName(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                       Model model) {
        Iterable<StarSystem> starSystems;
        if (name != null && !name.isEmpty()) {
            starSystems = starSystemRepo.findByName(name);
        } else {
            starSystems = starSystemRepo.findAll();
        }
        model.addAttribute("starSystems", starSystems);
        return "find/find-star-system";
    }

    @GetMapping("/find/star-system/{id}")
    private String detailsStarSystem(@PathVariable(value = "id") long id, Model model) {
        if (!starSystemRepo.existsById(id)) {
            return "find/find-star-system";
        }
        Optional<StarSystem> starSystem = starSystemRepo.findById(id);
        ArrayList<StarSystem> res = new ArrayList<>();
        starSystem.ifPresent(res::add);
        model.addAttribute("starSystem", res);
        return "details/details-star-system";
    }


    @GetMapping("/find/star-system/{id}/edit")
    private String editStarSystem(@PathVariable(value = "id") long id, Model model) {
        if (!starSystemRepo.existsById(id)) {
            return "find/find-star-system";
        }
        Optional<StarSystem> starSystem = starSystemRepo.findById(id);
        ArrayList<StarSystem> res = new ArrayList<>();
        starSystem.ifPresent(res::add);
        model.addAttribute("starSystem", res);
        return "edit/edit-star-system";
    }


    @PostMapping("/find/star-system/{id}/edit")
    private String editStarSystemPost(@PathVariable(value = "id") long id,
                                      @RequestParam String name,
                                      @RequestParam String declination,
                                      @RequestParam String rightAscension,
                                      @RequestParam String distance,
                                      @RequestParam String galaxyName,
                                      Model model) {
        Validator.validate(name);
        StarSystem starSystem = starSystemRepo.findById(id).orElseThrow();
        starSystem.setName(name);
        starSystem.setDeclination(declination);
        starSystem.setRightAscension(rightAscension);
        starSystem.setDistance(distance);
        starSystem.setGalaxyName(galaxyName);
        starSystemRepo.save(starSystem);
        return "redirect:/";
    }

}
