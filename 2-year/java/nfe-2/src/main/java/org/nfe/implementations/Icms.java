package org.nfe.implementations;

import org.nfe.abstracts.Tax;

public class Icms extends Tax {
  public Icms(double value) {
    super("ICMS", value);
  }
}
