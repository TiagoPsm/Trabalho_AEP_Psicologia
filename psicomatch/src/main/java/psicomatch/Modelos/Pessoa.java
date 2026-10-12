package psicomatch.Modelos;

public class Pessoa {
    private String nome;
    private String email;
    private String endereco;
    private int telefone;
    private String cpf;

    public Pessoa(String nome, int telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
}
