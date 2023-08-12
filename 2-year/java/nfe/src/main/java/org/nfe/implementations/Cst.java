package org.nfe.implementations;

import org.nfe.abstracts.Tax;

public class Cst extends Tax {
  public Cst(double value) {
    super("CST", value);
  }
}
