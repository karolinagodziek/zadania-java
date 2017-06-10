package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.Random;

public class Controller {

    @FXML
        Button runButton;

    @FXML
    
    private void initialize() {
        
        runButton.setOnMouseEntered(event -> moveButton());
        
    }

    private void moveButton() {
        
        Random rnd = new Random();
        runButton.setTranslateX(rnd.nextInt(500));
        runButton.setTranslateY(rnd.nextInt(450));
        
    }
}
