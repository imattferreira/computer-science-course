package org.nfe.implementations;

public class Phone {
  private int ddd;
    private int number;

  public Phone() {
    this.ddd = 0;
    this.number = 0;
  }

  public Phone(int ddd, int number) {
    this.ddd = ddd;
    this.number = number;
  }

  public int getDdd() {
    return this.ddd;
  }

  public void setDdd(int ddd) {
    this.ddd = ddd;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
