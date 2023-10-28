package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entidades.Produto;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {

    @FXML
    private TextField codTextField;
    @FXML
    private TextField compTextField;
    @FXML
    private TextField descTextField;
    @FXML
    private TextField forTextField;
    @FXML
    private TextField pesoTextField;
    @FXML
    private TextField qntTextField;
    @FXML
    private TextField vendTextField;
    @FXML
    private Label tituloLabel;
    @FXML
    private GridPane cadastroGrid;
    private Stage interacao;
    private boolean botaoConfirmarClicado;
    private Produto produto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleBotaoConfirmar(){
        if (validarEntradaDeDados()) {
            produto.setCodigo(codTextField.getText());
            produto.setDescricao(descTextField.getText());
            produto.setPeso(pesoTextField.getText());
            produto.setValorDeCompra(Double.parseDouble(compTextField.getText()));
            produto.setValorDeVenda(Double.parseDouble(vendTextField.getText()));
            produto.setDetalhes(forTextField.getText());
            produto.setQuantidade(Double.parseDouble(qntTextField.getText()));
            botaoConfirmarClicado = true;
            interacao.close();
        }
    }

    public void handleBotaoCancelar(){
        interacao.close();
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.codTextField.setText(produto.getCodigo());
        this.descTextField.setText(produto.getDescricao());
        this.pesoTextField.setText(produto.getPeso());
        this.vendTextField.setText(String.valueOf(produto.getValorDeVenda()));
        this.compTextField.setText(String.valueOf(produto.getValorDeCompra()));
        this.forTextField.setText(produto.getDetalhes());
        this.qntTextField.setText(String.valueOf(produto.getQuantidade()));
    }

    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        for (Node node : cadastroGrid.getChildren()) {
            if (((TextField) node).getText() == null || ((TextField) node).getText().trim().isEmpty()) {
                node.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                errorMessage += ((TextField) node).getPromptText() +"\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    public void setLabelTitulo(String titulo) {
        this.tituloLabel.setText(titulo);
    }
    public void setInteracao(Stage interacao) {
        this.interacao = interacao;
    }
    public boolean isBotaoClicado() {
        return botaoConfirmarClicado;
    }

}