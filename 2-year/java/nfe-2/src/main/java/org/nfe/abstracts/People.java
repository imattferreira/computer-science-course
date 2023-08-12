package org.nfe.abstracts;

import org.nfe.implementations.Address;
import org.nfe.implementations.Phone;

public abstract class People extends Entity {
  private Address address;
  private Phone phone;

  public People() {
    this.address = new Address();
    this.phone = new Phone();
  }

  public People(Address address, Phone phone) {
    this.address = address;
    this.phone = phone;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Phone getPhone() {
    return phone;
  }

  public void setPhone(Phone phone) {
    this.phone = phone;
  }
}
