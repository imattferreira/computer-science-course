package domain.implementations;

import domain.abstracts.PessoaFisica;

public class Funcionario extends PessoaFisica {
  private String dataAdmissao;
  // TODO possibly optional
  private String dataDemissao;
  private String ctps;
  private double salario;

  public Funcionario() {
    this.setDataAdmissao("");
    this.setDataDemissao("");
    this.setCtps("");
    this.setSalario(0);
  }

  public Funcionario(String dataAdmissao, String dataDemissao, String ctps, double salario, String cpf, String rg, String emissor) {
    super(cpf, rg, emissor);

    this.setDataAdmissao(dataAdmissao);
    this.setDataDemissao(dataDemissao);
    this.setCtps(ctps);
    this.setSalario(salario);
  }

  public String getDataAdmissao() {
    return this.dataAdmissao;
  }

  public void setDataAdmissao(String dataAdmissao) {
    this.dataAdmissao = dataAdmissao;
  }

  public String getDataDemissao() {
    return this.dataDemissao;
  }

  public void setDataDemissao(String dataDemissao) {
    this.dataDemissao = dataDemissao;
  }

  public String getCtps() {
    return this.ctps;
  }

  public void setCtps(String ctps) {
    this.ctps = ctps;
  }

  public double getSalario() {
    return this.salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  @Override
  public void entrar() {
    super.entrar();

    System.out.println("Data admissao: ");
    String dataAdmissao = leia.next();
    this.setDataAdmissao(dataAdmissao);

    System.out.println("Data demissao: ");
    String dataDemissao = leia.next();
    this.setDataDemissao(dataDemissao);

    System.out.println("CTPS: ");
    String ctps = leia.next();
    this.setCtps(ctps);

    System.out.println("Salario: ");
    double salario = leia.nextDouble();
    this.setSalario(salario);
  }

  @Override
  public void imprimir() {
    super.imprimir();

    System.out.println("---------------------");
    System.out.println("| Funcionário");
    System.out.println("|> Data admissão....." + this.getDataAdmissao());
    System.out.println("|> Data demissão....." + this.getDataDemissao());
    System.out.println("|> CTPS.............." + this.getCtps());
    System.out.println("|> Salario..........." + this.getSalario());
    System.out.println("---------------------");
  }
}
