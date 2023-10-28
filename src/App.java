import java.io.IOException;

import controller.PagesController;
import javafx.application.Application;
import javafx.stage.Stage;

import static controller.PagesController.stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        PagesController.openLoginPage();
    }

    public static void main(String[] args) {
        launch();
    }
}
