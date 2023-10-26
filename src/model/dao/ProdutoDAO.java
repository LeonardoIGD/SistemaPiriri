package model.dao;

import model.bd.Conexao;
import model.entidades.Produto;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {

    public static boolean cadastrarProdutoDAO(@NotNull Produto produto){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("INSERT INTO produto (tbProduto_codigo, tbProduto_descricao, tbProduto_peso, tbProduto_valorcompra, tbProduto_valorvenda, tbProduto_quantidade, tbProduto_vencimento, tbProduto_detalhes) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

            sql.setString(1, produto.getCodigo());
            sql.setString(2, produto.getDescricao());
            sql.setString(3, produto.getPeso());
            sql.setDouble(4, produto.getValorDeCompra());
            sql.setDouble(5, produto.getValorDeVenda());
            sql.setDouble(6, produto.getQuantidade());
            sql.setString(7, produto.getVencimento());
            sql.setString(8, produto.getDetalhes());

            sql.executeUpdate();
            sql.close();

            return true;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução do inseção SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return false;
        }
    }

    public static Produto buscarProdutoDAO(String codigo){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("SELECT * FROM produto WHERE tbProduto_codigo = ?;");

            sql.setString(1, codigo);

            ResultSet rs;

            String descricao = "";
            String peso = "";
            double valorCompra = 0.0;
            double valorVenda = 0.0;
            double quantidade = 0.0;
            String vencimento = "";
            String detalhes = "";

            rs = sql.executeQuery();

            while (rs.next()){
                codigo = rs.getString("tbProduto_codigo");
                descricao = rs.getString("tbProduto_descricao");
                peso = rs.getString("tbProduto_peso");
                valorCompra = rs.getDouble("tbProduto_valorcompra");
                valorVenda = rs.getDouble("tbProduto_valorvenda");
                quantidade = rs.getDouble("tbProduto_quantidade");
                vencimento = rs.getString("tbProduto_vencimento");
                detalhes = rs.getString("tbProduto_detalhes");
            }

            if(!descricao.isEmpty()){
                return new Produto(codigo, descricao, quantidade, valorCompra, valorVenda, peso, vencimento, detalhes);
            }

            rs.close();
            sql.close();

            return null;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da consulta SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return null;
        }
    }

    public static ArrayList<Produto> buscarProdutosDAO(){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("SELECT * FROM produto;");

            ResultSet rs;
            rs = sql.executeQuery();

            ArrayList<Produto> produtos = new ArrayList<>();

            while (rs.next()){
                Produto produto = new Produto();

                produto.setCodigo(rs.getString("tbProduto_codigo"));
                produto.setDescricao(rs.getString("tbProduto_descricao"));
                produto.setPeso(rs.getString("tbProduto_peso"));
                produto.setValorDeCompra(rs.getDouble("tbProduto_valorcompra"));
                produto.setValorDeVenda(rs.getDouble("tbProduto_valorvenda"));
                produto.setQuantidade(rs.getDouble("tbProduto_quantidade"));
                produto.setVencimento(rs.getString("tbProduto_vencimento"));
                produto.setDetalhes(rs.getString("tbProduto_detalhes"));

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da consulta SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return null;
        }
    }

    public static boolean editarProdutoDAO(@NotNull Produto produto){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("UPDATE produto SET tbProduto_codigo = ?, tbProduto_descricao = ?, tbProduto_peso = ?, tbProduto_valorcompra = ?, tbProduto_valorvenda = ?, tbProduto_vencimento = ?, tbProduto_detalhes = ?, tbProduto_quantidade = ? WHERE tbProduto_codigo = ?;");

            sql.setString(1, produto.getCodigo());
            sql.setString(2, produto.getDescricao());
            sql.setString(3, produto.getPeso());
            sql.setDouble(4, produto.getValorDeCompra());
            sql.setDouble(5, produto.getValorDeVenda());
            sql.setString(6, produto.getVencimento());
            sql.setString(7, produto.getDetalhes());
            sql.setDouble(8, produto.getQuantidade());
            sql.setString(9, produto.getCodigo());

            sql.executeUpdate();
            sql.close();

            return true;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da edição no SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return false;
        }
    }

    public static boolean removerProdutoDAO(Produto produto){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("DELETE FROM produto WHERE tbProduto_codigo = ?;");

            sql.setString(1, produto.getCodigo());

            sql.executeUpdate();
            sql.close();

            return true;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da remoção do SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return false;
        }
    }

}
