package org.nfe.implementations;

import org.nfe.abstracts.Entity;
import org.nfe.abstracts.InputTypes;

import java.util.Scanner;

public class Address extends Entity {
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

  public void create(Scanner input) {
    String cep = (String) this.inputWithoutException(input, "Digite o cep: ", InputTypes.STRING);
    String city = (String) this.inputWithoutException(input, "Digite a cidade: ", InputTypes.STRING);
    String state = (String) this.inputWithoutException(input, "Digite o estado: ", InputTypes.STRING);
    int number = (int) this.inputWithoutException(input, "Digite o numero do endereco: ", InputTypes.INT);
    String neighborhood = (String) this.inputWithoutException(input, "Digite o bairo: ", InputTypes.STRING);
    String road = (String) this.inputWithoutException(input, "Digite o rua: ", InputTypes.STRING);
   
    this.setCity(city);
    this.setState(state);
    this.setNumber(number);
    this.setNeighborhood(neighborhood);
    this.setRoad(road);
    this.setCep(cep);
  }

  public void output() {
    System.out.println("Endereço:");
    System.out.println("cep: " + this.getCep());
    System.out.println("cidade: " + this.getCity());
    System.out.println("estado: " + this.getState());
    System.out.println("rua: " + this.getRoad());
    System.out.println("número: " + this.getNumber());
    System.out.println("bairro: " + this.getNeighborhood());
  }
}
