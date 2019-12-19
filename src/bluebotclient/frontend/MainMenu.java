package bluebotclient.frontend;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.List;

public class MainMenu extends Application {

    private ObservableList<String> route =
            FXCollections.observableArrayList();

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
        BorderPane route = Route();
        ListView routeList = createListView();
        BorderPane pcControl = createControls();

        changelogItem.setOnAction((event) -> borderPane.setCenter(changeLog));
        routeItem.setOnAction((event) -> borderPane.setCenter(route));
        routeListItem.setOnAction((event) -> borderPane.setCenter(routeList));
        pcControlItem.setOnAction((event) -> borderPane.setCenter(pcControl));

        borderPane.setCenter(changeLog);

        Scene scene = new Scene(borderPane, 800, 600);

        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("BoeBot UI");
        stage.show();
    }

    private Label createLabel() {
        Label label = new Label();

        return label;
    }

    public BorderPane Route(){
        BorderPane pane = new BorderPane();

        Label label = new Label("Add command:");
        Button button = new Button("Stop");
        Button button1 = new Button("Forward");
        Button button2 = new Button("Backwards");
        Button button3 = new Button("Left");
        Button button4 = new Button("Right");
        Button button5 = new Button("Delete");
        Button button6 = new Button("Save");
        ListView view = new ListView();

        button.setOnAction(actionEvent -> {
            view.getItems().add("Stop");
            route.add("Stop");
            System.out.println();
        });

        button1.setOnAction(actionEvent -> {
            view.getItems().add("Forward");
            route.add("Forward");
        });

        button2.setOnAction(actionEvent -> {
            view.getItems().add("Backwards");
            route.add("Backwards");
        });

        button3.setOnAction(actionEvent -> {
            view.getItems().add("Left");
            route.add("Left");
        });

        button4.setOnAction(actionEvent -> {
            view.getItems().add("Right");
            route.add("Right");
        });

        button5.setOnAction(actionEvent -> {
            int selectedIndices = view.getSelectionModel().getSelectedIndex();
            if (selectedIndices != -1) {

                view.getItems().remove(selectedIndices);
                route.remove(selectedIndices);
            }
        });

        button6.setOnAction(actionEvent -> {
            for (int i = 0; i < route.size(); i++){
                System.out.println(route.get(i));
            }
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(button1, button2, button3, button4, button);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label, hBox, view, button6, button5);

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
