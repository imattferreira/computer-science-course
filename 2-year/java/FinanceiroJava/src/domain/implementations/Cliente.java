package domain.implementations;

import domain.abstracts.PessoaJuridica;

public class Cliente extends PessoaJuridica {
  private double limiteCredito;
  private Endereco enderecoCobranca;

  public Cliente() {
    this.setLimiteCredito(0);
    this.setEnderecoCobranca(new Endereco());
  }

  public Cliente(double limiteCredito, Endereco enderecoCobranca, String cnpj, String inscricao, String contato) {
    super(cnpj, inscricao, contato);

    this.setLimiteCredito(limiteCredito);
    this.setEnderecoCobranca(enderecoCobranca);
  }

  public double getLimiteCredito() {
    return this.limiteCredito;
  }

  public void setLimiteCredito(double limiteCredito) {
    this.limiteCredito = limiteCredito;
  }

  public Endereco getEnderecoCobranca() {
    return this.enderecoCobranca;
  }

  public void setEnderecoCobranca(Endereco enderecoCobranca) {
    this.enderecoCobranca = enderecoCobranca;
  }

  @Override
  public void entrar() {
    super.entrar();

    System.out.println("Limite credito: ");
    Double limiteCredito = leia.nextDouble();
    System.out.println(limiteCredito);
    this.setLimiteCredito(limiteCredito);
    System.out.println(this.getLimiteCredito());

    System.out.println("Endereco Cobranca: ");
    Endereco endereco = new Endereco();

    endereco.entrar();

    this.setEnderecoCobranca(endereco);
  }

  @Override
  public void imprimir() {
    super.imprimir();

    System.out.println("---------------------");
    System.out.println("| Cliente");
    System.out.println("|> Limite Credito...." + this.getLimiteCredito());
    System.out.println("---------------------");

    getEnderecoCobranca().imprimir();
  }
}
