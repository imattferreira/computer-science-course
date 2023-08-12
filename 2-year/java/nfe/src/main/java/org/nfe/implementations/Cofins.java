package org.nfe.implementations;

import org.nfe.abstracts.Tax;

public class Cofins extends Tax {
  public Cofins(double value) {
    super("COFINS", value);
  }
}
