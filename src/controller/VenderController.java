package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entidades.Produto;
import model.entidades.Venda;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static model.dao.ProdutoDAO.buscarProdutoDAO;
import static model.dao.ProdutoDAO.editarProdutoDAO;

public class VenderController implements Initializable {

    @FXML
    private TextField caixaTextField;
    @FXML
    private Label totalBuy;
    @FXML
    private Label itemPrice;
    @FXML
    private Button IniciaVenda;
    @FXML
    private Button finalizaVenda;

    private HashMap<String, Double> produtosVendidos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        caixaTextField.setStyle("-fx-text-fill: Red; -fx-background-color: #CCCCCC;");
    }

    public void menuButtonOnAction() throws IOException{
        PagesController.stage.close();
        PagesController.openHomePage();
        PagesController.isConsulta = false;
    }

    public void consultarPrecoButtonOnAction() throws IOException{
        PagesController.isConsulta = true;
        Scene scene = new Scene(PagesController.openEstoquePage(),710,575);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(PagesController.stage);
        stage1.setTitle("Consultar Preços");
        stage1.setResizable(false);
        stage1.show();
    }

    public void iniciaVendaButtonOnAction() throws IOException{
        caixaTextField.setDisable(false);
        IniciaVenda.setVisible(false);
        IniciaVenda.setDisable(true);
        finalizaVenda.setDisable(false);
        finalizaVenda.setVisible(true);

        produtosVendidos = new HashMap<>();
    }

    public void adicionarProdutoButtonOnAction() throws IOException {
        Produto prod = null;
        double quant = 0.0;

        String[] entradas = caixaTextField.getText().split("x");

        if(caixaTextField.getText().contains("x")){
            if(Objects.equals(entradas[0], "x")){
                quant = 1.0;
            } else {
                quant = Double.parseDouble(entradas[0]);
            }

            prod = buscarProdutoDAO(entradas[1]);
        } else {
            prod = buscarProdutoDAO(entradas[0]);
            quant = 1.0;
        }

        if(prod != null){
            itemPrice.setText(String.valueOf(prod.getValorDeVenda()));

            double preco = prod.getValorDeVenda();
            double valorCompra = Double.parseDouble(totalBuy.getText());

            totalBuy.setText(String.valueOf(valorCompra + quant * preco));

            produtosVendidos.put(prod.getCodigo(), quant);
        }
    }

    public void finalizarCompraButtonOnAction() throws IOException{
        caixaTextField.clear();
        totalBuy.setText("0.00");
        itemPrice.setText("0.00");

        caixaTextField.setDisable(true);
        IniciaVenda.setVisible(true);
        IniciaVenda.setDisable(false);
        finalizaVenda.setDisable(true);
        finalizaVenda.setVisible(false);

        for (Map.Entry<String, Double> prod: produtosVendidos.entrySet()) {
            Produto produto = buscarProdutoDAO(prod.getKey());

            assert produto != null;
            produto.setQuantidade(produto.getQuantidade() - prod.getValue());

            editarProdutoDAO(produto);
        }
    }
}