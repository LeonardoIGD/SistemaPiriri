package model.dao;

import model.bd.Conexao;
import model.entidades.Venda;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static model.dao.VendaProdutoDAO.insereProdutoVendaDAO;

public class VendaDAO {

    public static boolean cadastrarVenda(@NotNull Venda venda){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("INSERT INTO venda (tbVenda_datahora, tbVenda_valortotal, tbFuncionario_id, tbVenda_status) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter mascara = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            String datahora = localDateTime.format(mascara);

            sql.setString(1, datahora);
            sql.setDouble(2, venda.getValorDaVenda());
            sql.setInt(3, venda.getId());
            sql.setString(4, venda.getStatus());

            sql.executeUpdate();

            ResultSet generatedKey = sql.getGeneratedKeys();

            if (generatedKey.next()){
                venda.setId(generatedKey.getInt(1));
            }

            sql.close();

            return insereProdutoVendaDAO(venda.getId(), venda.getListaDeProdutos());
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da inserção SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return false;
        }
    }

    public static boolean finalizarVenda(int idVenda, String situacao){
        Conexao conexao = Conexao.getInstance();
        Connection con = conexao.getConexao();

        try {
            PreparedStatement sql = con.prepareStatement("UPDATE venda SET tbVenda_status = ?, tbVenda_datahora = ? WHERE tbVenda_id = ?;");

            sql.setString(1, situacao);

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter mascara = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            String datahora = localDateTime.format(mascara);

            sql.setString(2, datahora);
            sql.setInt(3, idVenda);

            sql.executeUpdate();
            sql.close();

            return true;
        } catch (SQLException e){
            String mensagemDeErro = "Ocorreu um erro durante a execução da edição no SQL: " + e.getMessage();
            System.err.println(mensagemDeErro);

            return false;
        }
    }

}
