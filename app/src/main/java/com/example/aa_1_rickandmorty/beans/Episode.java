package com.example.aa_1_rickandmorty.beans;

import java.util.ArrayList;

public class Episode {

    private Integer id;
    private String name, air_date, episode, url;
    private ArrayList<Person> people;
    private ArrayList<String> characters_list;

    public Episode() {
    }

    public Episode(Integer id, String name, String air_date, String episode, String url, ArrayList<Person> people, ArrayList<String> characters_list) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.url = url;
        this.people = people;
        this.characters_list = characters_list;
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

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<String> getCharacters_list() {
        return characters_list;
    }

    public void setCharacters_list(ArrayList<String> characters_list) {
        this.characters_list = characters_list;
    }

    public String toStringList() {
        return  "id= " + id +
                ", name= " + name +
                ", air_date= " + air_date +
                ", episode= " + episode
                ;
    }
}
