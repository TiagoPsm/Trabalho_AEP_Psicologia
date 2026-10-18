package br.com.psicomatch.Modelos;

import java.util.ArrayList;

public class Equipe {
    private ArrayList<Profissional> profissionals;
    private Clinica clinica;

    public Equipe(Clinica clinica) {
        this.clinica = clinica;
        this.profissionals = new ArrayList<>();
    }

    public void setProfissionals(Profissional profissional) {
        this.profissionals.add(profissional);
    }
}
