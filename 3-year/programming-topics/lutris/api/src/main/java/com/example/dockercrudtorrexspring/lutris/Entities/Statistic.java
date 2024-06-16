package com.example.dockercrudtorrexspring.lutris.Entities;

public class Statistic {
    private int dependents;
    private int sectors;
    private int units;
    private int employees;

    public Statistic
            (
                    int employees,
                    int dependents,
                    int units,
                    int sectors
            )
    {
        this.employees = employees;
        this.dependents = dependents;
        this.units = units;
        this.sectors = sectors;
    }

    public int getDependents() {
        return dependents;
    }

    public void setDependents(int dependent) {
        this.dependents = dependent;
    }

    public int getSectors() {
        return sectors;
    }

    public void setSectors(int sectors) {
        this.sectors = sectors;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }
}
