package org.nfe.abstracts;

import java.util.Scanner;

public abstract class Entity {

  //  TODO improve Type Signature to avoid force casting of returned value
  protected Object inputWithoutException(Scanner input, String message, InputTypes type) {
    while (true) {
      try {
        System.out.println(message);

        switch (type) {
          case INT:
            return input.nextInt();
          case DOUBLE:
            return input.nextDouble();
          case STRING:
            return input.next();
          default:
            // should never occur
            break;
        }
      } catch (Exception err) {
        System.out.println("Parece que algo de errado aconteceu, tente novamente");
        input.next();
      }
    }
  }
}
