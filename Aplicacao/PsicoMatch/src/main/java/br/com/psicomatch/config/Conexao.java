package br.com.psicomatch.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexao {
    public static Connection conectar() throws Exception {
        Properties propriedades = new Properties();
        try (InputStream entrada = Conexao.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (entrada == null) throw new IllegalStateException("Arquivo db.properties não encontrado.");
            propriedades.load(entrada);
        }
        return DriverManager.getConnection(propriedades.getProperty("db.url"), propriedades.getProperty("db.user"), propriedades.getProperty("db.password"));
    }
}
