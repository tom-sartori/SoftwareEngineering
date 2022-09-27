package exercise1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        // Initialize a list of buttons.
        List<Button> buttonList = new ArrayList<>();
        int numberOfButton = askUserForInt();
        for (int i = 0; i < numberOfButton; i++) {
            Button button = new Button("Button " + i);
            button.setOnMouseClicked( (mouseEvent) -> System.out.println(button.getText()) );
            buttonList.add(button);
        }

        // Lays out controls in a horizontal flow, wrapping nodes when space is needed.
        FlowPane flowPane = new FlowPane(
                label
        );
        flowPane.getChildren().addAll(buttonList);


        Scene scene = new Scene(flowPane, 640, 480);

        // Event listener on key pressed.
        scene.setOnKeyPressed(
                (keyEvent) -> {
                    if (keyEvent.getCode().isDigitKey()) {
                        int buttonIndex = Integer.parseInt(keyEvent.getText());
                        buttonList.get(buttonIndex).requestFocus();
                    }
                });


        // Add style used for the button's color.
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private int askUserForInt() {
        System.out.println("Entrez un chiffre : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
