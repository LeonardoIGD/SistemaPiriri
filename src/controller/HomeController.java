package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane staticView;
    @FXML
    private ImageView backGroundHomeView;
    @FXML
    private Label cargoLabel;
    @FXML
    private Label nomeUsuarioLabel;
    @FXML
    private ImageView homeIconView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backGroungHomeFile = new File("figs/background2.jpeg");
        Image backGroundHomeImage = new Image(backGroungHomeFile.toURI().toString());
        backGroundHomeView.setImage(backGroundHomeImage);

        File homeIconFile = new File("figs/homeIcon.png");
        Image homeIconHomeImage = new Image(homeIconFile.toURI().toString());
        homeIconView.setImage(homeIconHomeImage);

        cargoLabel.setText(LoginController.func.getCargo());
        nomeUsuarioLabel.setText(LoginController.func.getNome());
    }

    public void estoqueButtonOnAction() throws IOException {
        if (staticView.getChildren().size() > 1){
            staticView.getChildren().remove(1);
            staticView.getChildren().add(PagesController.openEstoquePage());
        }else{
            staticView.getChildren().add(PagesController.openEstoquePage());
        }
    }

    public void venderButtonOnAction() throws IOException{
        PagesController.openVendasPage();
    }

    public void homeButtonOnAction(){
        if (staticView.getChildren().size() > 1){
            staticView.getChildren().remove(1);
        }
    }

    public void sairButtonOnAction(){
        PagesController.stage.close();
    }

    public void voltarButtonOnAction() throws IOException {
        PagesController.openLoginPage();
    }

}