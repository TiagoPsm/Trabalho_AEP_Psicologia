package br.com.psicomatch.Modelos;

import java.time.LocalDateTime;

public class Agendamento {
    private String data;
    private String hora;
    private Profissional profissional;
    private Paciente paciente;
    private Clinica clinica;

    public Agendamento(String data, String hora, Profissional profissional, Paciente paciente, Clinica clinica) {
        this.data = data;
        this.hora = hora;
        this.profissional = profissional;
        this.paciente = paciente;
        this.clinica = clinica;

        paciente.setAgendamentos(this);
        profissional.setAgendamentos(this);
        clinica.setAgendamentos(this);
    }

    public String getData() {
        return "Data: " + data;
    }

    public String getHora() {
        return "Hora: " + hora;
    }

    public String getProfissional() {
        return "Profissional: " + profissional.getNome();
    }

    public String getPaciente() {
        return "Paciente: " + paciente.getNome();
    }

    public String getClinica() {
        return  "Clinica: " + clinica.getNome();
    }
}
