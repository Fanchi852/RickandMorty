package com.example.aa_1_rickandmorty.beans;

import java.util.ArrayList;

public class Person {

    private Integer id;
    private String name, status, species, type, gender, image;
    private Location origin, location;
    private ArrayList<Episode> episode_list;
    private ArrayList<String> episode;

    public Person() {
    }

    public Person(Integer id, String name, String status, String species, String type, String gender, String image, Location origin, Location location, ArrayList<Episode> episode_list, ArrayList<String> episode) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.image = image;
        this.origin = origin;
        this.location = location;
        this.episode_list = episode_list;
        this.episode = episode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Episode> getEpisode_list() {
        return episode_list;
    }

    public void setEpisode_list(ArrayList<Episode> episode_list) {
        this.episode_list = episode_list;
    }

    public ArrayList<String> getEpisode() {
        return episode;
    }

    public void setEpisode(ArrayList<String> episode) {
        this.episode = episode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toStringList() {
        return  "id= " + id +
                ", name= " + name +
                ", species= " + species +
                ", type= " + type +
                ", gender= " + gender
                ;
    }


}
