package ru.doneathome.forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ru.doneathome.model.Pipe;

public class MainController {

    @FXML
    public BorderPane rootPane;

    @FXML
    public Button createPipe;

    @FXML
    public TableView<Pipe> pipeTable;



    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {

    }

    @FXML
    public void doCreate(ActionEvent actionEvent) {
    }
}
