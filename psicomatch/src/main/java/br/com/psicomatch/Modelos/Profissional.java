package br.com.psicomatch.Modelos;

public class Profissional extends Pessoa {
    private double valorConsulta;
    private Clinica clinica;

    public Profissional(String nome, String cpf, String telefone, double valorConsulta) {
        super(nome, cpf, telefone);
        this.valorConsulta = valorConsulta;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Clinica getClinica() {
        return clinica;
    }
}
