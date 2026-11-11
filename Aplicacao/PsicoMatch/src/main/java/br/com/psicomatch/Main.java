package br.com.psicomatch;

import br.com.psicomatch.config.Conexao;
import br.com.psicomatch.dao.ClinicaDAO;
import br.com.psicomatch.dao.PacienteDAO;
import br.com.psicomatch.dao.ProfissionalDAO;
import br.com.psicomatch.menu.MenuClinica;
import br.com.psicomatch.menu.MenuPaciente;
import br.com.psicomatch.menu.MenuProfissional;
import br.com.psicomatch.model.Clinica;
import br.com.psicomatch.model.Paciente;
import br.com.psicomatch.model.Profissional;

public class Main {
    private static final PacienteDAO PACIENTES = new PacienteDAO();
    private static final ProfissionalDAO PROFISSIONAIS = new ProfissionalDAO();
    private static final ClinicaDAO CLINICAS = new ClinicaDAO();

    public static void main(String[] args) {
        try (var conexao = Conexao.conectar()) {
            System.out.println("Conexão com o banco realizada.");
        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao banco: " + e.getMessage());
            return;
        }

        boolean continuar = true;

        while (continuar) {
            exibirMenuInicial();
            int opcao = Entrada.lerInt("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> entrarPaciente();
                    case 2 -> entrarProfissional();
                    case 3 -> entrarClinica();
                    case 0 -> {
                        continuar = false;
                        System.out.println("Sistema encerrado.");
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Não foi possível concluir a operação: " + e.getMessage());
            }
        }
    }

    private static void exibirMenuInicial() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("             PSICOMATCH");
        System.out.println("====================================");
        System.out.println("1 - Entrar como paciente");
        System.out.println("2 - Entrar como profissional");
        System.out.println("3 - Entrar como clínica");
        System.out.println("0 - Sair");
        System.out.println("------------------------------------");
    }

    private static void entrarPaciente() throws Exception {
        String cpf = Entrada.ler("Informe seu CPF: ");
        Paciente paciente = PACIENTES.buscar(cpf);

        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            String cadastrar = Entrada.ler("Deseja realizar o cadastro? (S/N): ");

            if (!cadastrar.equalsIgnoreCase("S")) {
                return;
            }

            paciente = new Paciente(
                cpf,
                Entrada.ler("Nome: "),
                Entrada.ler("Telefone: "),
                Entrada.ler("Endereço: "),
                ""
            );

            PACIENTES.inserir(paciente);
            System.out.println("Cadastro realizado com sucesso.");
        }

        System.out.println("Bem-vindo(a), " + paciente.getNome() + "!");
        new MenuPaciente(cpf).exibir();
    }

    private static void entrarProfissional() throws Exception {
        String cpf = Entrada.ler("Informe seu CPF: ");
        Profissional profissional = PROFISSIONAIS.buscar(cpf);

        if (profissional == null) {
            System.out.println("Profissional não encontrado.");
            String cadastrar = Entrada.ler("Deseja realizar o cadastro? (S/N): ");

            if (!cadastrar.equalsIgnoreCase("S")) {
                return;
            }

            profissional = new Profissional(
                cpf,
                Entrada.ler("Nome: "),
                Entrada.ler("Telefone: "),
                Entrada.ler("E-mail: "),
                Entrada.lerDouble("Valor da consulta: ")
            );

            PROFISSIONAIS.inserir(profissional);
            System.out.println("Cadastro realizado com sucesso.");
        }

        System.out.println("Bem-vindo(a), " + profissional.getNome() + "!");
        new MenuProfissional(cpf).exibir();
    }

    private static void entrarClinica() throws Exception {
        String cnpj = Entrada.ler("Informe o CNPJ: ");
        Clinica clinica = CLINICAS.buscar(cnpj);

        if (clinica == null) {
            System.out.println("Clínica não encontrada.");
            String cadastrar = Entrada.ler("Deseja realizar o cadastro? (S/N): ");

            if (!cadastrar.equalsIgnoreCase("S")) {
                return;
            }

            clinica = new Clinica(
                cnpj,
                Entrada.ler("Nome da clínica: "),
                Entrada.ler("Rua: "),
                Entrada.ler("Bairro: "),
                Entrada.ler("Complemento: "),
                Entrada.ler("Número: ")
            );

            CLINICAS.inserir(clinica);
            System.out.println("Cadastro realizado com sucesso.");
        }

        System.out.println("Bem-vindo(a), " + clinica.getNome() + "!");
        new MenuClinica(cnpj).exibir();
    }
}
