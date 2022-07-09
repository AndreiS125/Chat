package org.andreis.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static FXMLLoader main = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    static FXMLLoader regis = new FXMLLoader(HelloApplication.class.getResource("registry.fxml"));
    public static Network network;
    static Stage st;
    @Override
    public void start(Stage stage) throws IOException {
        st=stage;
        network=new Network(main.getController());
        Scene scene = new Scene(regis.load(), 600, 400);
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();
        //network.openConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}