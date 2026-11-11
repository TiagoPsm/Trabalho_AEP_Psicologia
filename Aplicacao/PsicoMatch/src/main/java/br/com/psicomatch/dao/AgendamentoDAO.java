package br.com.psicomatch.dao;

import br.com.psicomatch.config.Conexao;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDAO {
    public void agendar(LocalDate data,LocalTime hora,String clinica,String profissional,String paciente)throws Exception{
        try(Connection c=Conexao.conectar()){
            if(conflito(c,data,hora,profissional,0))throw new IllegalArgumentException("Este profissional já possui agendamento ativo nesse dia e horário.");
            int id;try(Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COALESCE(MAX(agendamento_id),0)+1 FROM agendamento")){r.next();id=r.getInt(1);}
            try(PreparedStatement s=c.prepareStatement("INSERT INTO agendamento(agendamento_id,agendamento_data,agendamento_horario,clinica_cnpj,profissional_cpf,paciente_cpf) VALUES(?,?,?,?,?,?)")){s.setInt(1,id);s.setDate(2,Date.valueOf(data));s.setTime(3,Time.valueOf(hora));s.setString(4,clinica);s.setString(5,profissional);s.setString(6,paciente);s.executeUpdate();}
            try(PreparedStatement s=c.prepareStatement("INSERT INTO agendamento_status(agendamento_id,status) VALUES(?,'AGENDADO')")){s.setInt(1,id);s.executeUpdate();}
        }
    }
    public void listar()throws Exception{String q="SELECT a.agendamento_id,a.agendamento_data,a.agendamento_horario,c.clinica_nome,p.profissional_nome,pa.paciente_nome,COALESCE(st.status,'AGENDADO') status FROM agendamento a JOIN clinica c ON c.clinica_cnpj=a.clinica_cnpj JOIN profissional p ON p.profissional_cpf=a.profissional_cpf JOIN paciente pa ON pa.paciente_cpf=a.paciente_cpf LEFT JOIN agendamento_status st ON st.agendamento_id=a.agendamento_id ORDER BY a.agendamento_data,a.agendamento_horario";try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery(q)){while(r.next())System.out.println(r.getInt(1)+" | "+r.getDate(2)+" "+r.getTime(3)+" | "+r.getString(4)+" | "+r.getString(5)+" | "+r.getString(6)+" | "+r.getString(7));}}
    public void remarcar(int id,LocalDate data,LocalTime hora)throws Exception{try(Connection c=Conexao.conectar()){String cpf;try(PreparedStatement s=c.prepareStatement("SELECT profissional_cpf FROM agendamento WHERE agendamento_id=?")){s.setInt(1,id);try(ResultSet r=s.executeQuery()){if(!r.next())throw new IllegalArgumentException("Agendamento não encontrado.");cpf=r.getString(1);}}if(conflito(c,data,hora,cpf,id))throw new IllegalArgumentException("Este profissional já possui agendamento ativo nesse dia e horário.");try(PreparedStatement s=c.prepareStatement("UPDATE agendamento SET agendamento_data=?,agendamento_horario=? WHERE agendamento_id=?")){s.setDate(1,Date.valueOf(data));s.setTime(2,Time.valueOf(hora));s.setInt(3,id);s.executeUpdate();}}}
    public void alterarStatus(int id,String status)throws Exception{try(Connection c=Conexao.conectar();PreparedStatement s=c.prepareStatement("INSERT INTO agendamento_status(agendamento_id,status) VALUES(?,?) ON DUPLICATE KEY UPDATE status=VALUES(status)")){s.setInt(1,id);s.setString(2,status);s.executeUpdate();}}
    public void excluir(int id)throws Exception{try(Connection c=Conexao.conectar()){try(PreparedStatement s=c.prepareStatement("DELETE FROM agendamento_status WHERE agendamento_id=?")){s.setInt(1,id);s.executeUpdate();}try(PreparedStatement s=c.prepareStatement("DELETE FROM agendamento WHERE agendamento_id=?")){s.setInt(1,id);s.executeUpdate();}}}
    private boolean conflito(Connection c,LocalDate d,LocalTime h,String cpf,int ignorar)throws Exception{String q="SELECT COUNT(*) FROM agendamento a LEFT JOIN agendamento_status st ON st.agendamento_id=a.agendamento_id WHERE a.profissional_cpf=? AND a.agendamento_data=? AND a.agendamento_horario=? AND a.agendamento_id<>? AND COALESCE(st.status,'AGENDADO')<>'CANCELADO'";try(PreparedStatement s=c.prepareStatement(q)){s.setString(1,cpf);s.setDate(2,Date.valueOf(d));s.setTime(3,Time.valueOf(h));s.setInt(4,ignorar);try(ResultSet r=s.executeQuery()){r.next();return r.getInt(1)>0;}}}

    public void listarPorPaciente(String cpf) throws Exception {
        listarPorResponsavel("a.paciente_cpf", cpf);
    }

    public void listarPorProfissional(String cpf) throws Exception {
        listarPorResponsavel("a.profissional_cpf", cpf);
    }

    public void listarPorClinica(String cnpj) throws Exception {
        listarPorResponsavel("a.clinica_cnpj", cnpj);
    }

    private void listarPorResponsavel(String campo, String identificador) throws Exception {
        String sql = "SELECT a.agendamento_id, a.agendamento_data, a.agendamento_horario, c.clinica_nome, p.profissional_nome, pa.paciente_nome, COALESCE(st.status, 'AGENDADO') status FROM agendamento a JOIN clinica c ON c.clinica_cnpj=a.clinica_cnpj JOIN profissional p ON p.profissional_cpf=a.profissional_cpf JOIN paciente pa ON pa.paciente_cpf=a.paciente_cpf LEFT JOIN agendamento_status st ON st.agendamento_id=a.agendamento_id WHERE " + campo + "=? ORDER BY a.agendamento_data, a.agendamento_horario";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, identificador);

            try (ResultSet rs = ps.executeQuery()) {
                boolean encontrou = false;

                while (rs.next()) {
                    encontrou = true;
                    System.out.println("Código: " + rs.getInt("agendamento_id"));
                    System.out.println("Data: " + rs.getDate("agendamento_data"));
                    System.out.println("Horário: " + rs.getTime("agendamento_horario"));
                    System.out.println("Clínica: " + rs.getString("clinica_nome"));
                    System.out.println("Profissional: " + rs.getString("profissional_nome"));
                    System.out.println("Paciente: " + rs.getString("paciente_nome"));
                    System.out.println("Status: " + rs.getString("status"));
                    System.out.println("------------------------------------");
                }

                if (!encontrou) {
                    System.out.println("Nenhum agendamento encontrado.");
                }
            }
        }
    }

    public void cancelarDoPaciente(int id, String cpf) throws Exception {
        alterarStatusComPermissao(id, "a.paciente_cpf", cpf, "CANCELADO");
    }

    public void remarcarDoPaciente(int id, String cpf, LocalDate data, LocalTime horario) throws Exception {
        validarResponsavel(id, "a.paciente_cpf", cpf);
        remarcar(id, data, horario);
    }

    public void alterarStatusDoProfissional(int id, String cpf, String status) throws Exception {
        alterarStatusComPermissao(id, "a.profissional_cpf", cpf, status);
    }

    private void alterarStatusComPermissao(int id, String campo, String identificador, String status) throws Exception {
        validarResponsavel(id, campo, identificador);
        alterarStatus(id, status);
    }

    private void validarResponsavel(int id, String campo, String identificador) throws Exception {
        String consulta = "SELECT COUNT(*) FROM agendamento a WHERE a.agendamento_id=? AND " + campo + "=?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setInt(1, id);
            ps.setString(2, identificador);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();

                if (rs.getInt(1) == 0) {
                    throw new IllegalArgumentException("Agendamento não encontrado para este usuário.");
                }
            }
        }
    }
}
