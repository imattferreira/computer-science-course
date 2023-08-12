package org.nfe.implementations;

import org.nfe.abstracts.Billing;

enum BankVoucherTypes {
  INPUT, OUTPUT
}

public class BankVoucher extends Billing {
  private BankVoucherTypes type;
  private int code;
  private String emissionDate;
  private String accessKey;
  private String protocol;

  public BankVoucher() {
    super();

    this.type = BankVoucherTypes.OUTPUT;
    this.code = 0;
    this.emissionDate = "";
    this.accessKey = "";
    this.protocol = "";
  }

  public BankVoucher(BankVoucherTypes type, int code, String emissionDate, String accessKey, String protocol, String expirationDate, int installments, double total) {
    super(expirationDate, installments, total);

    this.type = type;
    this.code = code;
    this.emissionDate = emissionDate;
    this.accessKey = accessKey;
    this.protocol = protocol;
  }

  public BankVoucher(BankVoucherTypes type, int code, String emissionDate, String accessKey, String protocol, String expirationDate, double total) {
    super(expirationDate, total);

    this.type = type;
    this.code = code;
    this.emissionDate = emissionDate;
    this.accessKey = accessKey;
    this.protocol = protocol;
  }

  public BankVoucher(int code, String emissionDate, String accessKey, String protocol, String expirationDate, int installments, double total) {
    super(expirationDate, installments, total);

    this.type = BankVoucherTypes.OUTPUT;
    this.code = code;
    this.emissionDate = emissionDate;
    this.accessKey = accessKey;
    this.protocol = protocol;
  }

  public BankVoucher(int code, String emissionDate, String accessKey, String protocol, String expirationDate, double total) {
    super(expirationDate, total);

    this.type = BankVoucherTypes.OUTPUT;
    this.code = code;
    this.emissionDate = emissionDate;
    this.accessKey = accessKey;
    this.protocol = protocol;
  }

  public BankVoucherTypes getType() {
    return type;
  }

  public void setType(BankVoucherTypes type) {
    this.type = type;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getEmissionDate() {
    return emissionDate;
  }

  public void setEmissionDate(String emissionDate) {
    this.emissionDate = emissionDate;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }
}
