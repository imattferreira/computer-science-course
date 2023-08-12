package domain.repositorios;

import java.util.ArrayList;

import domain.implementations.Cliente;
import domain.implementations.Fornecedor;
import domain.implementations.Pagar;
import domain.interfaces.InterfaceRepositorio;

public class PagamentosRepositorio implements InterfaceRepositorio<Pagar> {
  static ArrayList<Pagar> pagamentos = new ArrayList<Pagar>();

  @Override
  public void popular(int qdd) {
    for (int i = 0; i <= qdd; i++) {
      this.incluir(i);
    }
  }

  @Override
  public void incluir(int index) {
    String parsed = Integer.toString(index);

    Pagar pagamento = new Pagar(
      FornecedoresRepositorio.fornecedores.get(index),
      "boleto " + parsed,
      index,
      index * 3,
      "emissao" + parsed,
      "vencimento" + parsed,
      "pagamento" + parsed,
      index * 76,
      0,
      0,
      index * 10,
      index * 76 - index * 10
    );

    pagamentos.add(pagamento);
  }

  @Override
  public void incluir(Pagar novo) {
    pagamentos.add(novo);
  }

  @Override
  public void alterar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'alterar'");
  }

   public Pagar consultarPosicao(int posicao) {
    return pagamentos.get(posicao);
   }

  public Pagar consultar(int numero)throws ClassNotFoundException {
    for (int i = 0; i < pagamentos.size(); i++) {
      Pagar item = pagamentos.get(i);

      if (item.getNumero() == numero) {
        return item;
      }
    }

    throw new ClassNotFoundException("Pagamento not found");
  }

  public Pagar consultarPorCnpjFornecedor(String cnpj)throws ClassNotFoundException {
    for (int i = 0; i < pagamentos.size(); i++) {
      Pagar item = pagamentos.get(i);

      if (item.getFornecedor().getCnpj().equals(cnpj)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Pagamento not found");
  }

  public Pagar consultarPorValor(double valor) throws ClassNotFoundException{
    for (int i = 0; i < pagamentos.size(); i++) {
      Pagar item = pagamentos.get(i);

      if (item.getValor() == valor) {
        return item;
      }
    }

    throw new ClassNotFoundException("Pagamento not found");
  }

  public Pagar consultarPorBoleto(String boleto) throws ClassNotFoundException {
    for (int i = 0; i < pagamentos.size(); i++) {
      Pagar item = pagamentos.get(i);

      if (item.getBoleto().equals(boleto)) {
        return item;
      }
    }

    throw new ClassNotFoundException("Pagamento not found");
  }

  @Override
  public void excluir(int id) {
    ArrayList<Pagar> novoPagamentos = new ArrayList<Pagar>();

    for (int i = 0; i < pagamentos.size(); i++) {
      Pagar item = pagamentos.get(i);

      if (item.getId() != id) {
        novoPagamentos.add(item);
      }
    }

    pagamentos = novoPagamentos;
  }

}
