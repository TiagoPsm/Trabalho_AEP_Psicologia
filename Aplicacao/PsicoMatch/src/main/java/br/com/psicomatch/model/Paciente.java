package br.com.psicomatch.model;

public class Paciente extends Pessoa {
    private String endereco;
    private String profissionalCpf;

    public Paciente(String cpf, String nome, String telefone, String endereco, String profissionalCpf) {
        super(cpf, nome, telefone);
        this.endereco = endereco;
        this.profissionalCpf = profissionalCpf;
    }
    public String getEndereco() { return endereco; }
    public String getProfissionalCpf() { return profissionalCpf; }
    @Override public String apresentar() { return "Paciente: " + getNome() + " | CPF: " + getCpf(); }
}
