package bluebotclient.backend;
import bluebotclient.frontend.Gui;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Program program = new Program();
        Application.launch(Gui.class);
        program.run();

    }
}
