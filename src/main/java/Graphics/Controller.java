package Graphics;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button play;
    public Button exit;
    public AnchorPane pane;

    private boolean isRunning;

    public void start() {//TODO: code
        /*while(isRunning){

        }*/
    }

    public void exit(){
        isRunning = false;
        Game.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isRunning=true;
        System.out.println("Loading...");
    }

}
