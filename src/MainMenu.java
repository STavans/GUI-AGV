import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class MainMenu extends Application {

    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu changelogMenu = new Menu("Changelog");
        Menu routeMenu = new Menu("Route");
        Menu routeListMenu = new Menu("Route List");
        Menu pcControlMenu = new Menu("PC Control");

        MenuItem changelogItem = new MenuItem("Changelog");
        MenuItem routeItem = new MenuItem("Route");
        MenuItem routeListItem = new MenuItem("Route List");
        MenuItem pcControlItem = new MenuItem("PC Control");

        changelogMenu.getItems().add(changelogItem);
        routeMenu.getItems().add(routeItem);
        routeListMenu.getItems().add(routeListItem);
        pcControlMenu.getItems().add(pcControlItem);

        menuBar.getMenus().addAll(changelogMenu, routeMenu, routeListMenu, pcControlMenu);

        borderPane.setTop(menuBar);

        Label changeLog = createLabel();
        BorderPane route = createRoute();
        ListView routeList = createListView();
        BorderPane pcControl = createControls();

        changelogItem.setOnAction((event) -> borderPane.setCenter(changeLog));
        routeItem.setOnAction((event) -> borderPane.setCenter(route));
        routeListItem.setOnAction((event) -> borderPane.setCenter(routeList));
        pcControlItem.setOnAction((event) -> borderPane.setCenter(pcControl));

        borderPane.setCenter(changeLog);

        Scene scene = new Scene(borderPane, 800, 600);

//        scene.setOnKeyPressed(event -> {
//            switch (event.getCode()) {
//                case UP:    goNorth = true; break;
//                case DOWN:  goSouth = true; break;
//                case LEFT:  goWest  = true; break;
//                case RIGHT: goEast  = true; break;
//                case SHIFT: running = true; break;
//            }
//        });
//
//        scene.setOnKeyReleased(event -> {
//            switch (event.getCode()) {
//                case UP:    goNorth = false; break;
//                case DOWN:  goSouth = false; break;
//                case LEFT:  goWest  = false; break;
//                case RIGHT: goEast  = false; break;
//                case SHIFT: running = false; break;
//            }
//        });

        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("BoeBot UI");
        stage.show();
    }

    private Label createLabel() {
        Label label = new Label();

        return label;
    }

    private BorderPane createRoute(){
        BorderPane pane = new BorderPane();

        Label label = new Label("Add command:");
        TextField field = new TextField();
        Button button = new Button("Add");
        TextArea area = new TextArea();

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label, field, button, area);

        pane.setLeft(vBox);

        return pane;
    }

    private ListView createListView(){
        ListView view = new ListView();

        view.getItems().add("Enter a list here");
        view.getItems().add("Enter a list here");
        view.getItems().add("Enter a list here");
        view.getItems().add("Enter a list here");
        view.getItems().add("Enter a list here");

        return view;
    }


    private BorderPane createControls(){
        BorderPane pane = new BorderPane();
        Button button = new Button("Control Toggle");
        TextArea area = new TextArea();

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(button, area);

        pane.setLeft(vBox);

        return pane;
    }
}
