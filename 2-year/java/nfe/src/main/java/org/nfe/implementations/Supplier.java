package org.nfe.implementations;

import org.nfe.abstracts.People;

public class Supplier extends People {
  private String cnpj;
  private String companyName;
  private String stateSubscription;
  public Supplier() {
    this.cnpj = "";
    this.companyName = "";
    this.stateSubscription = "";
  }

  public Supplier(String cnpj, String companyName, String stateSubscription, Address address, Phone phone) {
    super(address, phone);

    this.cnpj = cnpj;
    this.companyName = companyName;
    this.stateSubscription = stateSubscription;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getStateSubscription() {
    return stateSubscription;
  }

  public void setStateSubscription(String stateSubscription) {
    this.stateSubscription = stateSubscription;
  }

}
