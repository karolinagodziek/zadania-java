package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
        RadioButton celciusStart;
    @FXML
        RadioButton fahrenheitStart;
    @FXML
        RadioButton kelvinStart;
    @FXML
        RadioButton celciusEnd;
    @FXML
        RadioButton fahrenheitEnd;
    @FXML
        RadioButton kelvinEnd;
    @FXML
        TextField output;
    @FXML
        TextField txtFieldValue;
    @FXML
    
    private void init() {
        
        selectFirst();
        initActions();
        
    }

    private void selectFirst() {
        
        celciusEnd.setSelected(true);
        celciusStart.setSelected(true);
        txtFieldValue.setText("0");
        output.setText("0");
        
    }

    private void initActions() {
        
        txtFieldValue.setOnKeyReleased(event -> {
            convertData();
            
        });

        celciusEnd.setOnMouseClicked(event -> convertData());
        celciusStart.setOnMouseClicked(event -> convertData());
        kelvinEnd.setOnMouseClicked(event -> convertData());
        kelvinStart.setOnMouseClicked(event -> convertData());
        fahrenheitStart.setOnMouseClicked(event -> convertData());
        fahrenheitEnd.setOnMouseClicked(event -> convertData());
    }

    private void convertData() {
        
        try {
            double degreeFrom = Double.valueOf(txtFieldValue.getText());
            double degreeTo = 0;

            if ((celciusStart.isSelected() && celciusEnd.isSelected()) ||
                    (kelvinStart.isSelected() && kelvinEnd.isSelected()) ||
                    (fahrenheitStart.isSelected() && fahrenheitEnd.isSelected())) {
                output.setText(String.valueOf(degreeFrom));
                
            } else {

                if (celciusStart.isSelected() && kelvinEnd.isSelected()) 
                    degreeTo = degreeFrom + 273.15;
                 else if (celciusStart.isSelected() && fahrenheitEnd.isSelected()) 
                    degreeTo = (degreeFrom * (9 / 5)) + 32;
                 else if (kelvinStart.isSelected() && celciusEnd.isSelected()) 
                    degreeTo = degreeFrom - 273.15;
                 else if (kelvinStart.isSelected() && fahrenheitEnd.isSelected()) 
                    degreeTo = (degreeFrom * (9 / 5)) - 459.67;
                else if (fahrenheitStart.isSelected() && celciusEnd.isSelected()) 
                    degreeTo = (degreeFrom - 32) / 1.8;
                 else if (fahrenheitStart.isSelected() && kelvinEnd.isSelected()) 
                    degreeTo = (degreeFrom + 459.67) / 1.8;
                
                output.setText(String.valueOf(degreeTo));
                
            }
            
        } catch (Exception ex) {
            
            output.setText("Wrong value");
            
        }
    }

}
