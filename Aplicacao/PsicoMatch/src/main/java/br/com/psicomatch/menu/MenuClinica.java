package br.com.psicomatch.menu;

import br.com.psicomatch.Entrada;
import br.com.psicomatch.dao.ClinicaDAO;
import br.com.psicomatch.dao.AgendamentoDAO;
import br.com.psicomatch.model.Clinica;

public class MenuClinica {
    private final String cnpj;
    private final ClinicaDAO clinicas = new ClinicaDAO();
    private final AgendamentoDAO agendamentos = new AgendamentoDAO();

    public MenuClinica(String cnpj) {
        this.cnpj = cnpj;
    }

    public void exibir() throws Exception {
        int opcao;

        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println("              MENU CLÍNICA");
            System.out.println("====================================");
            System.out.println("1 - Consultar meus dados");
            System.out.println("2 - Atualizar meus dados");
            System.out.println("3 - Vincular profissional à equipe");
            System.out.println("4 - Consultar minha equipe");
            System.out.println("5 - Ver agendamentos da clínica");
            System.out.println("0 - Voltar ao início");
            System.out.println("------------------------------------");

            opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> consultarDados();
                case 2 -> atualizarDados();
                case 3 -> vincularProfissional();
                case 4 -> clinicas.listarEquipe(cnpj);
                case 5 -> agendamentos.listarPorClinica(cnpj);
                case 0 -> System.out.println("Voltando ao menu inicial.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void consultarDados() throws Exception {
        Clinica clinica = clinicas.buscar(cnpj);
        System.out.println("CNPJ: " + clinica.getCnpj());
        System.out.println("Nome: " + clinica.getNome());
        System.out.println("Endereço: " + clinica.getRua() + ", " + clinica.getNumero());
        System.out.println("Bairro: " + clinica.getBairro());
        System.out.println("Complemento: " + clinica.getComplemento());
    }

    private void atualizarDados() throws Exception {
        Clinica clinica = new Clinica(
            cnpj,
            Entrada.ler("Novo nome: "),
            Entrada.ler("Rua: "),
            Entrada.ler("Bairro: "),
            Entrada.ler("Complemento: "),
            Entrada.ler("Número: ")
        );

        clinicas.atualizar(clinica);
        System.out.println("Dados da clínica atualizados.");
    }

    private void vincularProfissional() throws Exception {
        String cpf = Entrada.ler("CPF do profissional cadastrado: ");
        clinicas.vincularProfissional(cnpj, cpf);
        System.out.println("Profissional vinculado à equipe.");
    }
}
