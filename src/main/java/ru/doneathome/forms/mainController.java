package ru.doneathome.forms;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class mainController {
    public Label helloWorld;

    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");
    }
}
