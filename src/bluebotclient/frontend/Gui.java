package bluebotclient.frontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("Click me to send");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("w, will turn the led on!"));
        borderPane.setCenter(button);
        borderPane.setBottom(new TextArea());
        stage.setTitle("BlueBot Client");
        stage.setScene(new Scene(borderPane));
        stage.show();

    }
}
