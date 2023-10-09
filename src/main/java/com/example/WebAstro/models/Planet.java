package com.example.WebAstro.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String type;
    private String mass;
    private String radius;
    private String semiMajorAxis;
    private String orbitalEccentricity;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(String semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public String getOrbitalEccentricity() {
        return orbitalEccentricity;
    }

    public void setOrbitalEccentricity(String orbitalEccentricity) {
        this.orbitalEccentricity = orbitalEccentricity;
    }

    public String getStarSystemName() {
        return starSystemName;
    }

    public void setStarSystemName(String starSystemName) {
        this.starSystemName = starSystemName;
    }

    public Planet(String name, String type, String mass, String radius, String semiMajorAxis, String orbitalEccentricity, String starSystemName) {
        this.name = name;
        this.type = type;
        this.mass = mass;
        this.radius = radius;
        this.semiMajorAxis = semiMajorAxis;
        this.orbitalEccentricity = orbitalEccentricity;
        this.starSystemName = starSystemName;
    }

    public Planet() {
    }
}
