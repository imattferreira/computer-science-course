package org.nfe.abstracts;

public abstract class Billing extends Entity {
  private double total;
  private int installments;
  private String expirationDate;

  public Billing() {
    this.total = 0;
    this.installments = 1;
    this.expirationDate = "";
  }

  public Billing(String expirationDate, double total) {
    this.expirationDate = expirationDate;
    this.installments = 1;
    this.total = total;
  }

  public Billing(String expirationDate, int installments, double total) {
    this.expirationDate = expirationDate;
    this.installments = installments;
    this.total = total;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public int getInstallments() {
    return installments;
  }

  public void setInstallments(int installments) {
    this.installments = installments;
  }

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }
}
