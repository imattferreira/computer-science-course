package domain.implementations;

import domain.abstracts.Financeiro;

public class Pagar extends Financeiro {
  private Fornecedor fornecedor;
  private String boleto;

  public Pagar() {
    this.setFornecedor(new Fornecedor());
    this.setBoleto("");
  }

  public Pagar(Fornecedor fornecedor) {
    this.setFornecedor(fornecedor);
  }

  public Pagar(
          Fornecedor fornecedor,
          String boleto,
          int id,
          int numero,
          String emissao,
          String vencimento,
          String pagamento,
          double valor,
          double juros,
          double multa,
          double desconto,
          double total) {
    super(id, numero, emissao, vencimento, pagamento, valor, juros, multa, desconto, total);

    this.setFornecedor(fornecedor);
    this.setBoleto(boleto);
  }

  public Fornecedor getFornecedor() {
    return this.fornecedor;
  }

  public void setFornecedor(Fornecedor fornecedor) {
    this.fornecedor = fornecedor;
  }

  public String getBoleto() {
    return this.boleto;
  }

  public void setBoleto(String boleto) {
    this.boleto = boleto;
  }

  @Override
  public void entrar() {
    super.entrar();

    System.out.println("Boleto: ");
    String boleto = leia.next();
    this.setBoleto(boleto);
  }

  @Override
  public void imprimir() {
    super.imprimir();

    System.out.println("---------------------");
    System.out.println("| Pagar");
    System.out.println("|> Boleto............" + this.getBoleto());
    System.out.println("---------------------");

    this.getFornecedor().imprimir();
  }
}
