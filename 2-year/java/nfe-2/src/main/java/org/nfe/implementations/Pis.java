package org.nfe.implementations;

import org.nfe.abstracts.Tax;

public class Pis extends Tax {
  public Pis(double value) {
    super("PIS", value);
  }
}
