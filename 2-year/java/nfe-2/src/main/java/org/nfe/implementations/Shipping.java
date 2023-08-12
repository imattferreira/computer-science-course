package org.nfe.implementations;

import org.nfe.abstracts.Entity;
import org.nfe.abstracts.InputTypes;

import java.util.Scanner;

public class Shipping extends Entity {
  private ShippingCompany shippingCompany;
  private double grossWeight;
  private double netWeight;
  private BusinessClient businessClient;
  private PrivateIndividualClient privateIndividualClient;
  private Supplier supplier;
  private Vehicle vehicle;

  public Shipping() {}

  public Shipping(
          ShippingCompany shippingCompany,
          double grossWeight,
          double netWeight,
          BusinessClient businessClient,
          PrivateIndividualClient privateIndividualClient,
          Supplier supplier,
          Vehicle vehicle) {
    this.shippingCompany = shippingCompany;
    this.grossWeight = grossWeight;
    this.businessClient = businessClient;
    this.privateIndividualClient = privateIndividualClient;
    this.supplier = supplier;
    this.vehicle = vehicle;
    this.netWeight = netWeight;
  }

  public ShippingCompany getShippingCompany() {
    return shippingCompany;
  }

  public void setShippingCompany(ShippingCompany shippingCompany) {
    this.shippingCompany = shippingCompany;
  }

  public double getGrossWeight() {
    return grossWeight;
  }

  public void setGrossWeight(double grossWeight) {
    this.grossWeight = grossWeight;
  }

  public double getNetWeight() {
    return netWeight;
  }

  public void setNetWeight(double netWeight) {
    this.netWeight = netWeight;
  }

  public BusinessClient getBusinessClient() {
    return businessClient;
  }

  public void setBusinessClient(BusinessClient businessClient) {
    this.businessClient = businessClient;
  }

  public PrivateIndividualClient getPrivateIndividualClient() {
    return privateIndividualClient;
  }

  public void setPrivateIndividualClient(PrivateIndividualClient privateIndividualClient) {
    this.privateIndividualClient = privateIndividualClient;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public void create(Scanner input) {

//    TODO
//    String shippingCompany = (String) this.inputWithoutException(input, "Digite o nome da empresa de envio", InputTypes.STRING);
//
//    this.setBusinessClient();
//    this.setGrossWeight();
//    this.setSupplier();
//    this.setNetWeight();
//    this.setVehicle();
//    this.setPrivateIndividualClient();
//    this.setShippingCompany(new ShippingCompany());
  }

  public void output() {
    System.out.println("Envio:");
    this.getShippingCompany().output();
    System.out.println("peso bruto: " + this.getGrossWeight());
    System.out.println("peso liquido: " + this.getNetWeight());
    this.getBusinessClient().output();
    this.getPrivateIndividualClient().output();
    this.getSupplier().output();
    this.getVehicle().output();
  }
}
