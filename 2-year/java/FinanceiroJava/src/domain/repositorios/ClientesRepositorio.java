package domain.repositorios;

import java.util.ArrayList;

import domain.implementations.Cliente;
import domain.implementations.Endereco;
import domain.interfaces.InterfaceRepositorio;

public class ClientesRepositorio implements InterfaceRepositorio<Cliente> {
  static ArrayList<Cliente> clientes = new ArrayList<Cliente>();

  @Override
  public void popular(int qdd) {
    for (int i = 0; i <= qdd; i++) {
      this.incluir(i);
    }
  }

  @Override
  public void incluir(int index) {
    String parsed = Integer.toString(index);

    Cliente cliente = new Cliente(
      index * 1200,
      new Endereco(
        "logradouro " + parsed,
        "numero" + parsed,
        "complemento" + parsed,
        "bairro" + parsed,
        "cidade" + parsed,
        "estado" + parsed,
        index
      ),
      "cnpj " + parsed,
      "inscricao " + parsed,
      "contato " + parsed
    );

    clientes.add(cliente);
  }

  @Override
  public void incluir(Cliente novo) {
    clientes.add(novo);
  }

  @Override
  public void alterar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'alterar'");
  }

  public Cliente consultarPosicao(int posicao) {
    return clientes.get(posicao);
  }

  public Cliente consultar(int id) throws ClassNotFoundException {
    for (int i = 0; i < clientes.size(); i++) {
      Cliente item = clientes.get(i);

      if (item.getId() == id) {
        return item;
      }
    }

    throw new ClassNotFoundException("Cliente not found");
  }

  public Cliente consultarPorNome(String nome) throws ClassNotFoundException {
    for (int i = 0; i < clientes.size(); i++) {
      Cliente item = clientes.get(i);

      if (item.getNome().equals(nome)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Cliente not found");
  }

  public Cliente consultarPorCnpj(String cnpj) throws ClassNotFoundException {
    for (int i = 0; i < clientes.size(); i++) {
      Cliente item = clientes.get(i);

      if (item.getCnpj().equals(cnpj)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Cliente not found");
  }

  @Override
  public void excluir(int id) {
    ArrayList<Cliente> novoClientes = new ArrayList<Cliente>();

    for (int i = 0; i < clientes.size(); i++) {
      Cliente item = clientes.get(i);

      if (item.getId() != id) {
        novoClientes.add(item);
      }
    }

    clientes = novoClientes;
  }
}
