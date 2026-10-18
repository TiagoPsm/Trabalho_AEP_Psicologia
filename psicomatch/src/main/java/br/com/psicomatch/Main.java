package br.com.psicomatch;

import br.com.psicomatch.Modelos.Agendamento;
import br.com.psicomatch.Modelos.Clinica;
import br.com.psicomatch.Modelos.Paciente;
import br.com.psicomatch.Modelos.Profissional;

import java.util.Scanner;

public class Main {
    static void main() {

        Scanner leitor = new Scanner(System.in);

        Paciente paciente01 = new Paciente("Murilo", "124.462.453.34", "(44) 3423-3242");
        Profissional profissional01 = new Profissional("Carlos", "234.243.423.65", "(44) 4534-4343", 180.00);
        Profissional profissional02 = new Profissional("Maria", "111.232.543.65", "(44) 4534-0003", 250.00);
        Clinica clinica01 = new Clinica("Jardim sol", "Palmeiras do ceu", "456", "192.32.432");

        Agendamento agendamento01 = new Agendamento("15/08/2026", "15:50", profissional01, paciente01, clinica01);
        Agendamento agendamento02 = new Agendamento("29/08/2026", "19:50", profissional02, paciente01, clinica01);

        for (Agendamento agendamento : paciente01.getAgendamentos()) {
            System.out.println("------------------------------------");
            System.out.println(agendamento.getData());
            System.out.println(agendamento.getHora());
            System.out.println(agendamento.getProfissional());
            System.out.println(agendamento.getPaciente());
        }
    }
}
