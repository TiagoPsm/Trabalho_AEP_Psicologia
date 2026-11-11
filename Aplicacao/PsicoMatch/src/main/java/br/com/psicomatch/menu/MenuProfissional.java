package br.com.psicomatch.menu;

import br.com.psicomatch.Entrada;
import br.com.psicomatch.dao.AgendamentoDAO;
import br.com.psicomatch.dao.PainelDAO;
import br.com.psicomatch.dao.PerfilDAO;
import br.com.psicomatch.dao.ProfissionalDAO;
import br.com.psicomatch.model.Profissional;

public class MenuProfissional {
    private final String cpf;
    private final ProfissionalDAO profissionais = new ProfissionalDAO();
    private final PerfilDAO perfis = new PerfilDAO();
    private final AgendamentoDAO agendamentos = new AgendamentoDAO();
    private final PainelDAO painel = new PainelDAO();

    public MenuProfissional(String cpf) {
        this.cpf = cpf;
    }

    public void exibir() throws Exception {
        int opcao;

        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println("           MENU PROFISSIONAL");
            System.out.println("====================================");
            System.out.println("1 - Consultar meu cadastro");
            System.out.println("2 - Atualizar meu cadastro");
            System.out.println("3 - Cadastrar ou atualizar meu perfil público");
            System.out.println("4 - Excluir meu perfil público");
            System.out.println("5 - Ver meus agendamentos");
            System.out.println("6 - Marcar consulta como realizada");
            System.out.println("7 - Cancelar consulta");
            System.out.println("8 - Ver meu painel de gestão");
            System.out.println("0 - Voltar ao início");
            System.out.println("------------------------------------");

            opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> consultarCadastro();
                case 2 -> atualizarCadastro();
                case 3 -> salvarPerfil();
                case 4 -> excluirPerfil();
                case 5 -> agendamentos.listarPorProfissional(cpf);
                case 6 -> alterarStatus("REALIZADO");
                case 7 -> alterarStatus("CANCELADO");
                case 8 -> painel.exibir(cpf);
                case 0 -> System.out.println("Voltando ao menu inicial.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void consultarCadastro() throws Exception {
        Profissional profissional = profissionais.buscar(cpf);
        System.out.println(profissional.apresentar());
        System.out.println("Telefone: " + profissional.getTelefone());
        System.out.println("E-mail: " + profissional.getEmail());
        System.out.println("Valor da consulta: R$ " + profissional.getValorConsulta());
    }

    private void atualizarCadastro() throws Exception {
        Profissional profissional = new Profissional(
            cpf,
            Entrada.ler("Novo nome: "),
            Entrada.ler("Novo telefone: "),
            Entrada.ler("Novo e-mail: "),
            Entrada.lerDouble("Novo valor da consulta: ")
        );

        profissionais.atualizar(profissional);
        System.out.println("Cadastro atualizado.");
    }

    private void salvarPerfil() throws Exception {
        String formacao = Entrada.ler("Formação: ");
        String especialidades = Entrada.ler("Especialidades: ");
        String abordagem = Entrada.ler("Abordagem psicológica: ");
        String modalidade = Entrada.ler("Modalidade (online/presencial): ");
        String descricao = Entrada.ler("Descrição e gostos pessoais: ");
        String interesses = Entrada.ler("Interesses separados por vírgula: ");

        perfis.salvar(cpf, formacao, especialidades, abordagem, modalidade, descricao, interesses);
        System.out.println("Perfil salvo.");
    }

    private void excluirPerfil() throws Exception {
        perfis.excluir(cpf);
        System.out.println("Perfil público excluído.");
    }

    private void alterarStatus(String status) throws Exception {
        int id = Entrada.lerInt("Número do agendamento: ");
        agendamentos.alterarStatusDoProfissional(id, cpf, status);
        System.out.println("Agendamento atualizado.");
    }
}
