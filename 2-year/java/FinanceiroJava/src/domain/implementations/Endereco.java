package domain.implementations;

import domain.interfaces.InterfaceCadastro;

public class Endereco implements InterfaceCadastro {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private int cep;

    public Endereco() {
        this.setLogradouro("");
        this.setNumero("");
        this.setComplemento("");
        this.setBairro("");
        this.setCidade("");
        this.setEstado("");
        this.setCep(0);
    }

    public Endereco(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            int cep) {
        this.setLogradouro(logradouro);
        this.setNumero(numero);
        this.setComplemento(complemento);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.setCep(cep);
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return this.cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    @Override
    public void entrar() {
        System.out.println("Logradouro:");
        String logradouro = leia.next();
        this.setLogradouro(logradouro);

        System.out.println("Numero:");
        String numero = leia.next();
        this.setNumero(numero);

        System.out.println("Complemento:");
        String complemento = leia.next();
        this.setComplemento(complemento);

        System.out.println("Bairro:");
        String bairro = leia.next();
        this.setBairro(bairro);

        System.out.println("Cidade:");
        String cidade = leia.next();
        this.setCidade(cidade);

        System.out.println("Estado:");
        String estado = leia.next();
        this.setEstado(estado);

        System.out.println("CEP:");
        int cep = leia.nextInt();
        this.setCep(cep);
    }

    @Override
    public void imprimir() {
        System.out.println("---------------------");
        System.out.println("| Endereço");
        System.out.println("|> Logradouro........" + this.getLogradouro());
        System.out.println("|> Numero............" + this.getNumero());
        System.out.println("|> Complemento......." + this.getComplemento());
        System.out.println("|> Bairro............" + this.getBairro());
        System.out.println("|> Cidade............" + this.getCidade());
        System.out.println("|> Estado............" + this.getEstado());
        System.out.println("|> CEP..............." + this.getCep());
        System.out.println("---------------------");
    }
}
