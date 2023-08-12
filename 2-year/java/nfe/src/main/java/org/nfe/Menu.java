/**
 * TODO
 * - finish create Nfe methods
 * - finish create Product methods
 * - finish create `populate` method of `NfesRepository`
 * - finish Menu
 */
package org.nfe;

import org.nfe.errors.ValidationException;
import org.nfe.implementations.Nfe;
import org.nfe.repositories.NfesRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
  NfesRepository nfesRepository = new NfesRepository();
  Scanner input = new Scanner(System.in);

  public Menu() {
    this.nfesRepository.populate(50);
  }
  public void execute() {
    int selected = this.showStartupMenu();

    switch (selected) {
      case 1:
        Nfe newNfe = new Nfe();

        newNfe.create(this.input);

        this.nfesRepository.create(newNfe);
        break;
      case 2:
        this.showUpdateNfeMenu();
        break;
      case 3:
        this.showFindNfeMenu();
        break;
      case 4:
        while (true) {
          try {
            System.out.println("Digite o número da NF-e");
            int number = this.input.nextInt();

            this.nfesRepository.exclude(number);
            break;
          } catch (InputMismatchException err) {
            System.out.println("Parece que algo de errado aconteceu, tente novamente.");
            this.input.next();
          }
        }
        break;
      case 5:
        int number, qty = 0;

        while (true) {
          try {
            System.out.println("Digite o número da NF-e inicial");
            number = this.input.nextInt();

            if (number < 1) {
              throw new ValidationException("Número da NF-e inválido");
            }

            System.out.println("Digite a quantidade de NF-es que deseja visualizar");
            qty = this.input.nextInt();

            if (qty < 1) {
              throw new ValidationException("Quantidade de NF-es inválida");
            }
            break;
          } catch (InputMismatchException err) {
            System.out.println("Parece que algo de errado aconteceu, tente novamente.");
            this.input.next();
          } catch (ValidationException err) {
            System.out.println(err.getMessage() + ", tente novamente.");
          }
        }

        ArrayList<Nfe> result = this.nfesRepository.findRangeByNumber(number, qty);

        result.forEach(Nfe::output);
        break;
      default:
        System.exit(0);
        break;
    }
  }

  private int showStartupMenu() {
    while (true) {
      System.out.println("1. criar nova NF-e");
      System.out.println("2. alterar NF-e existente");
      System.out.println("3. exibir informações da NF-e");
      System.out.println("4. cancelar NF-e");
      System.out.println("5. mostrar NF-es por faixa de números");
      System.out.println("6. sair");

      try {
        int selected = this.input.nextInt();

        if (selected <= 0 || selected > 6) {
          System.out.println("Parece que você digitou uma opção inválida, tente novamente");
          continue;
        }

        System.out.println(selected);
        return selected;
      } catch (InputMismatchException err) {
        System.out.println("Parece que você digitou uma opção inválida, tente novamente");
        this.input.next();
      }
    }
  }

    private int showUpdateNfeMenu() {
      while (true) {
        System.out.println("1. alterar destinatário/remetente");
        System.out.println("2. alterar fatura");
        System.out.println("3. alterar cálculo do imposto");
        System.out.println("4. alterar transportadora");
        System.out.println("5. voltar");

        try {
          int selected = this.input.nextInt();

          if (selected <= 0 || selected > 5) {
            System.out.println("Parece que você digitou uma opção inválida, tente novamente");
            continue;
          }

          return selected;
        } catch (InputMismatchException err) {
          System.out.println("Parece que você digitou uma opção inválida, tente novamente");
          this.input.next();
        }
      }
    }

    private int showFindNfeMenu() {
      while (true) {
        System.out.println("1. buscar pelo número da NF-e");
        System.out.println("2. buscar pela razão social do cliente");
        System.out.println("3. buscar pelo CNPJ/CPF do cliente");
        System.out.println("4. buscar pelo valor total da NF-e");
        System.out.println("5. voltar");

        try {
          int selected = this.input.nextInt();

          if (selected <= 0 || selected > 5) {
            System.out.println("Parece que você digitou uma opção inválida, tente novamente");
            continue;
          }

          return selected;
        } catch (InputMismatchException err) {
          System.out.println("Parece que você digitou uma opção inválida, tente novamente");
          this.input.next();
        }
      }
    }
}
