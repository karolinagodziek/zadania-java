package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
        Button buttonAdd;
    @FXML
        Button buttonDelete;
    @FXML
        Button buttonEdit;
    @FXML
        TextField nameTxt;
    @FXML
        TextField numberTxt;
    @FXML
        TableView<Person> viewTable;

    private ObservableList<Person> data;

    @FXML
    
    private void initialize() {
        
        buttonAdd.setOnMouseClicked(event -> {
            
            data.add(new Person(nameTxt.getText(), numberTxt.getText()));
            
        });
        buttonDelete.setOnMouseClicked(event -> {
            
            data.remove(viewTable.getSelectionModel().getSelectedItem());
            
        });
        buttonEdit.setOnMouseClicked(event -> {
            
            Person person = viewTable.getSelectionModel().getSelectedItem();
            nameTxt.setText(person.getName());
            numberTxt.setText(person.getPhone());
            data.remove(person);
            
        });

        viewTable.setEditable(true);
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
        TableColumn phoneCol = new TableColumn("Phone");
        phoneCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("Phone")
                
        );

        data = FXCollections.observableArrayList();
        viewTable.setItems(data);
        viewTable.getColumns().addAll(nameCol, phoneCol);
        
    }
}
