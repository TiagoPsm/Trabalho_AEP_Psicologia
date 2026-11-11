package br.com.psicomatch.dao;

import br.com.psicomatch.config.Conexao;
import java.sql.*;

public class PainelDAO {
    public void exibir(String profissionalCpf)throws Exception{
        String q="SELECT COUNT(*) total,SUM(CASE WHEN COALESCE(st.status,'AGENDADO')='REALIZADO' THEN 1 ELSE 0 END) realizados,SUM(CASE WHEN COALESCE(st.status,'AGENDADO')='CANCELADO' THEN 1 ELSE 0 END) cancelados,COALESCE(SUM(CASE WHEN COALESCE(st.status,'AGENDADO')='REALIZADO' THEN p.profissional_valor_consulta ELSE 0 END),0) faturamento FROM agendamento a JOIN profissional p ON p.profissional_cpf=a.profissional_cpf LEFT JOIN agendamento_status st ON st.agendamento_id=a.agendamento_id WHERE a.profissional_cpf=?";
        try(Connection c=Conexao.conectar();PreparedStatement s=c.prepareStatement(q)){s.setString(1,profissionalCpf);try(ResultSet r=s.executeQuery()){r.next();int total=r.getInt("total"),realizados=r.getInt("realizados"),cancelados=r.getInt("cancelados");double taxa=total==0?0:(realizados*100.0/total);System.out.println("Total de consultas: "+total);System.out.println("Realizadas: "+realizados);System.out.println("Canceladas: "+cancelados);System.out.printf("Taxa de realização: %.2f%%%n",taxa);System.out.printf("Faturamento: R$ %.2f%n",r.getDouble("faturamento"));}}
    }
}
