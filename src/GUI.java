/*
Created by Risto Pärnapuu on 30.04.2015.
*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(new File("resources/GUI.fxml").toURI().toURL());
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
        primaryStage.setTitle("Matrix operations");
    }

    public static void main(String []args) {
        launch(args);
    }
}