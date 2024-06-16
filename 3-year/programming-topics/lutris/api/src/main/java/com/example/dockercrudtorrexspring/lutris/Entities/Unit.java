package com.example.dockercrudtorrexspring.lutris.Entities;

public class Unit {
    private int id;
    private String name;
    private String launchDate;

    public Unit() {

    }

    public Unit
            (
                int id,
                String name,
                String launchDate
            )
    {
        this.id = id;
        this.name = name;
        this.launchDate = launchDate;
    }

    public Unit(String name, String launchDate) {
        this.name = name;
        this.launchDate = launchDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaunchDate() {
        return this.launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }
}
