package com.example.WebAstro.controllers;

import com.example.WebAstro.validation.Validator;
import com.example.WebAstro.models.Star;
import com.example.WebAstro.repos.StarRepo;
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
public class StarController {
    @Autowired
    private StarRepo starRepo;

    @GetMapping("/add/star")
    public String addStar(Model model) {
        return "add/add-star";
    }

    @PostMapping("/add/star")
    private String addStar(@RequestParam String name, @RequestParam String mass, @RequestParam String radius, @RequestParam String spectralClass, @RequestParam String magnitude, @RequestParam String starSystemName, Model model) {
        Validator.validate(name);
        Star star = new Star(name, mass, radius, spectralClass, magnitude, starSystemName);
        starRepo.save(star);
        return "redirect:/";
    }

    @GetMapping("/find/star")
    public String findStarByName(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        Iterable<Star> stars;
        if (name != null && !name.isEmpty()) {
            stars = starRepo.findByName(name);
        } else {
            stars = starRepo.findAll();
        }
        model.addAttribute("stars", stars);
        return "find/find-star";
    }

    @GetMapping("/find/star/{id}")
    private String detailsStar(@PathVariable(value = "id") long id, Model model) {
        if (!starRepo.existsById(id)) {
            return "find/find-star";
        }
        Optional<Star> star = starRepo.findById(id);
        ArrayList<Star> res = new ArrayList<>();
        star.ifPresent(res::add);
        model.addAttribute("star", res);
        return "details/details-star";
    }

    @GetMapping("/find/star/{id}/edit")
    private String editStar(@PathVariable(value = "id") long id, Model model) {
        if (!starRepo.existsById(id)) {
            return "find/find-star";
        }
        Optional<Star> star = starRepo.findById(id);
        ArrayList<Star> res = new ArrayList<>();
        star.ifPresent(res::add);
        model.addAttribute("star", res);
        return "edit/edit-star";
    }

    @PostMapping("/find/star/{id}/edit")
    private String editStarPost(@PathVariable(value = "id") long id,
                                @RequestParam String name,
                                @RequestParam String mass,
                                @RequestParam String radius,
                                @RequestParam String spectralClass,
                                @RequestParam String magnitude,
                                @RequestParam String starSystemName,
                                Model model) {
        Validator.validate(name);
        Star star = starRepo.findById(id).orElseThrow();
        star.setName(name);
        star.setMass(mass);
        star.setRadius(radius);
        star.setSpectralClass(spectralClass);
        star.setMagnitude(magnitude);
        star.setStarSystemName(starSystemName);
        starRepo.save(star);
        return "redirect:/";
    }

}
