package Graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {//TODO:add choice for players

    private Scene mainMenu;
    private static Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        setScenes();
        window.setScene(mainMenu);
        window.setResizable(false);
        window.show();
    }

    private void setScenes(){

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            mainMenu = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
            mainMenu.getStylesheets().addAll(
                    this.getClass().getResource("mainMenuStyle.css").toExternalForm(),
                    this.getClass().getResource("button.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
            window.close();
        }


    }

    static void close(){
        window.close();
    }
}
