package domain.implementations;

import domain.abstracts.PessoaJuridica;

public class Fornecedor extends PessoaJuridica {
  private double limiteCompra;
  private String dataCadastro;
  private String site;

  public Fornecedor() {
    this.setLimiteCompra(0);
    this.setDataCadastro("");
    this.setSite("");
  }

  public Fornecedor(double limiteCompra, String dataCadastro, String site, String cnpj, String inscricao, String contato) {
    super(cnpj, inscricao, contato);

    this.setLimiteCompra(limiteCompra);
    this.setDataCadastro(dataCadastro);
    this.setSite(site);
  }

  public double getLimiteCompra() {
    return this.limiteCompra;
  }

  public void setLimiteCompra(double limiteCompra) {
    this.limiteCompra = limiteCompra;
  }

  public String getDataCadastro() {
    return this.dataCadastro;
  }

  public void setDataCadastro(String dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public String getSite() {
    return this.site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  @Override
  public void entrar() {
    super.entrar();

    System.out.println("Limite compra: ");
    Double limiteCompra = leia.nextDouble();
    this.setLimiteCompra(limiteCompra);

    System.out.println("Data cadastro: ");
    String dataCadastro = leia.next();
    this.setDataCadastro(dataCadastro);

    System.out.println("Site: ");
    String site = leia.next();
    this.setSite(site);
  }

  @Override
  public void imprimir() {
    super.imprimir();

    System.out.println("---------------------");
    System.out.println("| Fornecedor");
    System.out.println("|> Limite compra....." + this.getLimiteCompra());
    System.out.println("|> Data cadastro....." + this.getDataCadastro());
    System.out.println("|> Site.............." + this.getSite());
    System.out.println("---------------------");
  }
}
