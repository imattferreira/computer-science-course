package domain.interfaces;

import java.util.Scanner;

public interface InterfaceCadastro {
    Scanner leia = new Scanner(System.in);

    public void entrar();

    public void imprimir();
}