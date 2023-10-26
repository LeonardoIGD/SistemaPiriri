package model.bd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Conexao instance;
    private Connection conexcao;

    private Conexao() {
        try {
            this.conexcao = DriverManager.getConnection("jdbc:postgresql://isabelle.db.elephantsql.com:5432/xoubvfub", System.getenv("DB_USERNAME"), System.getenv("DB_PASSWORD"));
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    public static synchronized Conexao getInstance(){
        if(instance == null){
            instance = new Conexao();
        }

        return instance;
    }

    //Metodo get que retorna a conexao
    public Connection getConexao(){
        return this.conexcao;
    }

}
