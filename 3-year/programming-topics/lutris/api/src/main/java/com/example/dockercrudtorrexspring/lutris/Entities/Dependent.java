package com.example.dockercrudtorrexspring.lutris.Entities;

public class Dependent {
    private int id;
    private String name;
    private String birth;
    private int idEmployee;

    public Dependent() {

    }

    public Dependent
            (
                    int id,
                    String name,
                    String birth,
                    int idEmployee
            )
    {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.idEmployee = idEmployee;
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

    public String getBirth() {
        return this.birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
}
