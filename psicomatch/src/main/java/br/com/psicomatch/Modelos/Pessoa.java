package br.com.psicomatch.Modelos;

import java.util.ArrayList;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private ArrayList<Agendamento> agendamentos;

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.agendamentos = new ArrayList<Agendamento>();
    }

    public void setAgendamentos(Agendamento agendamento) {
        this.agendamentos.add(agendamento);
    }

    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public String getNome() {
        return nome;
    }
}
