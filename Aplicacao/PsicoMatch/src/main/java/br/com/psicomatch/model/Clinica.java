package br.com.psicomatch.model;

import java.util.ArrayList;
import java.util.List;

public class Clinica {
    private String cnpj;
    private String nome;
    private String rua;
    private String bairro;
    private String complemento;
    private String numero;
    private final List<Profissional> equipe = new ArrayList<>();

    public Clinica(String cnpj, String nome, String rua, String bairro, String complemento, String numero) {
        this.cnpj = cnpj; this.nome = nome; this.rua = rua; this.bairro = bairro; this.complemento = complemento; this.numero = numero;
    }
    public void adicionarProfissional(Profissional profissional) { equipe.add(profissional); }
    public List<Profissional> getEquipe() { return equipe; }
    public String getCnpj() { return cnpj; }
    public String getNome() { return nome; }
    public String getRua() { return rua; }
    public String getBairro() { return bairro; }
    public String getComplemento() { return complemento; }
    public String getNumero() { return numero; }
}
