/**
 ** Instructions:
 * - goto inside `src` dir (command: cd .\src)
 * - compile the project (javac Main.java)
 * - run the project (java Main)
 */
package domain;

import java.util.Scanner;

import domain.implementations.Cliente;
import domain.implementations.Fornecedor;
import domain.implementations.Funcionario;
import domain.implementations.Pagar;
import domain.implementations.Receber;
import domain.interfaces.InterfaceCadastro;
import domain.repositorios.*;

public class MenuPrincipal {
  private ClientesRepositorio clientesRepositorio = new ClientesRepositorio();
  private FornecedoresRepositorio fornecedoresRepositorio = new FornecedoresRepositorio();
  private FuncionariosRepositorio funcionariosRepositorio = new FuncionariosRepositorio();
  private PagamentosRepositorio pagamentosRepositorio = new PagamentosRepositorio();
  private RecebimentosRepositorio recebimentosRepositorio = new RecebimentosRepositorio();


  private Cliente cliente;
  private Fornecedor fornecedor;
  private Funcionario funcionario;
  private Receber receber;
  private Pagar pagar;
  private Scanner leia;

  public MenuPrincipal() {
    this.popularRepositorios();

    cliente = new Cliente();
    fornecedor = new Fornecedor();
    funcionario = new Funcionario();
    leia = new Scanner(System.in);
  }

  public void menu() {
    while (true) {
      System.out.println("---------------------");
      System.out.println("| Selecione: ");
      System.out.println("---------------------");
      System.out.println("|>   1. Funcionário");
      System.out.println("|>   2. Cliente");
      System.out.println("|>   3. Fornecedor");
      System.out.println("|>   4. Receber");
      System.out.println("|>   5. Pagar");
      System.out.println("|>   6. Fluxo de caixa");
      System.out.println("|>   0. Encerrar");
      System.out.println("---------------------");

      int selecionado = leia.nextInt();
      int opcao;

      switch (selecionado) {
        case 0:
          opcao = 0;
          System.exit(0);
          break;
        case 1:
          opcao = this.subMenu("Funcionário");
          break;
        case 2:
          opcao = this.subMenu("Cliente");
          break;
        case 3:
          opcao = this.subMenu("Fornecedor");
          break;
        case 4:
          opcao = this.subMenu("Receber");
          break;
        case 5:
          opcao = this.subMenu("Pagar");
          break;
        case 6:
          opcao = 0;
          this.mostrarFluxoCaixa();
          break;
        default:
          this.cleanup();
          System.out.println("|>> Selecione uma opção válida\n");
          continue;
      }

      // TODO create method to manipulate polymorphic methods of entities (Object)
      switch (selecionado) {
        case 1:
          this.iniciarOpcaoDaEntidadeSelecionada(funcionario, opcao);
          break;
        case 2:
          this.iniciarOpcaoDaEntidadeSelecionada(cliente, opcao);
          break;
        case 3:
          this.iniciarOpcaoDaEntidadeSelecionada(fornecedor, opcao);
          break;
        case 4:
          if (this.receber == null || opcao == 4) {
            receber = new Receber(this.cliente);

            if (this.cliente == null) {
              System.out.println("Antes, insira as informações do cliente");
            }

            if (opcao == 4) {
              break;
            }
          }

          this.iniciarOpcaoDaEntidadeSelecionada(receber, opcao);
          break;
        case 5:
          if (this.pagar == null || opcao == 4) {
            pagar = new Pagar(this.fornecedor);

            if (this.pagar == null) {
              System.out.println("Antes, insira as informações do cliente");
            }

            if (opcao == 4) {
              break;
            }
          }

          this.iniciarOpcaoDaEntidadeSelecionada(pagar, opcao);
          break;
        default:
          break;
      }
    }
  }

  private int subMenu(String entidade) {
    while (true) {
      String cabecalho = "| Cadastro de: " + entidade;
      int comprimentoDelimitador = cabecalho.length();
      // String delimitador = "-".repeat(comprimentoDelimitador);

      // System.out.println(delimitador);
      System.out.println(cabecalho);
      // System.out.println(delimitador);
      System.out.println("|>   a. Incluir");
      System.out.println("|>   b. Alterar");
      System.out.println("|>   c. Consultar");
      System.out.println("|>   d. Excluir");
      System.out.println("|>   e. Voltar");
      // System.out.println(delimitador);

      String selecionado = leia.next().toLowerCase();

      switch (selecionado) {
        case "a":
          return 1;
        case "b":
          return 2;
        case "c":
          return 3;
        case "d":
          return 4;
        case "e":
          return 0;
        default:
          this.cleanup();
          System.out.println("|>> Selecione uma opção válida\n");
          break;
      }
    }
  }

