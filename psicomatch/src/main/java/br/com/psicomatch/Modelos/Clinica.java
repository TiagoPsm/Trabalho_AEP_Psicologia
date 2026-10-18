package br.com.psicomatch.Modelos;

import java.util.ArrayList;

public class Clinica {
    private String bairro;
    private String rua;
    private String numero;
    private String cnpj;
    private String complemento;
    private ArrayList<Agendamento> agendamentos;

    public Clinica(String bairro, String rua, String numero, String cnpj) {
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cnpj = cnpj;
    }

    //public void setAgendamentos(Agendamento agendamento) {
        //this.agendamentos.add(agendamento);
    //}
}
