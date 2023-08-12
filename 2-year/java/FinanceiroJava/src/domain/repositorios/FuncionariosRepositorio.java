package domain.repositorios;

import java.util.ArrayList;

import domain.implementations.Cliente;
import domain.implementations.Funcionario;
import domain.interfaces.InterfaceRepositorio;

public class FuncionariosRepositorio implements InterfaceRepositorio<Funcionario> {
  static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

  @Override
  public void popular(int qdd) {
    for (int i = 0; i <= qdd; i++) {
      this.incluir(i);
    }
  }

  @Override
  public void incluir(int index) {
    String parsed = Integer.toString(index);

    Funcionario funcionario = new Funcionario(
      "admissao " + parsed,
      "demissao " + parsed,
      "ctps " + parsed,
      index * 560,
      "cpf " + parsed,
      "rg " + parsed,
      "emissor " + parsed
    );

    funcionarios.add(funcionario);
  }

  @Override
  public void incluir(Funcionario novo) {
    funcionarios.add(novo);
  }

  @Override
  public void alterar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'alterar'");
  }

  public Funcionario consultarPosicao(int posicao) {
    return funcionarios.get(posicao);
  }

  public Funcionario consultar(int id) throws ClassNotFoundException{
    for (int i = 0; i < funcionarios.size(); i++) {
      Funcionario item = funcionarios.get(i);

      if (item.getId() == id) {
        return item;
      }
    }

    throw new ClassNotFoundException("Funcionario not found");
  }

  public Funcionario consultarPorNome(String nome) throws ClassNotFoundException {
    for (int i = 0; i < funcionarios.size(); i++) {
      Funcionario item = funcionarios.get(i);

      if (item.getNome().equals(nome)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Funcionario not found");
  }

  public Funcionario consultarPorCpf(String cpf) throws ClassNotFoundException {
    for (int i = 0; i < funcionarios.size(); i++) {
      Funcionario item = funcionarios.get(i);

      if (item.getCpf().equals(cpf)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Funcionario not found");
  }

  @Override
  public void excluir(int id) {
    ArrayList<Funcionario> novoFuncionarios = new ArrayList<Funcionario>();

    for (int i = 0; i < funcionarios.size(); i++) {
      Funcionario item = funcionarios.get(i);

      if (item.getId() != id) {
        novoFuncionarios.add(item);
      }
    }

    funcionarios = novoFuncionarios;
  }

}
