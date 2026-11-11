package br.com.psicomatch.dao;

import br.com.psicomatch.config.Conexao;
import java.sql.*;

public class PerfilDAO {
    public void salvar(String cpf,String formacao,String especialidades,String abordagem,String modalidade,String descricao,String interesses)throws Exception{
        String q="INSERT INTO perfil_profissional(profissional_cpf,formacao,especialidades,abordagem,modalidade,descricao) VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE formacao=VALUES(formacao),especialidades=VALUES(especialidades),abordagem=VALUES(abordagem),modalidade=VALUES(modalidade),descricao=VALUES(descricao)";
        try(Connection c=Conexao.conectar()){c.setAutoCommit(false);try(PreparedStatement s=c.prepareStatement(q)){s.setString(1,cpf);s.setString(2,formacao);s.setString(3,especialidades);s.setString(4,abordagem);s.setString(5,modalidade);s.setString(6,descricao);s.executeUpdate();}try(PreparedStatement d=c.prepareStatement("DELETE FROM interesse_profissional WHERE profissional_cpf=?")){d.setString(1,cpf);d.executeUpdate();}try(PreparedStatement i=c.prepareStatement("INSERT INTO interesse_profissional(profissional_cpf,interesse) VALUES(?,?)")){for(String item:interesses.split(",")){if(!item.isBlank()){i.setString(1,cpf);i.setString(2,item.trim());i.addBatch();}}i.executeBatch();}c.commit();}
    }
    public void excluir(String cpf)throws Exception{try(Connection c=Conexao.conectar()){try(PreparedStatement s=c.prepareStatement("DELETE FROM interesse_profissional WHERE profissional_cpf=?")){s.setString(1,cpf);s.executeUpdate();}try(PreparedStatement s=c.prepareStatement("DELETE FROM perfil_profissional WHERE profissional_cpf=?")){s.setString(1,cpf);s.executeUpdate();}}}
    public void exibir()throws Exception{String q="SELECT p.profissional_nome,p.profissional_telefone,p.profissional_email,p.profissional_valor_consulta,pr.formacao,pr.especialidades,pr.abordagem,pr.modalidade,pr.descricao FROM profissional p JOIN perfil_profissional pr ON pr.profissional_cpf=p.profissional_cpf ORDER BY p.profissional_nome";try(Connection c=Conexao.conectar();Statement s=c.createStatement();ResultSet r=s.executeQuery(q)){while(r.next())System.out.println("\n"+r.getString(1)+" | "+r.getString(2)+" | "+r.getString(3)+" | R$ "+r.getBigDecimal(4)+"\nFormação: "+r.getString(5)+"\nEspecialidades: "+r.getString(6)+"\nAbordagem: "+r.getString(7)+"\nModalidade: "+r.getString(8)+"\n"+r.getString(9));}}
}
