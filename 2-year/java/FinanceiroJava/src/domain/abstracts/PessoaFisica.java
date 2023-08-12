package domain.abstracts;

public class PessoaFisica extends Pessoa {
    private String cpf;
    private String rg;
    private String emissor;

    public PessoaFisica() {
        this.setCpf("");
        this.setRg("");
        this.setEmissor("");
    }

    public PessoaFisica(String cpf, String rg, String emissor) {
        this.setCpf(cpf);
        this.setRg(rg);
        this.setEmissor(emissor);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    @Override
    public void entrar() {
        super.entrar();

        System.out.println("CPF: ");
        String cpf = leia.next();
        this.setCpf(cpf);

        System.out.println("RG: ");
        String rg = leia.next();
        this.setRg(rg);

        System.out.println("Emissor: ");
        String emissor = leia.next();
        this.setEmissor(emissor);
    }

    @Override
    public void imprimir() {
        super.imprimir();

        System.out.println("---------------------");
        System.out.println("| Pessoa Física");
        System.out.println("|> CPF..............." + this.getCpf());
        System.out.println("|> RG................" + this.getRg());
        System.out.println("|> Emissor..........." + this.getEmissor());
        System.out.println("---------------------");
    }
}
