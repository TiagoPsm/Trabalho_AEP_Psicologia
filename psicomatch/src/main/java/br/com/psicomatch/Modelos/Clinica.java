package br.com.psicomatch.Modelos;

import java.util.ArrayList;

public class Clinica {
    private String nome;
    private String bairro;
    private String rua;
    private String numero;
    private String cnpj;
    private String complemento;
    private ArrayList<Agendamento> agendamentos;
    private Equipe equipe;

    public Clinica(String nome, String bairro, String rua, String numero, String cnpj) {
        this.nome = nome;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cnpj = cnpj;
        this.agendamentos = new ArrayList<Agendamento>();
        this.equipe = new Equipe(this);
    }

    public void setAgendamentos(Agendamento agendamento) {
        this.agendamentos.add(agendamento);
    }

    public String getNome() {
        return nome;
    }

    public void adicionarProfissional(Profissional profissional) {
        this.equipe.setProfissionals(profissional);
        profissional.setClinica(this);
    }
}
