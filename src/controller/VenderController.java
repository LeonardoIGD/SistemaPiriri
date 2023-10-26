package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entidades.Produto;
import model.entidades.Venda;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static model.dao.ProdutoDAO.buscarProdutoDAO;

public class VenderController implements Initializable {

    @FXML
    private TextField caixaTextField;
    @FXML
    private TextField Compra;
    @FXML
    private TextField Item;
    @FXML
    private TextField QuantidadeInicial;
    @FXML
    private Button IniciaVenda;
    @FXML
    private Button finalizaVenda;

    private ArrayList<Produto> produtos;

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
        Compra.setDisable(false);
        Item.setDisable(false);
        QuantidadeInicial.setDisable(false);
        IniciaVenda.setVisible(false);
        IniciaVenda.setDisable(true);
        finalizaVenda.setDisable(false);
        finalizaVenda.setVisible(true);

        produtos = new ArrayList<>();
    }

    public void adicionarProdutoButtonOnAction() throws IOException {
        Produto prod = buscarProdutoDAO(caixaTextField.getText());

        if(prod != null){
            Item.setText(String.valueOf(prod.getValorDeVenda()));

            int quant = Integer.parseInt(QuantidadeInicial.getText());
            if (quant == 0) quant = 1;

            double preco = prod.getValorDeVenda();

            double valorCompra = Double.parseDouble(Compra.getText());

            Compra.setText(String.valueOf(valorCompra + quant * preco));

            produtos.add(prod);
        }
    }

    public void finalizarCompraButtonOnAction() throws IOException{
        caixaTextField.setDisable(true);
        Compra.setDisable(true);
        Item.setDisable(true);
        QuantidadeInicial.setDisable(true);
        IniciaVenda.setVisible(true);
        IniciaVenda.setDisable(false);
        finalizaVenda.setDisable(true);
        finalizaVenda.setVisible(false);
    }
}