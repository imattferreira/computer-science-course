package org.nfe.implementations;

import org.nfe.abstracts.People;

public class PrivateIndividualClient extends People {
  private String cpf;
  private String name;

  public PrivateIndividualClient() {
    this.name = "";
    this.cpf = "";
  }

  public PrivateIndividualClient(String name, String cpf, Address address, Phone phone) {
    super(address, phone);

    this.name = name;
    this.cpf = cpf;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
