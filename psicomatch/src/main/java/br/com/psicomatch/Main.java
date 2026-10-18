package br.com.psicomatch;

import br.com.psicomatch.Modelos.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {

        Scanner leitor = new Scanner(System.in);

        Paciente paciente01 = new Paciente("Murilo", "124.462.453.34", "(44) 3423-3242");
        Profissional carlos = new Profissional("Carlos", "234.243.423.65", "(44) 4534-4343", 180.00);
        Profissional profissional02 = new Profissional("Maria", "111.232.543.65", "(44) 4534-0003", 250.00);
        Profissional profissional03 = new Profissional("Kim", "000.232.553.65", "(44) 1123-0003", 160.00);
        Clinica clinica01 = new Clinica("Clinica Rosas", "Jardim sol", "Palmeiras do ceu", "456", "192.32.432");

        Agendamento agendamento01 = new Agendamento("15/08/2026", "15:50", carlos, paciente01, clinica01);
        Agendamento agendamento02 = new Agendamento("29/08/2026", "19:50", profissional02, paciente01, clinica01);

        clinica01.adicionarProfissional(carlos);
        clinica01.adicionarProfissional(profissional02);

        System.out.println("\nAgendamentos: ");
        for (Agendamento agendamento : paciente01.getAgendamentos()) {
            System.out.println("------------------------------------");
            System.out.println(agendamento.getData());
            System.out.println(agendamento.getHora());
            System.out.println(agendamento.getClinica());
            System.out.println(agendamento.getProfissional());
            System.out.println(agendamento.getPaciente());
        }

        Interesse animes = new Interesse("Animes");
        Interesse culinaria = new Interesse("Culinária");
        Interesse viagem = new Interesse("Viagem");

        carlos.setInteresses(animes);
        carlos.setInteresses(viagem);
        paciente01.setInteresses(animes);
        profissional02.setInteresses(culinaria);
        profissional03.setInteresses(animes);
        profissional02.setInteresses(animes);

        System.out.println("\nInteresses: ");
        for (Interesse interesse : paciente01.getInteresses()) {
            System.out.println("------------------------------------");
            System.out.println(interesse.getNome());
        }


        //Listando profissionais com interesesses compativeis
        System.out.println("------------------------------------");
        ArrayList<Profissional> profissionals = new ArrayList<>();

        profissionals.add(profissional03);
        profissionals.add(profissional02);
        profissionals.add(carlos);

        System.out.printf("Profissionais compativeis com o paciente %s\n\n", paciente01.getNome());
        for (Profissional profissional : profissionals) {
             for (Interesse interesse : paciente01.getInteresses()) {
                 if (profissional.getInteresses().contains(interesse)) {
                     System.out.printf("Profissional: %s | Interesses: %s\n", profissional.getNome(), profissional.getInteresses().toString());
                     System.out.println("------------------------------------");
                     break;
                 }
             }
        }
    }
}
