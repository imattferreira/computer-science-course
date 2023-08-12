package org.nfe.implementations;

import org.nfe.abstracts.InputTypes;

import java.util.ArrayList;
import java.util.Scanner;

public class Nfe extends Sale {
  private int id;
  private ArrayList<Product> products;
  private Shipping shipping;
  private Address address;
  private BusinessClient businessClient;
  private PrivateIndividualClient privateIndividualClient;
  private BankVoucher bankVoucher;
  private Supplier supplier;


  public Nfe() {
  }

  public Nfe(
    int id,
    ArrayList<Product> products,
    Shipping shipping,
    Address address,
    BusinessClient businessClient,
    PrivateIndividualClient privateIndividualClient,
    BankVoucher bankVoucher,
    Supplier supplier) {
    this.id = id;
    this.products = products;
    this.shipping = shipping;
    this.address = address;
    this.businessClient = businessClient;
    this.privateIndividualClient = privateIndividualClient;
    this.bankVoucher = bankVoucher;
    this.supplier = supplier;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public PrivateIndividualClient getPrivateIndividualClient() {
    return privateIndividualClient;
  }

  public void setPrivateIndividualClient(PrivateIndividualClient privateIndividualClient) {
    this.privateIndividualClient = privateIndividualClient;
  }

  public BusinessClient getBusinessClient() {
    return businessClient;
  }

  public void setBusinessClient(BusinessClient businessClient) {
    this.businessClient = businessClient;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public BankVoucher getBankVoucher() {
    return bankVoucher;
  }

  public void setBankVoucher(BankVoucher bankVoucher) {
    this.bankVoucher = bankVoucher;
  }

  public Shipping getShipping() {
    return shipping;
  }

  public void setShipping(Shipping shipping) {
    this.shipping = shipping;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public void create(Scanner input) {
    ArrayList<Product> products = new ArrayList<Product>();
    Product product = new Product();
    Shipping shipping = new Shipping();
    Address address = new Address();
    BusinessClient businessClient = new BusinessClient();
    PrivateIndividualClient privateIndividualClient = new PrivateIndividualClient();
    BankVoucher bankVoucher = new BankVoucher();
    Supplier supplier = new Supplier();

    int id = (int) this.inputWithoutException(input, "id: ", InputTypes.INT);
    address.create(input);
    shipping.create(input);
    businessClient.create(input);
    privateIndividualClient.create(input);
    supplier.create(input);
    product.create(input);
    bankVoucher.create(input);

    products.add(product);

    this.setId(id);
    this.setAddress(address);
    this.setBankVoucher(bankVoucher);
    this.setBusinessClient(businessClient);
    this.setProducts(products);
    this.setShipping(shipping);
    this.setSupplier(supplier);
    this.setPrivateIndividualClient(privateIndividualClient);

  }

  public void output() {
//    TODO
    System.out.println("========");
    System.out.println("NFE:");
    System.out.println("id " + this.id);
    this.supplier.output();
    this.businessClient.output();
    this.products.forEach(Product::output);
    this.shipping.output();
    this.address.output();
    this.bankVoucher.output();
    this.privateIndividualClient.output();
  }
}
