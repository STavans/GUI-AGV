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

    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu changelogMenu = new Menu("Changelog");
        Menu routeMenu = new Menu("Route");
        Menu pcControlMenu = new Menu("PC Control");

        MenuItem changelogItem = new MenuItem("Changelog");
        MenuItem routeItem = new MenuItem("Route");
        MenuItem pcControlItem = new MenuItem("PC Control");

        changelogMenu.getItems().add(changelogItem);
        routeMenu.getItems().add(routeItem);
        pcControlMenu.getItems().add(pcControlItem);

        menuBar.getMenus().addAll(changelogMenu, routeMenu, pcControlMenu);

        borderPane.setTop(menuBar);

        Label changeLog = createLabel();
        BorderPane route = Route();
        BorderPane pcControl = createControls();

        changelogItem.setOnAction((event) -> borderPane.setCenter(changeLog));
        routeItem.setOnAction((event) -> borderPane.setCenter(route));
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
        ObservableList<String> route = FXCollections.observableArrayList();
        BorderPane pane = new BorderPane();

        Label addCommand = new Label("Add command:");
        Label savedRoutes = new Label("Saved Routes: ");
        Button stop = new Button("Stop");
        Button forward = new Button("Forward");
        Button backwards = new Button("Backwards");
        Button left = new Button("Left");
        Button right = new Button("Right");
        Button deleteCommand = new Button("Delete");
        Button deleteRoute = new Button("Delete");
        Button save = new Button("Save");
        Button executeRoute = new Button("Execute route");

        ListView newRoute = new ListView();
        ListView routeList = new ListView();

        stop.setOnAction(actionEvent -> {
            newRoute.getItems().add("Stop");
            route.add("Stop");
        });

        forward.setOnAction(actionEvent -> {
            newRoute.getItems().add("Forward");
            route.add("Forward");
        });

        backwards.setOnAction(actionEvent -> {
            newRoute.getItems().add("Backwards");
            route.add("Backwards");
        });

        left.setOnAction(actionEvent -> {
            newRoute.getItems().add("Left");
            route.add("Left");
        });

        right.setOnAction(actionEvent -> {
            newRoute.getItems().add("Right");
            route.add("Right");
        });

        deleteCommand.setOnAction(actionEvent -> {
            int selectedIndices = newRoute.getSelectionModel().getSelectedIndex();

            if (selectedIndices != -1) {
                newRoute.getItems().remove(selectedIndices);
                route.remove(selectedIndices);
            }
        });

        deleteRoute.setOnAction(event -> {
            int selectedRoute = routeList.getSelectionModel().getSelectedIndex();

            if (selectedRoute != -1){
                routeList.getItems().remove(selectedRoute);
            }
        });

        save.setOnAction(actionEvent -> {
            routeList.getItems().add(route);
            for (int i = 0; i < route.size(); i++){
                System.out.println(route.get(i));
            }
        });
        HBox buttonBox1 = new HBox();
        buttonBox1.setSpacing(10);
        buttonBox1.getChildren().addAll(forward, backwards, left, right, stop);

        HBox buttonBox2 = new HBox();
        buttonBox2.setSpacing(10);
        buttonBox2.getChildren().addAll(save, deleteCommand);

        VBox chooseRoute = new VBox();
        chooseRoute.setSpacing(20);
        chooseRoute.getChildren().addAll(addCommand, buttonBox1, newRoute, buttonBox2);

        VBox existingRoute = new VBox();
        existingRoute.setSpacing(20);
        existingRoute.getChildren().addAll(savedRoutes, deleteRoute, routeList, executeRoute);

        HBox routes = new HBox();
        routes.setSpacing(50);
        routes.getChildren().addAll(chooseRoute, existingRoute);

        pane.setCenter(routes);

        return pane;
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
