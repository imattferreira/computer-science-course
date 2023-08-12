package org.nfe.implementations;

public class Product {
  private String name;
  private int id;
  private double price;
  private Ipi ipiBasePrice;
  private Pis pisBasePrice;
  private Cofins cofinsBasePrice;
  private Cfop cfop;
  private Cst cst;
  private Ncm ncm;
  private int quantity;

  public Product() {}

  public Product(
    String name,
    int id,
    double price,
    Ipi ipiBasePrice,
    Pis pisBasePrice,
    Cofins cofinsBasePrice,
    Cfop cfop,
    Cst cst,
    Ncm ncm,
    int quantity) {
    this.name = name;
    this.ipiBasePrice = ipiBasePrice;
    this.id = id;
    this.cfop = cfop;
    this.pisBasePrice = pisBasePrice;
    this.cofinsBasePrice = cofinsBasePrice;
    this.cst = cst;
    this.ncm = ncm;
    this.quantity = quantity;
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Ipi getIpiBasePrice() {
    return ipiBasePrice;
  }

  public void setIpiBasePrice(Ipi ipiBasePrice) {
    this.ipiBasePrice = ipiBasePrice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Cofins getCofinsBasePrice() {
    return cofinsBasePrice;
  }

  public void setCofinsBasePrice(Cofins cofinsBasePrice) {
    this.cofinsBasePrice = cofinsBasePrice;
  }

  public Pis getPisBasePrice() {
    return pisBasePrice;
  }

  public void setPisBasePrice(Pis pisBasePrice) {
    this.pisBasePrice = pisBasePrice;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Cfop getCfop() {
    return cfop;
  }

  public void setCfop(Cfop cfop) {
    this.cfop = cfop;
  }

  public Cst getCst() {
    return cst;
  }

  public void setCst(Cst cst) {
    this.cst = cst;
  }

  public Ncm getNcm() {
    return ncm;
  }

  public void setNcm(Ncm ncm) {
    this.ncm = ncm;
  }
}
