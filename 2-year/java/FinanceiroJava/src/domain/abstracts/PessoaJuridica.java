package domain.abstracts;

public class PessoaJuridica extends Pessoa {
    private String cnpj;
    private String inscricao;
    private String contato;

    public PessoaJuridica() {
        this.setCnpj("");
        this.setInscricao("");
        this.setContato("");
    }

    public PessoaJuridica(String cnpj, String inscricao, String contato) {
        this.setCnpj(cnpj);
        this.setInscricao(inscricao);
        this.setContato(contato);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public void entrar() {
        super.entrar();

        System.out.println("CNPJ: ");
        String cnpj = leia.next();
        this.setCnpj(cnpj);

        System.out.println("Inscricao: ");
        String inscricao = leia.next();
        this.setInscricao(inscricao);

        System.out.println("Contato: ");
        String contato = leia.next();
        this.setContato(contato);
    }

    @Override
    public void imprimir() {
        super.imprimir();

        System.out.println("---------------------");
        System.out.println("| Pessoa Jurídica");
        System.out.println("|> CNPJ.............." + this.getCnpj());
        System.out.println("|> Inscricao........." + this.getInscricao());
        System.out.println("|> Contato..........." + this.getContato());
        System.out.println("---------------------");
    }
}
