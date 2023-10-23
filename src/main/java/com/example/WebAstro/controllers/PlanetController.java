package com.example.WebAstro.controllers;

import com.example.WebAstro.validation.Validator;
import com.example.WebAstro.models.Planet;
import com.example.WebAstro.repos.PlanetRepo;
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
public class PlanetController {
    @Autowired
    PlanetRepo planetRepo;

    @GetMapping("/add/planet")
    public String addPlanet(Model model) {
        return "add/add-planet";
    }

    @PostMapping("/add/planet")
    private String addPlanet(@RequestParam String name,
                             @RequestParam String type,
                             @RequestParam String mass,
                             @RequestParam String radius,
                             @RequestParam String semiMajorAxis,
                             @RequestParam String orbitalEccentricity,
                             @RequestParam String starSystemName,
                             Model model) {
        Validator.validate(name);
        Planet planet = new Planet(name, type, mass, radius, semiMajorAxis, orbitalEccentricity, starSystemName);
        planetRepo.save(planet);
        return "redirect:/";
    }

    @GetMapping("/find/planet")
    public String findPlanetByName(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                       Model model) {
        Iterable<Planet> planets;
        if (name != null && !name.isEmpty()) {
            planets = planetRepo.findByName(name);
        } else {
            planets = planetRepo.findAll();
        }
        model.addAttribute("planets", planets);
        return "find/find-planet";
    }

    @GetMapping("/find/planet/{id}")
    private String detailsPlanet(@PathVariable(value = "id") long id, Model model) {
        if (!planetRepo.existsById(id)) {
            return "find/find-planet";
        }
        Optional<Planet> planet = planetRepo.findById(id);
        ArrayList<Planet> res = new ArrayList<>();
        planet.ifPresent(res::add);
        model.addAttribute("planet", res);
        return "details/details-planet";
    }

    @GetMapping("/find/planet/{id}/edit")
    private String editPlanet(@PathVariable(value = "id") long id, Model model) {
        if (!planetRepo.existsById(id)) {
            return "find/find-planet";
        }
        Optional<Planet> planet = planetRepo.findById(id);
        ArrayList<Planet> res = new ArrayList<>();
        planet.ifPresent(res::add);
        model.addAttribute("planet", res);
        return "edit/edit-planet";
    }

    @PostMapping("/find/planet/{id}/edit")
    private String editPlanetPost(@PathVariable(value = "id") long id,
                                      @RequestParam String name,
                                      @RequestParam String type,
                                      @RequestParam String mass,
                                      @RequestParam String radius,
                                      @RequestParam String semiMajorAxis,
                                      @RequestParam String orbitalEccentricity,
                                      @RequestParam String starSystemName,
                                      Model model) {
        Validator.validate(name);
        Planet planet = planetRepo.findById(id).orElseThrow();
        planet.setName(name);
        planet.setType(type);
        planet.setMass(mass);
        planet.setRadius(radius);
        planet.setSemiMajorAxis(semiMajorAxis);
        planet.setOrbitalEccentricity(orbitalEccentricity);
        planet.setStarSystemName(starSystemName);
        planetRepo.save(planet);
        return "redirect:/";
    }


}
