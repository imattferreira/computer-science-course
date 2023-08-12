package org.nfe.implementations;

import org.nfe.abstracts.People;

public class BusinessClient extends People {
  private String cnpj;
  private String companyName;
  private String stateSubscription;

  public BusinessClient() {
    this.cnpj = "";
    this.companyName = "";
    this.stateSubscription = "";
  }

  public BusinessClient(String companyName, String cnpj, String stateSubscription, Address address, Phone phone) {
    super(address, phone);

    this.companyName = companyName;
    this.cnpj = cnpj;
    this.stateSubscription = stateSubscription;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
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
}
