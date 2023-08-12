package domain.repositorios;

import java.util.ArrayList;

import domain.implementations.Cliente;
import domain.implementations.Fornecedor;
import domain.interfaces.InterfaceRepositorio;

public class FornecedoresRepositorio implements InterfaceRepositorio<Fornecedor> {
  static ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

  @Override
  public void popular(int qdd) {
    for (int i = 0; i <= qdd; i++) {
      this.incluir(i);
    }
  }

  @Override
  public void incluir(int index) {
    String parsed = Integer.toString(index);

    Fornecedor fornecedor = new Fornecedor(
      1400 * index,
      "data " + parsed,
      "site " + parsed,
      "cnpj " + parsed,
      "inscricao " + parsed,
      "contato " + parsed
    );

    fornecedores.add(fornecedor);
  }

  @Override
  public void incluir(Fornecedor novo) {
    fornecedores.add(novo);
  }

  @Override
  public void alterar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'alterar'");
  }

  public Fornecedor consultarPosicao(int posicao) {
    return fornecedores.get(posicao);
  }

  public Fornecedor consultar(int id) throws ClassNotFoundException {
    for (int i = 0; i < fornecedores.size(); i++) {
      Fornecedor item = fornecedores.get(i);

      if (item.getId() == id) {
        return item;
      }
    }

    throw new ClassNotFoundException("Fornecedor not found");
  }

  public Fornecedor consultarPorCnpj(String cnpj) throws ClassNotFoundException {
    for (int i = 0; i < fornecedores.size(); i++) {
      Fornecedor item = fornecedores.get(i);

      if (item.getCnpj().equals(cnpj)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Fornecedor not found");
  }

  @Override
  public void excluir(int id) {
    ArrayList<Fornecedor> novoFornecedors = new ArrayList<Fornecedor>();

    for (int i = 0; i < fornecedores.size(); i++) {
      Fornecedor item = fornecedores.get(i);

      if (item.getId() != id) {
        novoFornecedors.add(item);
      }
    }

    fornecedores = novoFornecedors;
  }
}
