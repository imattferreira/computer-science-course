package domain.implementations;

import domain.abstracts.Financeiro;

public class Receber extends Financeiro {
  private Cliente cliente;
  private String notaFiscal;

  public Receber() {
    this.setCliente(new Cliente());
    this.setNotaFiscal("");
  }

  public Receber(Cliente cliente) {
    this.setCliente(cliente);
  }

  public Receber(
          Cliente cliente,
          String notaFiscal,
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

    this.setCliente(cliente);
    this.setNotaFiscal(notaFiscal);
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getNotaFiscal() {
    return this.notaFiscal;
  }

  public void setNotaFiscal(String notaFiscal) {
    this.notaFiscal = notaFiscal;
  }

  @Override
  public void entrar() {
    super.entrar();

    System.out.println("Nota Fiscal: ");
    String notaFiscal = leia.next();
    this.setNotaFiscal(notaFiscal);
  }

  @Override
  public void imprimir() {
    super.imprimir();

    System.out.println("---------------------");
    System.out.println("| Receber");
    System.out.println("|> Nota Fiscal....." + this.getNotaFiscal());
    System.out.println("---------------------");

    this.getCliente().imprimir();
  }
}
