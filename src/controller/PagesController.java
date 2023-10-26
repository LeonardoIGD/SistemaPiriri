package controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;

public class  PagesController {
    public static Stage stage;
    public static boolean isConsulta = false;
    public static Stage interacao;
    public static boolean alterar = false;

    public static void centerViews(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getScene().getWidth()) / 2;
        double centerY = bounds.getMinY() + (bounds.getHeight() - stage.getScene().getHeight()) / 2;

        stage.setX(centerX);
        stage.setY(centerY);
        stage.setWidth(stage.getScene().getWidth());
        stage.setHeight(stage.getScene().getHeight());
    }


    public static void openHomePage() throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(PagesController.class.getResource("../view/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 740);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.setScene(scene);
        centerViews();
        stage.show();
    }
    public static void openVendasPage() throws IOException{
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(PagesController.class.getResource("../view/VenderView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 740);
        stage.setTitle("Vendas");
        stage.setScene(scene);
        centerViews();
        stage.show();
    }
    public static void openLoginPage() throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(PagesController.class.getResource("../view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,435);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        centerViews();
        stage.show();
    }
    public static Parent openEstoquePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PagesController.class.getResource("../view/EstoqueView.fxml"));
        return loader.load();
    }

    public static void openCadastrarPage() throws IOException {
        interacao = new Stage();
        FXMLLoader loader = new FXMLLoader(PagesController.class.getResource("../view/CadastrarProduto.fxml"));
        Scene scene = new Scene(loader.load(),700,600);
        interacao.setScene(scene);
        interacao.initModality(Modality.WINDOW_MODAL);
        interacao.initOwner(PagesController.stage);
        interacao.setResizable(false);
        interacao.show();
    }
}
