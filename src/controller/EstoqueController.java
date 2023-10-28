package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.dao.ProdutoDAO;
import model.entidades.Produto;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static model.dao.ProdutoDAO.buscarProdutosDAO;

public class EstoqueController implements Initializable {

    @FXML
    private Button cadastrarEstoqueButton;
    @FXML
    private Button ExcluirEstoqueButton;
    @FXML
    private Button EditarEstoqueButton;
    @FXML
    private TableColumn<Produto, String> produtosTableColumn;
    @FXML
    private TableView<Produto> tableViewProduto;
    @FXML
    private Label codLabel;
    @FXML
    private Label descricaoLabel;
    @FXML
    private Label fornecedorLabel;
    @FXML
    private Label precoLabel;
    @FXML
    private Label qntLabel;
    private List<Produto> listProdutos;
    private ObservableList<Produto> observableListProdutos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validarPermissao();
        carregarTableViewProdutos();
        tableViewProduto.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue
        ) -> selecionarItemTableViewProduto(newValue));
    }

    private void validarPermissao(){
        if(LoginController.func.getCargo().equalsIgnoreCase("caixa") || PagesController.isConsulta){
            cadastrarEstoqueButton.setDisable(true);
            EditarEstoqueButton.setDisable(true);
            ExcluirEstoqueButton.setDisable(true);
        }
    }

    public void carregarTableViewProdutos(){
        produtosTableColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        listProdutos = buscarProdutosDAO();
        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        tableViewProduto.setItems(observableListProdutos);
    }

    public void selecionarItemTableViewProduto(Produto produto){
        if (produto != null) {
            codLabel.setText(produto.getCodigo());
            descricaoLabel.setText(produto.getDescricao());
            precoLabel.setText(String.valueOf(produto.getValorDeVenda()));
            fornecedorLabel.setText(produto.getDetalhes());
            qntLabel.setText(String.valueOf(produto.getQuantidade()));
        } else {
            codLabel.setText("");
            descricaoLabel.setText("");
            precoLabel.setText("");
            fornecedorLabel.setText("");
            qntLabel.setText("");
        }
    }

    public void handleBotaoCadastrar() throws IOException {
        final String nome = "CADASTRAR PRODUTO";
        Produto produto = new Produto();
        boolean botaoConfirmarClicado = showCadastroProduto(produto,nome);
        if (botaoConfirmarClicado) {
            ProdutoDAO.cadastrarProdutoDAO(produto);
            carregarTableViewProdutos();
        }
    }

    @FXML
    public void handleBotaoAlterar() throws IOException {
        final String nome = "EDITAR PRODUTO";
        Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
        if (produto != null) {
            boolean botaoConfirmarClicado = showCadastroProduto(produto,nome);
            if (botaoConfirmarClicado) {
                ProdutoDAO.editarProdutoDAO(produto);
                carregarTableViewProdutos();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela!");
            alert.show();
        }
    }

    public void handleBotaoRemover(){
        Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
        if (produto != null) {
            ProdutoDAO.removerProdutoDAO(produto);
            carregarTableViewProdutos();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(produto.getDescricao().toUpperCase()+" REMOVIDO COM SUCESSO!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela!");
            alert.show();
        }
    }

    public boolean showCadastroProduto(Produto produto,String nome) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadastroController.class.getResource("../view/CadastrarProduto.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle(nome);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        CadastroController controller = loader.getController();
        controller.setInteracao(dialogStage);
        controller.setProduto(produto);
        controller.setLabelTitulo(nome);
        dialogStage.showAndWait();

        return controller.isBotaoClicado();
    }

}
