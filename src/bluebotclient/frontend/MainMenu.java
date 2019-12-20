package bluebotclient.frontend;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import bluebotclient.backend.BlueBotConnection;
import jssc.SerialPort;

public class MainMenu extends Application {

    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu routeMenu = new Menu("Route");
        Menu pcControlMenu = new Menu("PC Control");

        MenuItem routeItem = new MenuItem("Route");
        MenuItem pcControlItem = new MenuItem("PC Control");

        routeMenu.getItems().add(routeItem);
        pcControlMenu.getItems().add(pcControlItem);

        menuBar.getMenus().addAll(routeMenu, pcControlMenu);

        borderPane.setTop(menuBar);

        BorderPane route = Route();
        BorderPane pcControl = createControls();

        routeItem.setOnAction((event) -> borderPane.setCenter(route));
        pcControlItem.setOnAction((event) -> borderPane.setCenter(pcControl));

        borderPane.setCenter(route);

        Scene scene = new Scene(borderPane, 900, 700);

        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle("BoeBot UI");
        stage.show();
    }

    public BorderPane Route() {
        ObservableList<String> route = FXCollections.observableArrayList();
        BorderPane pane = new BorderPane();

        Label addCommand = new Label("Add command:");
        Label savedRoutes = new Label("Saved Routes: ");
        Button stop = new Button("Stop");
        Button end = new Button("Einde");
        Button forward = new Button("Forward");
        Button left = new Button("Left");
        Button right = new Button("Right");
        Button deleteCommand = new Button("Delete");
        Button deleteRoute = new Button("Delete");
        Button save = new Button("Save");
        Button executeRoute = new Button("Execute route");
        Button cancelRoute = new Button("Cancel route");
        Button resumeRoute = new Button("Resume route");
        Button pauseRoute = new Button("Pause route");
        BlueBotConnection connection = new BlueBotConnection(new SerialPort("COM3"));

        ListView newRoute = new ListView();
        ListView routeList = new ListView();

        stop.setOnAction(actionEvent -> {
            newRoute.getItems().add("Stop");
            route.add("Stop");
        });

        end.setOnAction(actionEvent -> {
            newRoute.getItems().add("Einde");
            route.add("Einde");
        });

        forward.setOnAction(actionEvent -> {
            newRoute.getItems().add("Forward");
            route.add("Forward");
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
            if (selectedRoute != -1) {
                routeList.getItems().remove(selectedRoute);
            }
        });

        save.setOnAction(actionEvent -> {
            routeList.getItems().add(FXCollections.observableArrayList(route));
            //for (int i = 0; i < route.size(); i++){
            //    System.out.println(route.get(i));
            //}
        });

        executeRoute.setOnAction(event -> {
            connection.writeString("B");

            for (int i = 0; i < route.size(); i++) {
                String state = route.get(i);

                System.out.println(state);
                    switch (state){
                        case "Forward":
                            connection.writeString("w");
                            break;
                        case "Left":
                            connection.writeString("a");
                            break;
                        case "Right":
                            connection.writeString("d");
                            break;
                        case "Stop":
                            connection.writeString("s");
                            break;
                        case "Einde":
                            connection.writeString("e");
                            break;
                    }
            }

            connection.writeString("S");
        });

        cancelRoute.setOnAction(actionEvent -> connection.writeString("C"));

        resumeRoute.setOnAction(actionEvent -> connection.writeString("R"));

        pauseRoute.setOnAction(actionEvent -> connection.writeString("P"));

        HBox buttonBox1 = new HBox();
        buttonBox1.setSpacing(10);
        buttonBox1.getChildren().addAll(forward, left, right, stop, end);

        HBox buttonBox2 = new HBox();
        buttonBox2.setSpacing(10);
        buttonBox2.getChildren().addAll(save, deleteCommand);

        HBox buttonBox3 = new HBox();
        buttonBox3.setSpacing(10);
        buttonBox3.getChildren().addAll(executeRoute, pauseRoute, resumeRoute, cancelRoute);

        VBox chooseRoute = new VBox();
        chooseRoute.setSpacing(20);
        chooseRoute.getChildren().addAll(addCommand, buttonBox1, newRoute, buttonBox2);

        VBox existingRoute = new VBox();
        existingRoute.setSpacing(20);
        existingRoute.getChildren().addAll(savedRoutes, deleteRoute, routeList, buttonBox3);

        HBox routes = new HBox();
        routes.setSpacing(50);
        routes.getChildren().addAll(chooseRoute, existingRoute);

        pane.setCenter(routes);

        return pane;
    }

    private BorderPane createControls() {
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
