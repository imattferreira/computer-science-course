package org.nfe.implementations;

public class Address {
    private String city;
    private String cep;
    private String road;
    private String state;
    private String neighborhood;
    private int number;

  public Address() {
    this.cep = "";
    this.neighborhood = "";
    this.city = "";
    this.number = 0;
    this.road = "";
    this.state = "";
  }

  public Address(String cep, String neighborhood, String city, int number, String road, String state) {
    this.cep = cep;
    this.neighborhood = neighborhood;
    this.city = city;
    this.road = road;
    this.number = number;
    this.state = state;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getRoad() {
    return road;
  }

  public void setRoad(String road) {
    this.road = road;
  }
}
