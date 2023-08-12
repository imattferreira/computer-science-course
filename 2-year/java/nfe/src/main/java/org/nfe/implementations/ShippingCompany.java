package org.nfe.implementations;

import org.nfe.abstracts.People;

import java.util.ArrayList;

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
}
