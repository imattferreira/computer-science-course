package org.nfe.implementations;

import org.nfe.abstracts.InputTypes;
import org.nfe.abstracts.People;

import java.util.ArrayList;
import java.util.Scanner;

public class ShippingCompany extends People {
  private ArrayList<Vehicle> vehicles;
  private String stateSubscription;
  private String companyName;
  private int antt;


  public ShippingCompany() {
    this.companyName = "";
    this.stateSubscription = "";
    this.vehicles = new ArrayList<Vehicle>();
    this.antt = 0;
  }

  public ShippingCompany(String companyName, String stateSubscription, int antt, ArrayList<Vehicle> vehicles, Address address, Phone phone) {
    super(address, phone);

    this.companyName = companyName;
    this.stateSubscription = stateSubscription;
    this.vehicles = vehicles;
    this.antt = antt;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getStateSubscription() {
    return stateSubscription;
  }

  public void setStateSubscription(String stateSubscription) {
    this.stateSubscription = stateSubscription;
  }

  public ArrayList<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(ArrayList<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  public int getAntt() {
    return antt;
  }

  public void setAntt(int antt) {
    this.antt = antt;
  }

  public void create(Scanner input) {

    int antt = (int) this.inputWithoutException(input, "Digite o ANTT", InputTypes.INT);
    String companyName = (String) this.inputWithoutException(input, "Digite o nome da Transportadora", InputTypes.STRING);
    String stateSubscription = (String) this.inputWithoutException(input, "Digite a inscricao estadual", InputTypes.STRING);

    this.setAntt(antt);
    this.setCompanyName(companyName);
    this.setStateSubscription(stateSubscription);
//    TODO ignore these setters
    //    this.setVehicles();
//    this.setAddress();
//    this.setPhone();
  }

  public void output() {
    System.out.println("Transportadora:");
    System.out.println("companha: " + this.getCompanyName());
    System.out.println("inscricao estadual: " + this.getStateSubscription());
    System.out.println("ANTT: " + this.getAntt());
    this.getVehicles().forEach(Vehicle::output);
  }
}
