package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Main extends Application {
    private final String INITIAL_TEXT = "<html><body onLoad='document.body.focus();'>\n" +
            "Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook! Ook. Ook. Ook. Ook. Ook.\n" +
            "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook! Ook! Ook?\n" +
            "Ook? Ook! Ook? Ook. Ook. Ook! Ook.\n" +
            "Ook! Ook. Ook. Ook? Ook. Ook.\n" +
            "Ook. Ook. Ook! Ook? Ook? Ook. Ook. Ook.\n" +
            "Ook? Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook?\n" +
            "Ook. Ook. Ook. Ook. Ook! Ook? Ook? Ook. Ook.\n" +            
            "Ook! Ook. Ook. Ook? Ook. Ook? Ook. Ook. Ook! Ook. </body></html>";
    @Override
    public void start(Stage stage) {
        stage.setTitle("Edytor języka Ook");
        stage.setWidth(700);
        stage.setHeight(500);
        Scene scene = new Scene(new Group());

        VBox root = new VBox();
        root.setPadding(new Insets(8, 8, 8, 8));
        root.setSpacing(5);
        root.setAlignment(Pos.BOTTOM_LEFT);

        final HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(345);
        htmlEditor.setHtmlText(INITIAL_TEXT);

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        Button showHTMLButton = new Button("Sprawdź");
        root.setAlignment(Pos.CENTER);

        showHTMLButton.setOnAction(new EventHandler<ActionEvent>() {
            String ook = new String(htmlEditor.getHtmlText());
            @Override public void handle(ActionEvent arg0) {
                String ook = new String(htmlEditor.getHtmlText());
                ook = ook.replaceAll("</{0,1}font.*?>","");;
                ook = ook.replaceAll("(?<=^|\\s)Ook[.]{1}(?=\\s|$)","<font color=\"magenta\">Ook.</font>");
                ook = ook.replaceAll("(?<=^|\\s)Ook[?]{1}(?=\\s|$)","<font color=\"cyan\">Ook?</font>");
                ook = ook.replaceAll("(?<=^|\\s)Ook[!]{1}(?=\\s|$)","<font color=\"lightGray\">Ook!</font>");
                htmlEditor.setHtmlText(ook);
            }
        });
        htmlEditor.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if (isValidEvent(event)){
                    String ook = new String(htmlEditor.getHtmlText());
                ook = ook.replaceAll("<\\/{0,1}font.*?>","");
                ook = ook.replaceAll("(?<=^|\\s)Ook[.]{1}(?=\\s|$)","<font color=\"magenta\">Ook.</font>");
                ook = ook.replaceAll("(?<=^|\\s)Ook[?]{1}(?=\\s|$)","<font color=\"cyan\">Ook?</font>");
                ook = ook.replaceAll("(?<=^|\\s)Ook[!]{1}(?=\\s|$)","<font color=\"lightGray\">Ook!</font>");
                htmlEditor.setHtmlText(ook);
                }
            }

            private boolean isValidEvent(KeyEvent event){
                return !isSelectAllEvent(event)
                        && ((isPasteEvent(event)) || isCharacterKeyReleased(event));
            }

            private boolean isSelectAllEvent(KeyEvent event){
                return event.isShortcutDown() && event.getCode() == KeyCode.A;
            }

            private boolean isPasteEvent(KeyEvent event){
                return event.isShortcutDown() && event.getCode() == KeyCode.V;
            }
            
            private boolean isCharacterKeyReleased(KeyEvent event){
                
                switch (event.getCode()){
                    case ALT:
                    case COMMAND:
                    case CONTROL:
                    case SHIFT:
                    case PAGE_UP:
                    case PAGE_DOWN:
                    case HOME:
                    case END:
                    case LEFT:
                    case RIGHT:
                    case UP:
                    case DOWN:
                        return false;
                    default:
                        return true;
                }
            }
        });

        root.getChildren().addAll(htmlEditor, showHTMLButton);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);

    }
}