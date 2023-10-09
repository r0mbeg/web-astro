package com.example.WebAstro.controllers;

import com.example.WebAstro.Validator;
import com.example.WebAstro.models.Satellite;
import com.example.WebAstro.repos.SatelliteRepo;
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
public class SatelliteController {
    @Autowired
    SatelliteRepo satelliteRepo;

    @GetMapping("/add/satellite")
    public String addSatellite(Model model) {
        return "add/add-satellite";
    }

    @PostMapping("/add/satellite")
    private String addSatellite(@RequestParam String name,
                             @RequestParam String type,
                             @RequestParam String mass,
                             @RequestParam String radius,
                             @RequestParam String semiMajorAxis,
                             @RequestParam String orbitalEccentricity,
                             @RequestParam String planetName,
                             Model model) {
        Validator.validate(name);
        Satellite satellite = new Satellite(name, type, mass, radius, semiMajorAxis, orbitalEccentricity, planetName);
        satelliteRepo.save(satellite);
        return "redirect:/";
    }

    @GetMapping("/find/satellite")
    public String findSatelliteByName(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                   Model model) {
        Iterable<Satellite> satellites;
        if (name != null && !name.isEmpty()) {
            satellites = satelliteRepo.findByName(name);
        } else {
            satellites = satelliteRepo.findAll();
        }
        model.addAttribute("satellites", satellites);
        return "find/find-satellite";
    }

    @GetMapping("/find/satellite/{id}")
    private String detailsSatellite(@PathVariable(value = "id") long id, Model model) {
        if (!satelliteRepo.existsById(id)) {
            return "find/find-satellite";
        }
        Optional<Satellite> satellite = satelliteRepo.findById(id);
        ArrayList<Satellite> res = new ArrayList<>();
        satellite.ifPresent(res::add);
        model.addAttribute("satellite", res);
        return "details/details-satellite";
    }

    @GetMapping("/find/satellite/{id}/edit")
    private String editSatellite(@PathVariable(value = "id") long id, Model model) {
        if (!satelliteRepo.existsById(id)) {
            return "find/find-satellite";
        }
        Optional<Satellite> satellite = satelliteRepo.findById(id);
        ArrayList<Satellite> res = new ArrayList<>();
        satellite.ifPresent(res::add);
        model.addAttribute("satellite", res);
        return "edit/edit-satellite";
    }

    @PostMapping("/find/satellite/{id}/edit")
    private String editSatellitePost(@PathVariable(value = "id") long id,
                                  @RequestParam String name,
                                  @RequestParam String type,
                                  @RequestParam String mass,
                                  @RequestParam String radius,
                                  @RequestParam String semiMajorAxis,
                                  @RequestParam String orbitalEccentricity,
                                  @RequestParam String planetName,
                                  Model model) {
        Validator.validate(name);
        Satellite satellite = satelliteRepo.findById(id).orElseThrow();
        satellite.setName(name);
        satellite.setType(type);
        satellite.setMass(mass);
        satellite.setRadius(radius);
        satellite.setSemiMajorAxis(semiMajorAxis);
        satellite.setOrbitalEccentricity(orbitalEccentricity);
        satellite.setPlanetName(planetName);
        satelliteRepo.save(satellite);
        return "redirect:/";
    }


}
