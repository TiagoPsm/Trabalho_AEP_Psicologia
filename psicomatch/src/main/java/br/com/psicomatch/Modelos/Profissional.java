package br.com.psicomatch.Modelos;

public class Profissional extends Pessoa {
    private double valorConsulta;

    public Profissional(String nome, String cpf, String telefone, double valorConsulta) {
        super(nome, cpf, telefone);
        this.valorConsulta = valorConsulta;
    }


}
