package br.com.psicomatch.dao;

import br.com.psicomatch.config.Conexao;
import br.com.psicomatch.model.Profissional;
import java.sql.*;
import java.util.*;

public class ProfissionalDAO implements CrudDAO<Profissional,String> {
    public void inserir(Profissional p)throws Exception{String sql="INSERT INTO profissional(profissional_cpf,profissional_nome,profissional_telefone,profissional_email,profissional_valor_consulta) VALUES(?,?,?,?,?)";try(Connection c=Conexao.conectar();PreparedStatement s=c.prepareStatement(sql)){s.setString(1,p.getCpf());s.setString(2,p.getNome());s.setString(3,p.getTelefone());s.setString(4,p.getEmail());s.setDouble(5,p.getValorConsulta());s.executeUpdate();}}
    public List<Profissional> listar()throws Exception{List<Profissional> l=new ArrayList<>();try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT * FROM profissional ORDER BY profissional_nome")){while(r.next())l.add(map(r));}return l;}
    public Profissional buscar(String cpf)throws Exception{try(Connection c=Conexao.conectar();PreparedStatement s=c.prepareStatement("SELECT * FROM profissional WHERE profissional_cpf=?")){s.setString(1,cpf);try(ResultSet r=s.executeQuery()){return r.next()?map(r):null;}}}
    public void atualizar(Profissional p)throws Exception{String q="UPDATE profissional SET profissional_nome=?,profissional_telefone=?,profissional_email=?,profissional_valor_consulta=? WHERE profissional_cpf=?";try(Connection c=Conexao.conectar();PreparedStatement s=c.prepareStatement(q)){s.setString(1,p.getNome());s.setString(2,p.getTelefone());s.setString(3,p.getEmail());s.setDouble(4,p.getValorConsulta());s.setString(5,p.getCpf());s.executeUpdate();}}
    public void excluir(String cpf)throws Exception{try(Connection c=Conexao.conectar();PreparedStatement s=c.prepareStatement("DELETE FROM profissional WHERE profissional_cpf=?")){s.setString(1,cpf);s.executeUpdate();}}
    public List<String> buscarPorFiltro(String abordagem,String modalidade,String especialidade)throws Exception{String q="SELECT p.profissional_cpf,p.profissional_nome,pr.abordagem,pr.modalidade,pr.especialidades FROM profissional p JOIN perfil_profissional pr ON pr.profissional_cpf=p.profissional_cpf WHERE pr.abordagem LIKE ? AND pr.modalidade LIKE ? AND pr.especialidades LIKE ? ORDER BY p.profissional_nome";List<String> l=new ArrayList<>();try(Connection c=Conexao.conectar();PreparedStatement s=c.prepareStatement(q)){s.setString(1,"%"+abordagem+"%");s.setString(2,"%"+modalidade+"%");s.setString(3,"%"+especialidade+"%");try(ResultSet r=s.executeQuery()){while(r.next())l.add(r.getString(1)+" | "+r.getString(2)+" | Abordagem: "+r.getString(3)+" | Modalidade: "+r.getString(4)+" | Especialidades: "+r.getString(5));}}return l;}
    private Profissional map(ResultSet r)throws SQLException{return new Profissional(r.getString("profissional_cpf"),r.getString("profissional_nome"),r.getString("profissional_telefone"),r.getString("profissional_email"),r.getDouble("profissional_valor_consulta"));}
}
