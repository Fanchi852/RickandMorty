package com.example.aa_1_rickandmorty.beans;

import java.util.ArrayList;

public class Location {

    private Integer id;
    private String name, type, dimension, url;
    private ArrayList<Person> residents;
    private ArrayList<String> residents_list;

    public Location() {
    }

    public Location(Integer id, String name, String type, String dimension, String url, ArrayList<Person> residents, ArrayList<String> residents_list) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
        this.url = url;
        this.residents = residents;
        this.residents_list = residents_list;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Person> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<Person> residents) {
        this.residents = residents;
    }

    public ArrayList<String> getResidents_list() {
        return residents_list;
    }

    public void setResidents_list(ArrayList<String> residents_list) {
        this.residents_list = residents_list;
    }

    public String toStringList() {
        return  "id= " + id +
                ", name= " + name +
                ", type= " + type +
                ", dimension= " + dimension
                ;
    }
}
