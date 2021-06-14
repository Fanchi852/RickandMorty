package com.example.aa_1_rickandmorty.beans;

import java.util.ArrayList;

public class Container {

    private Info info;
    private ArrayList<Person> results;

    public Container() {
        this.info = new Info();
    }

    public Container(Info info, ArrayList<Person> results) {
        this.info = info;
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Person> getResults() {
        return results;
    }

    public void setResults(ArrayList<Person> results) {
        this.results = results;
    }

    public class Info{

        Integer count, pages;
        String next, prev;

        public Info() {
        }

        public Info(Integer count, Integer pages, String next, String prev) {
            this.count = count;
            this.pages = pages;
            this.next = next;
            this.prev = prev;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getPages() {
            return pages;
        }

        public void setPages(Integer pages) {
            this.pages = pages;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getPrev() {
            return prev;
        }

        public void setPrev(String prev) {
            this.prev = prev;
        }
    }



}
