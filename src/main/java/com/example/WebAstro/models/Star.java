package com.example.WebAstro.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String mass;

    private String radius;

    private String spectralClass;

    private String magnitude;

    private String starSystemName;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getSpectralClass() {
        return spectralClass;
    }

    public void setSpectralClass(String spectralClass) {
        this.spectralClass = spectralClass;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getStarSystemName() {
        return starSystemName;
    }

    public void setStarSystemName(String starSystemName) {
        this.starSystemName = starSystemName;
    }

    public Star(String name, String mass, String radius, String spectralClass, String magnitude, String starSystemName) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.spectralClass = spectralClass;
        this.magnitude = magnitude;
        this.starSystemName = starSystemName;
    }

    public Star() {

    }
}
