package domain.abstracts;

import domain.interfaces.InterfaceCadastro;

public class Financeiro implements InterfaceCadastro {
  private int id;
  private int numero;
  private String emissao;
  private String vencimento;
  private String pagamento;
  private double valor;
  private double juros;
  private double multa;
  private double desconto;
  private double total;

  public Financeiro() {
    this.setId(0);
    this.setNumero(0);
    this.setEmissao("");
    this.setVencimento("");
    this.setPagamento("");
    this.setValor(0);
    this.setJuros(0);
    this.setMulta(0);
    this.setDesconto(0);
    this.setTotal(0);
  }

  public Financeiro(
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
    this.setId(id);
    this.setNumero(numero);
    this.setEmissao(emissao);
    this.setVencimento(vencimento);
    this.setPagamento(pagamento);
    this.setValor(valor);
    this.setJuros(juros);
    this.setMulta(multa);
    this.setDesconto(desconto);
    this.setTotal(total);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNumero() {
    return this.numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getEmissao() {
    return this.emissao;
  }

  public void setEmissao(String emissao) {
    this.emissao = emissao;
  }

  public String getVencimento() {
    return this.vencimento;
  }

  public void setVencimento(String vencimento) {
    this.vencimento = vencimento;
  }

  public String getPagamento() {
    return this.pagamento;
  }

  public void setPagamento(String pagamento) {
    this.pagamento = pagamento;
  }

  public double getValor() {
    return this.valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public double getJuros() {
    return this.juros;
  }

  public void setJuros(double juros) {
    this.juros = juros;
  }

  public double getMulta() {
    return this.multa;
  }

  public void setMulta(double multa) {
    this.multa = multa;
  }

  public double getDesconto() {
    return this.desconto;
  }

  public void setDesconto(double desconto) {
    this.desconto = desconto;
  }

  public double getTotal() {
    return this.total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  @Override
  public void entrar() {
    System.out.println("ID:");
    int id = leia.nextInt();
    this.setId(id);

    System.out.println("Numero:");
    int numero = leia.nextInt();
    this.setNumero(numero);

    System.out.println("Emissao:");
    String emissao = leia.next();
    this.setEmissao(emissao);

    System.out.println("Pagamento:");
    String pagamento = leia.next();
    this.setPagamento(pagamento);

    System.out.println("Valor:");
    double valor = leia.nextDouble();
    this.setValor(valor);

    System.out.println("Juros:");
    double juros = leia.nextDouble();
    this.setJuros(juros);

    System.out.println("Multa:");
    double multa = leia.nextDouble();
    this.setMulta(multa);

    System.out.println("Desconto:");
    double desconto = leia.nextDouble();
    this.setDesconto(desconto);

    System.out.println("Vencimento:");
    String vencimento = leia.next();
    this.setVencimento(vencimento);

    double total = this.calcularTotal();
    this.setTotal(total);
  }

  @Override
  public void imprimir() {
    System.out.println("---------------------");
    System.out.println("| Financeiro");
    System.out.println("|> ID................" + this.getId());
    System.out.println("|> Numero............" + this.getNumero());
    System.out.println("|> Emissão..........." + this.getEmissao());
    System.out.println("|> Vencimento........" + this.getVencimento());
    System.out.println("|> Pagamento........." + this.getPagamento());
    System.out.println("|> Valor............." + this.getValor());
    System.out.println("|> Juros............." + this.getJuros());
    System.out.println("|> Multa............." + this.getMulta());
    System.out.println("|> Desconto.........." + this.getDesconto());
    System.out.println("|> Total............." + this.getTotal());
    System.out.println("---------------------");
  }

  protected double calcularTotal() {
    return this.getValor() + this.getJuros() - this.getDesconto();
  }
}