  private void cleanup() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private void iniciarOpcaoDaEntidadeSelecionada(InterfaceCadastro entidade, int opcao) {
    // TODO to edit, create a way to consider a skip (Enter) to non-rewrite the field
    if (opcao == 1 || opcao == 2) {
      entidade.entrar();
    }

    if (opcao == 4) {
      int id = 0;

      while (true) {
        try {
          System.out.println("Digite o ID");
          id = leia.nextInt();

          break;
        } catch (Exception error) {
          System.out.println("Parece que algo de errado aconteceu, tente novamente");
        }
      }

      if (entidade instanceof Cliente) {
        this.clientesRepositorio.excluir(id);
      } else if (entidade instanceof Fornecedor) {
        this.fornecedoresRepositorio.excluir(id);
      } else if (entidade instanceof Funcionario) {
        this.funcionariosRepositorio.excluir(id);
      } else if (entidade instanceof Pagar) {
        this.pagamentosRepositorio.excluir(id);
      } else if (entidade instanceof Receber) {
        this.recebimentosRepositorio.excluir(id);
      } else {
        // case should never occur
      }
    }

    if (opcao == 3) {
        if (entidade instanceof Cliente) {
          this.consultarCliente().imprimir();;
        } else if (entidade instanceof Fornecedor) {
          this.consultarFornecedor().imprimir();;
        } else if (entidade instanceof Funcionario) {
          this.consultarFuncionario().imprimir();;
        } else if (entidade instanceof Pagar) {
          this.consultarPagar().imprimir();;
        } else if (entidade instanceof Receber) {
          this.consultarReceber().imprimir();
        } else {
          // case should never occur
        }

    }
  }

  private void mostrarFluxoCaixa() {
    double totalRecebido = 0;
    double totalPago = 0;
    double saldo = 0;

    System.out.println("| Fluxo de caixa");
    System.out.println("| Vencimento  |  Crédito  |  Débito  |  Saldo   |\n");

    if (this.pagar != null) {
      System.out.printf(
          "|    %s    |  R$$0,00  |  R$%.2f  |  R$%.2f  |\n",
          this.pagar.getVencimento(),
          this.pagar.getTotal(),
          saldo -= this.pagar.getTotal());

      totalPago = this.pagar.getTotal();
    }

    if (this.receber != null) {
      System.out.printf(
          "|    %s    |  R$%.2f   |  R$0,00  |   R$%.2f  |\n",
          this.receber.getVencimento(),
          this.receber.getTotal(),
          saldo += this.receber.getTotal());

      totalRecebido = this.receber.getTotal();
    }

    System.out.printf(
        "|    Total    |   R$%.2f  |  R$%.2f  |  R$%.2f  |\n",
        totalRecebido,
        totalPago,
        saldo);
  }

  private void popularRepositorios() {
    this.clientesRepositorio.popular(50);
    this.fornecedoresRepositorio.popular(50);
    this.funcionariosRepositorio.popular(50);
    this.recebimentosRepositorio.popular(50);
    this.pagamentosRepositorio.popular(50);
  }

