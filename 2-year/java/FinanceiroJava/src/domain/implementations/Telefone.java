package domain.implementations;

import domain.interfaces.InterfaceCadastro;

public class Telefone implements InterfaceCadastro {
    private int ddd;
    private long numero;

    public Telefone() {
        this.setDdd(0);
        this.setNumero(0);
    }

    public Telefone(int ddd, long numero) {
        this.setDdd(ddd);
        this.setNumero(numero);
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    @Override
    public void entrar() {
        System.out.println("DDD:");
        int ddd = leia.nextInt();
        this.setDdd(ddd);

        System.out.println("Numero:");
        long numero = leia.nextLong();
        this.setNumero(numero);
    }

    @Override
    public void imprimir() {
        System.out.println("---------------------");
        System.out.println("| Telefone");
        System.out.println("|> DDD..............." + this.getDdd());
        System.out.println("|> Numero............" + this.getNumero());
        System.out.println("---------------------");
    }
}
