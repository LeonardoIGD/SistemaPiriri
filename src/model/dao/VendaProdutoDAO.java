package model.dao;

import model.bd.Conexao;
import model.entidades.Produto;

import java.sql.*;
import java.util.ArrayList;

public class VendaProdutoDAO {

    public static boolean insereProdutoVendaDAO(int idVenda, ArrayList<Produto> produtos){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("INSERT INTO vendaproduto (tbVenda_id, tbProduto_codigo) VALUES (?, ?);");

            sql.setInt(1, idVenda);

            if(produtos != null){
                for (Produto produto: produtos) {
                    sql.setString(2, produto.getCodigo());
                    sql.executeUpdate();
                }
            }

            sql.close();

            return true;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da inserção SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return false;
        }
    }

}
