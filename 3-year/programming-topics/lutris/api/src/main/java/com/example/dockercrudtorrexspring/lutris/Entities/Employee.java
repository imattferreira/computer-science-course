package com.example.dockercrudtorrexspring.lutris.Entities;

public class Employee {
    private int id;
    private String name;
    private String date;
    private int idSector;
    private int idUnit;
    private int idImage;

    public Employee() {

    }

    public Employee
            (
                    int id,
                    String name,
                    String date,
                    int idSector,
                    int idUnit
            )
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.idSector = idSector;
        this.idUnit = idUnit;
    }

    public Employee
            (
                    int id,
                    String name,
                    String date,
                    int idSector,
                    int idUnit,
                    int idImage
            )
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.idSector = idSector;
        this.idUnit = idUnit;
        this.idImage = idImage;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdSector() {
        return this.idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public int getIdUnit() {
        return this.idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

    public int getIdImage() {
        return this.idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
