package br.com.psicomatch.dao;

import br.com.psicomatch.config.Conexao;
import br.com.psicomatch.model.Clinica;
import java.sql.*;
import java.util.*;

public class ClinicaDAO implements CrudDAO<Clinica,String> {
    public void inserir(Clinica c) throws Exception { String sql="INSERT INTO clinica (clinica_cnpj,clinica_nome,clinica_rua,clinica_bairro,clinica_complemento,clinica_numero) VALUES (?,?,?,?,?,?)"; try(Connection con=Conexao.conectar(); PreparedStatement ps=con.prepareStatement(sql)){ps.setString(1,c.getCnpj());ps.setString(2,c.getNome());ps.setString(3,c.getRua());ps.setString(4,c.getBairro());ps.setString(5,c.getComplemento());ps.setString(6,c.getNumero());ps.executeUpdate();} }
    public List<Clinica> listar() throws Exception { List<Clinica> lista=new ArrayList<>(); try(Connection con=Conexao.conectar(); Statement st=con.createStatement(); ResultSet rs=st.executeQuery("SELECT * FROM clinica ORDER BY clinica_nome")){while(rs.next())lista.add(map(rs));} return lista; }
    public Clinica buscar(String id) throws Exception { try(Connection con=Conexao.conectar();PreparedStatement ps=con.prepareStatement("SELECT * FROM clinica WHERE clinica_cnpj=?")){ps.setString(1,id);try(ResultSet rs=ps.executeQuery()){return rs.next()?map(rs):null;}} }
    public void atualizar(Clinica c) throws Exception { String sql="UPDATE clinica SET clinica_nome=?,clinica_rua=?,clinica_bairro=?,clinica_complemento=?,clinica_numero=? WHERE clinica_cnpj=?"; try(Connection con=Conexao.conectar();PreparedStatement ps=con.prepareStatement(sql)){ps.setString(1,c.getNome());ps.setString(2,c.getRua());ps.setString(3,c.getBairro());ps.setString(4,c.getComplemento());ps.setString(5,c.getNumero());ps.setString(6,c.getCnpj());ps.executeUpdate();} }
    public void excluir(String id) throws Exception { try(Connection con=Conexao.conectar();PreparedStatement ps=con.prepareStatement("DELETE FROM clinica WHERE clinica_cnpj=?")){ps.setString(1,id);ps.executeUpdate();} }
    public void vincularProfissional(String cnpj,String cpf) throws Exception {try(Connection con=Conexao.conectar();PreparedStatement ps=con.prepareStatement("INSERT INTO equipe(clinica_cnpj,profissional_cpf) VALUES(?,?)")){ps.setString(1,cnpj);ps.setString(2,cpf);ps.executeUpdate();}}
    private Clinica map(ResultSet rs)throws SQLException{return new Clinica(rs.getString("clinica_cnpj"),rs.getString("clinica_nome"),rs.getString("clinica_rua"),rs.getString("clinica_bairro"),rs.getString("clinica_complemento"),rs.getString("clinica_numero"));}

    public void listarEquipe(String cnpj) throws Exception {
        String sql = "SELECT p.profissional_cpf, p.profissional_nome, p.profissional_telefone, p.profissional_email FROM equipe e JOIN profissional p ON p.profissional_cpf=e.profissional_cpf WHERE e.clinica_cnpj=? ORDER BY p.profissional_nome";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cnpj);

            try (ResultSet rs = ps.executeQuery()) {
                boolean encontrou = false;

                while (rs.next()) {
                    encontrou = true;
                    System.out.println(rs.getString("profissional_cpf") + " | " + rs.getString("profissional_nome") + " | " + rs.getString("profissional_telefone") + " | " + rs.getString("profissional_email"));
                }

                if (!encontrou) {
                    System.out.println("A clínica ainda não possui profissionais na equipe.");
                }
            }
        }
    }
}
