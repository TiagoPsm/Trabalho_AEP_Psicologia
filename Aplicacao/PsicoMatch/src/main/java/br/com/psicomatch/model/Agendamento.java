package br.com.psicomatch.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
    private int id;
    private LocalDate data;
    private LocalTime horario;
    private String clinicaCnpj;
    private String profissionalCpf;
    private String pacienteCpf;
    private String status;

    public Agendamento(int id, LocalDate data, LocalTime horario, String clinicaCnpj, String profissionalCpf, String pacienteCpf, String status) {
        this.id=id; this.data=data; this.horario=horario; this.clinicaCnpj=clinicaCnpj; this.profissionalCpf=profissionalCpf; this.pacienteCpf=pacienteCpf; this.status=status;
    }
    public int getId() { return id; }
    public LocalDate getData() { return data; }
    public LocalTime getHorario() { return horario; }
    public String getClinicaCnpj() { return clinicaCnpj; }
    public String getProfissionalCpf() { return profissionalCpf; }
    public String getPacienteCpf() { return pacienteCpf; }
    public String getStatus() { return status; }
}
