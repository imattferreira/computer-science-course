package org.nfe.abstracts;

public abstract class Operation extends Entity {
  private final String type;
  public Operation(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
