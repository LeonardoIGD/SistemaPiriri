package model.dao;

import model.bd.Conexao;
import model.entidades.Funcionario;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {

    public static @Nullable Funcionario verificarLoginDAO(String username, String password){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("SELECT * FROM funcionario WHERE tbfuncionario_login = ? AND tbfuncionario_senha = ?;");
            sql.setString(1, username);
            sql.setString(2, password);

            ResultSet rs = null;

            int id = 0;

            String nome = "";
            String cargo = "";

            rs = sql.executeQuery();

            while (rs.next()){
                id = rs.getInt("tbFuncionario_id");
                nome = rs.getString("tbFuncionario_nome");
                username = rs.getString("tbFuncionario_login");
                password = rs.getString("tbFuncionario_senha");
                cargo = rs.getString("tbFuncionario_cargo");
            }

            rs.close();
            sql.close();

            if(id != 0){
                return new Funcionario(id, nome, username, password, cargo);
            }

            return null;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da consulta SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);
            return null;
        }
    }

}
