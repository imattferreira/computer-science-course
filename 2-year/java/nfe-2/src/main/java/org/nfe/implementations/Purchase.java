package org.nfe.implementations;

import org.nfe.abstracts.Operation;

import java.util.ArrayList;

class PurchaseProduct {
  private Product product;
  private int quantity;
  private double unitPrice;
  private double totalPrice;

  public PurchaseProduct(Product product, int quantity, double unitPrice, double totalPrice) {
    this.product =  product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.totalPrice = totalPrice;
  }

  public PurchaseProduct(Product product, int quantity, double unitPrice) {
    this.product =  product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.totalPrice = quantity * unitPrice;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }
}

public class Purchase extends Operation {
    ArrayList<PurchaseProduct> products;
    Supplier supplier;

  public Purchase() {
    super("PURCHASE");

    this.products = new ArrayList<PurchaseProduct>();
    this.supplier = new Supplier();
  }

  public Purchase(Supplier supplier, ArrayList<PurchaseProduct> products) {
    super("purchase");

    this.products = products;
    this.supplier = supplier;
  }

  public ArrayList<PurchaseProduct> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<PurchaseProduct> products) {
    this.products = products;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }
}
