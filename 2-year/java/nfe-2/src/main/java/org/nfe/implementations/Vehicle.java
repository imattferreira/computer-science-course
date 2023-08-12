package org.nfe.implementations;

public class Vehicle {
    private String plate;
    private String uf;

  public Vehicle() {
    this.plate = "";
    this.uf = "";
  }

  public Vehicle(String plate, String uf) {
    this.plate = plate;
    this.uf = uf;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public void output() {
    System.out.println("Veiculo:");
    System.out.println("placa: " + this.getPlate());
    System.out.println("UF: " + this.getUf());
  }
}
