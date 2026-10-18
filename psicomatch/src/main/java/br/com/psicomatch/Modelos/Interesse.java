package br.com.psicomatch.Modelos;

public class Interesse {
    private String nome;

    public Interesse(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
