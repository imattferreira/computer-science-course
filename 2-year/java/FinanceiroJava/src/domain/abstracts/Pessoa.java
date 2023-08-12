package domain.abstracts;

import domain.implementations.Endereco;
import domain.implementations.Telefone;
import domain.interfaces.InterfaceCadastro;

public class Pessoa implements InterfaceCadastro {
    private int id;
    private String nome;
    private Endereco endereco;
    private Telefone telefone;
    private String email;

    public Pessoa() {
        this.setId(0);
        this.setNome("");
        this.setEndereco(new Endereco());
        this.setTelefone(new Telefone());
        this.setEmail("");
    }

    public Pessoa(int id, String nome, Endereco endereco, Telefone telefone, String email) {
        this.setId(id);
        this.setNome(nome);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
        this.setEmail(email);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return this.telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void entrar() {
        System.out.println("ID:");
        int id = leia.nextInt();
        this.setId(id);

        System.out.println("Nome:");
        String nome = leia.next();
        this.setNome(nome);

        System.out.println("Endereco:");
        Endereco endereco = new Endereco();
        endereco.entrar();
        this.setEndereco(endereco);

        System.out.println("Telefone:");
        Telefone telefone = new Telefone();
        telefone.entrar();
        this.setTelefone(telefone);

        System.out.println("Email:");
        String email = leia.next();
        this.setEmail(email);

    }

    @Override
    public void imprimir() {
        System.out.println("---------------------");
        System.out.println("| Pessoa");
        System.out.println("|> ID..............." + this.getId());
        System.out.println("|> Nome............." + this.getNome());
        System.out.println("|> Email............" + this.getEmail());
        System.out.println("---------------------");

        this.getEndereco().imprimir();
        this.getTelefone().imprimir();
    }
}