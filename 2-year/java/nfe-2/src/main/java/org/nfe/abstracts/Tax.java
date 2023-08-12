package org.nfe.abstracts;


public abstract class Tax extends Entity {
  private final String type;
  private double percentage;
  private double value;

  public Tax(String type) {
    this.type = type;
  }

  public Tax(String type, double value) {
    this.type = type;
    this.value = value;
  }

  public Tax(String type, double value, double percentage) {
    this.type = type;
    this.value = value;
    this.percentage = percentage;
  }

  public String getType() {
    return type;
  }

  public double getPercentage() {
    return percentage;
  }

  public double getValue() {
    return value;
  }
}
