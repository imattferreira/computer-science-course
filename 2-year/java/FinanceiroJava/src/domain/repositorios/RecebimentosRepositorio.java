package domain.repositorios;

import java.util.ArrayList;

import domain.implementations.Cliente;
import domain.implementations.Endereco;
import domain.implementations.Receber;
import domain.interfaces.InterfaceRepositorio;

public class RecebimentosRepositorio implements InterfaceRepositorio<Receber> {
  static ArrayList<Receber> recebimentos = new ArrayList<Receber>();

  @Override
  public void popular(int qdd) {
    for (int i = 0; i <= qdd; i++) {
      this.incluir(i);
    }
  }

  @Override
  public void incluir(int index) {
    String parsed = Integer.toString(index);

    Receber recebimento = new Receber(
      ClientesRepositorio.clientes.get(index),
      "nota fiscal " + parsed,
      index,
      index,
      "emissao: " + parsed,
      "vencimento " + parsed,
      "pagamento " + parsed,
      87 * index,
      0,
      0,
      0,
      87 * index
    );

    recebimentos.add(recebimento);
  }

  @Override
  public void incluir(Receber novo) {
    recebimentos.add(novo);
  }

  @Override
  public void alterar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'alterar'");
  }

  public Receber consultarPosicao(int posicao) {
    return recebimentos.get(posicao);
  }

  public Receber consultar(int id) throws ClassNotFoundException {
    for (int i = 0; i < recebimentos.size(); i++) {
      Receber item = recebimentos.get(i);

      if (item.getId() == id) {
        return item;
      }
    }

    throw new ClassNotFoundException("Recebimento not found");
  }

  public Receber consultarPorValor(Double valor) throws ClassNotFoundException {
    for (int i = 0; i < this.recebimentos.size(); i++) {
      Receber item = this.recebimentos.get(i);

      if (item.getValor() == valor) {
        return item;
      }
    }

    throw new ClassNotFoundException("Recebimento not found");
  }

  public Receber consultarPorNotaFiscal(String notaFiscal) throws ClassNotFoundException {
    for (int i = 0; i < this.recebimentos.size(); i++) {
      Receber item = this.recebimentos.get(i);

      if (item.getNotaFiscal().equals(notaFiscal)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Recebimento not found");
  }

  public Receber consultarPorNome(String nome) throws ClassNotFoundException {
    for (int i = 0; i < this.recebimentos.size(); i++) {
      Receber item = this.recebimentos.get(i);

      if (item.getCliente().getNome().equals(nome)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Recebimento not found");
  }

  @Override
  public void excluir(int id) {
    ArrayList<Receber> novoRecebimentos = new ArrayList<Receber>();

    for (int i = 0; i < recebimentos.size(); i++) {
      Receber item = recebimentos.get(i);

      if (item.getId() != id) {
        novoRecebimentos.add(item);
      }
    }

    recebimentos = novoRecebimentos;
  }

}
