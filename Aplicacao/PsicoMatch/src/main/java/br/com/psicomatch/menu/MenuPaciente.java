package br.com.psicomatch.menu;

import br.com.psicomatch.Entrada;
import br.com.psicomatch.dao.AgendamentoDAO;
import br.com.psicomatch.dao.PacienteDAO;
import br.com.psicomatch.dao.PerfilDAO;
import br.com.psicomatch.dao.ProfissionalDAO;
import br.com.psicomatch.model.Paciente;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MenuPaciente {
    private final String cpf;
    private final PacienteDAO pacientes = new PacienteDAO();
    private final ProfissionalDAO profissionais = new ProfissionalDAO();
    private final AgendamentoDAO agendamentos = new AgendamentoDAO();
    private final PerfilDAO perfis = new PerfilDAO();

    public MenuPaciente(String cpf) {
        this.cpf = cpf;
    }

    public void exibir() throws Exception {
        int opcao;

        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println("             MENU PACIENTE");
            System.out.println("====================================");
            System.out.println("1 - Consultar meu cadastro");
            System.out.println("2 - Atualizar meu cadastro");
            System.out.println("3 - Informar minhas preferências");
            System.out.println("4 - Ver profissionais cadastrados");
            System.out.println("5 - Receber recomendações");
            System.out.println("6 - Buscar profissionais com filtros");
            System.out.println("7 - Agendar consulta");
            System.out.println("8 - Ver meus agendamentos");
            System.out.println("9 - Cancelar meu agendamento");
            System.out.println("10 - Remarcar meu agendamento");
            System.out.println("0 - Voltar ao início");
            System.out.println("------------------------------------");

            opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> consultarCadastro();
                case 2 -> atualizarCadastro();
                case 3 -> informarPreferencias();
                case 4 -> profissionais.listar().forEach(p -> System.out.println(p.apresentar()));
                case 5 -> recomendarProfissionais();
                case 6 -> buscarProfissionais();
                case 7 -> agendarConsulta();
                case 8 -> agendamentos.listarPorPaciente(cpf);
                case 9 -> cancelarAgendamento();
                case 10 -> remarcarAgendamento();
                case 0 -> System.out.println("Voltando ao menu inicial.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void consultarCadastro() throws Exception {
        Paciente paciente = pacientes.buscar(cpf);
        System.out.println(paciente.apresentar());
        System.out.println("Telefone: " + paciente.getTelefone());
        System.out.println("Endereço: " + paciente.getEndereco());
    }

    private void atualizarCadastro() throws Exception {
        Paciente paciente = new Paciente(
            cpf,
            Entrada.ler("Novo nome: "),
            Entrada.ler("Novo telefone: "),
            Entrada.ler("Novo endereço: "),
            ""
        );

        pacientes.atualizar(paciente);
        System.out.println("Cadastro atualizado.");
    }

    private void informarPreferencias() throws Exception {
        String interesses = Entrada.ler("Informe seus interesses separados por vírgula: ");
        pacientes.salvarPreferencias(cpf, interesses);
        System.out.println("Preferências salvas.");
    }

    private void recomendarProfissionais() throws Exception {
        List<String> recomendacoes = pacientes.recomendar(cpf);

        if (recomendacoes.isEmpty()) {
            System.out.println("Nenhuma recomendação encontrada. Informe suas preferências e aguarde profissionais cadastrarem seus perfis.");
            return;
        }

        recomendacoes.forEach(System.out::println);
    }

    private void buscarProfissionais() throws Exception {
        String abordagem = Entrada.ler("Abordagem (Enter para todas): ");
        String modalidade = Entrada.ler("Modalidade (Enter para todas): ");
        String especialidade = Entrada.ler("Especialidade (Enter para todas): ");
        List<String> resultado = profissionais.buscarPorFiltro(abordagem, modalidade, especialidade);

        if (resultado.isEmpty()) {
            System.out.println("Nenhum profissional encontrado.");
            return;
        }

        resultado.forEach(System.out::println);
    }

    private void agendarConsulta() throws Exception {
        LocalDate data = LocalDate.parse(Entrada.ler("Data (AAAA-MM-DD): "));
        LocalTime horario = LocalTime.parse(Entrada.ler("Horário (HH:MM): "));
        String cnpj = Entrada.ler("CNPJ da clínica: ");
        String profissionalCpf = Entrada.ler("CPF do profissional: ");

        agendamentos.agendar(data, horario, cnpj, profissionalCpf, cpf);
        System.out.println("Consulta agendada.");
    }

    private void cancelarAgendamento() throws Exception {
        int id = Entrada.lerInt("Número do agendamento: ");
        agendamentos.cancelarDoPaciente(id, cpf);
        System.out.println("Agendamento cancelado.");
    }

    private void remarcarAgendamento() throws Exception {
        int id = Entrada.lerInt("Número do agendamento: ");
        LocalDate data = LocalDate.parse(Entrada.ler("Nova data (AAAA-MM-DD): "));
        LocalTime horario = LocalTime.parse(Entrada.ler("Novo horário (HH:MM): "));

        agendamentos.remarcarDoPaciente(id, cpf, data, horario);
        System.out.println("Agendamento remarcado.");
    }
}
