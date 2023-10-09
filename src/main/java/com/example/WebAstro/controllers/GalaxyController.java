package com.example.WebAstro.controllers;

import com.example.WebAstro.Validator;
import com.example.WebAstro.models.Galaxy;
import com.example.WebAstro.repos.GalaxyRepo;
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
public class GalaxyController {

    @Autowired
    private GalaxyRepo galaxyRepo;


    @GetMapping("/add/galaxy")
    public String addGalaxy(Model model) {
        return "add/add-galaxy";
    }

    @PostMapping("/add/galaxy")
    private String addGalaxy(@RequestParam String name,
                             @RequestParam String declination,
                             @RequestParam String rightAscension,
                             @RequestParam String distance,
                             @RequestParam String type,
                             Model model) {
        Validator.validate(name);
        Galaxy galaxy = new Galaxy(name, declination, rightAscension, distance, type);
        galaxyRepo.save(galaxy);
        return "redirect:/";
    }

    @GetMapping("/find/galaxy")
    public String findGalaxyByName(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                   Model model) {
        Iterable<Galaxy> galaxies;
        if (name != null && !name.isEmpty()) {
            galaxies = galaxyRepo.findByName(name);
        } else {
            galaxies = galaxyRepo.findAll();
        }
        model.addAttribute("galaxies", galaxies);
        return "find/find-galaxy";
    }

    @GetMapping("/find/galaxy/{id}")
    private String detailsGalaxy(@PathVariable(value = "id") long id, Model model){
        if(!galaxyRepo.existsById(id)) {
            return "find/find-galaxy";
        }
        Optional<Galaxy> galaxy = galaxyRepo.findById(id);
        ArrayList<Galaxy> res = new ArrayList<>();
        galaxy.ifPresent(res::add);
        model.addAttribute("galaxy", res);
        return "details/details-galaxy";
    }

    @GetMapping("/find/galaxy/{id}/edit")
    private String editGalaxy(@PathVariable(value = "id") long id, Model model){
        if(!galaxyRepo.existsById(id)) {
            return "find/find-galaxy";
        }
        Optional<Galaxy> galaxy = galaxyRepo.findById(id);
        ArrayList<Galaxy> res = new ArrayList<>();
        galaxy.ifPresent(res::add);
        model.addAttribute("galaxy", res);
        return "edit/edit-galaxy";
    }
    
    @PostMapping("/find/galaxy/{id}/edit")
    private String editGalaxyPost(@PathVariable(value = "id") long id,
                                  @RequestParam String name,
                                  @RequestParam String declination,
                                  @RequestParam String rightAscension,
                                  @RequestParam String distance,
                                  @RequestParam String type,
                                  Model model) {
        Validator.validate(name);
        Galaxy galaxy = galaxyRepo.findById(id).orElseThrow();
        galaxy.setName(name);
        galaxy.setDeclination(declination);
        galaxy.setRightAscension(rightAscension);
        galaxy.setDistance(distance);
        galaxy.setType(type);
        galaxyRepo.save(galaxy);
        return "redirect:/";
    }
}
