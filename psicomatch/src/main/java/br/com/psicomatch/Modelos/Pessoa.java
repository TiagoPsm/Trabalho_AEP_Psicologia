package br.com.psicomatch.Modelos;

import java.util.ArrayList;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private ArrayList<Agendamento> agendamentos;
    private ArrayList<Interesse> interesses;

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.agendamentos = new ArrayList<Agendamento>();
        this.interesses = new ArrayList<Interesse>();
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

    public void setInteresses(Interesse interesse) {
        this.interesses.add(interesse);
    }

    public ArrayList<Interesse> getInteresses() {
        return interesses;
    }
}
