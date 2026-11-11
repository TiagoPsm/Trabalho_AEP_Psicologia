package br.com.psicomatch.model;

public class Profissional extends Pessoa {
    private String email;
    private double valorConsulta;

    public Profissional(String cpf, String nome, String telefone, String email, double valorConsulta) {
        super(cpf, nome, telefone);
        this.email = email;
        this.valorConsulta = valorConsulta;
    }
    public String getEmail() { return email; }
    public double getValorConsulta() { return valorConsulta; }
    @Override public String apresentar() { return "Profissional: " + getNome() + " | CPF: " + getCpf(); }
}