  private Cliente consultarCliente() {
    while (true) {
      String cabecalho = "| Consulta de: Cliente";
      int comprimentoDelimitador = cabecalho.length();
      // String delimitador = "-".repeat(comprimentoDelimitador);

      // System.out.println(delimitador);
      System.out.println(cabecalho);
      // System.out.println(delimitador);
      System.out.println("|>   a. Posição da lista");
      System.out.println("|>   b. CPF");
      System.out.println("|>   c. Nome");
      // System.out.println(delimitador);

      String selecionado = leia.next().toLowerCase();

      try {
        switch (selecionado) {
          case "a":
            int posicao = leia.nextInt();

            return this.clientesRepositorio.consultarPosicao(posicao);
          case "b":
            String cnpj = leia.next();

            return this.clientesRepositorio.consultarPorCnpj(cnpj);
          case "c":
            String nome = leia.next();

            return this.clientesRepositorio.consultarPorNome(nome);
          default:
            this.cleanup();
            System.out.println("|>> Selecione uma opção válida\n");
            break;
        }
      } catch (Error err) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      } catch (ClassNotFoundException e) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      }
    }
  }

  private Fornecedor consultarFornecedor() {
    while (true) {
      String cabecalho = "| Consulta de: Fornecedor";
      int comprimentoDelimitador = cabecalho.length();
      // String delimitador = "-".repeat(comprimentoDelimitador);

      // System.out.println(delimitador);
      System.out.println(cabecalho);
      // System.out.println(delimitador);
      System.out.println("|>   a. Posição da lista");
      System.out.println("|>   b. CNPJ");
      System.out.println("|>   c. ID");
      // System.out.println(delimitador);

      String selecionado = leia.next().toLowerCase();

      try {
        switch (selecionado) {
          case "a":
            int posicao = leia.nextInt();

            return this.fornecedoresRepositorio.consultarPosicao(posicao);
          case "b":
            String cnpj = leia.next();

            return this.fornecedoresRepositorio.consultarPorCnpj(cnpj);
          case "c":
            int id = leia.nextInt();

            return this.fornecedoresRepositorio.consultar(id);
          default:
            this.cleanup();
            System.out.println("|>> Selecione uma opção válida\n");
            break;
        }
      } catch (Error err) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      } catch (ClassNotFoundException e) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      }
    }
  }

  private Pagar consultarPagar() {
    while (true) {
      String cabecalho = "| Consulta de: Pagar";
      int comprimentoDelimitador = cabecalho.length();
      // String delimitador = "-".repeat(comprimentoDelimitador);

      // System.out.println(delimitador);
      System.out.println(cabecalho);
      // System.out.println(delimitador);
      System.out.println("|>   a. Numero");
      System.out.println("|>   b. CNPJ do fornecedor");
      System.out.println("|>   c. Valor");
      System.out.println("|>   d. Boleto");
      // System.out.println(delimitador);

      String selecionado = leia.next().toLowerCase();

      try {
        switch (selecionado) {
          case "a":
            int numero = leia.nextInt();

            return this.pagamentosRepositorio.consultar(numero);
          case "b":
            String cnpj = leia.next();

            return this.pagamentosRepositorio.consultarPorCnpjFornecedor(cnpj);
          case "c":
            int valor = leia.nextInt();

            return this.pagamentosRepositorio.consultarPorValor(valor);
          case "d":
            String boleto = leia.next();

            return this.pagamentosRepositorio.consultarPorBoleto(boleto);
          default:
            this.cleanup();
            System.out.println("|>> Selecione uma opção válida\n");
            break;
        }
      } catch (Error err) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      } catch (ClassNotFoundException e) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      }
    }
  }

  private Funcionario consultarFuncionario() {
    while (true) {
      String cabecalho = "| Consulta de: Funcionario";
      int comprimentoDelimitador = cabecalho.length();
      // String delimitador = "-".repeat(comprimentoDelimitador);

      // System.out.println(delimitador);
      System.out.println(cabecalho);
      // System.out.println(delimitador);
      System.out.println("|>   a. Posicao");
      System.out.println("|>   b. CPF");
      System.out.println("|>   c. Nome");
      // System.out.println(delimitador);

      String selecionado = leia.next().toLowerCase();

      try {
        switch (selecionado) {
          case "a":
            int posicao = leia.nextInt();

            return this.funcionariosRepositorio.consultarPosicao(posicao);
          case "b":
            String cpf = leia.next();

            return this.funcionariosRepositorio.consultarPorCpf(cpf);
          case "c":
            String nome = leia.next();

            return this.funcionariosRepositorio.consultarPorNome(nome);
          default:
            this.cleanup();
            System.out.println("|>> Selecione uma opção válida\n");
            break;
        }
      } catch (Error err) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      } catch (ClassNotFoundException e) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      }
    }
  }

  private Receber consultarReceber() {
    while (true) {
      String cabecalho = "| Consulta de: Receber";
      int comprimentoDelimitador = cabecalho.length();

      // String delimitador = "-".repeat(comprimentoDelimitador);

      // System.out.println(delimitador);
      System.out.println(cabecalho);
      // System.out.println(delimitador);
      System.out.println("|>   a. Nome do cliente");
      System.out.println("|>   b. Numero");
      System.out.println("|>   c. Valor");
      System.out.println("|>   d. Nota fiscal");
      // System.out.println(delimitador);

      String selecionado = leia.next().toLowerCase();

      try {
        switch (selecionado) {
          case "a":
            String nomeCliente = leia.next();

            return this.recebimentosRepositorio.consultarPorNome(nomeCliente);
          case "b":
            int id = leia.nextInt();

            return this.recebimentosRepositorio.consultar(id);
          case "c":
            double valor = leia.nextDouble();

            return this.recebimentosRepositorio.consultarPorValor(valor);
          case "d":
            String notaFiscal = leia.next();

            return this.recebimentosRepositorio.consultarPorNotaFiscal(notaFiscal);
          default:
            this.cleanup();
            System.out.println("|>> Selecione uma opção válida\n");
            break;
        }
      } catch (Error err) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      } catch (ClassNotFoundException e) {
        System.out.println("parece que algo de errado aconteceu, tente novamente");
      }
    }
  }
}
