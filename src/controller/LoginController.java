package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.entidades.Funcionario;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.dao.FuncionarioDAO.verificarLoginDAO;


public class LoginController implements Initializable {
    @FXML
    private ImageView backGroundView;
    @FXML
    private ImageView loginView;
    @FXML
    private Label loginMensage;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField passwordTextField;
    public static Funcionario func;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File backGroungFile = new File("figs/background.jpg");
        Image backGroundImage = new Image(backGroungFile.toURI().toString());
        backGroundView.setImage(backGroundImage);

        File loginFile = new File("figs/login.png");
        Image loginImage = new Image(loginFile.toURI().toString());
        loginView.setImage(loginImage);
    }

    public void loginButtonOnAction() throws IOException {
        if(validarLogin()){
            PagesController.openHomePage();
        } else {
            loginMensage.setTextFill(Color.RED);
            loginMensage.setAlignment(Pos.CENTER);
            loginMensage.setText("Dados de login invalidos.");
        }
    }

    public void cancelarButtonOnAction(){
        PagesController.stage.close();
    }

    public boolean validarLogin(){
        func = verificarLoginDAO(userTextField.getText(), passwordTextField.getText());
        return func != null;
    }

}