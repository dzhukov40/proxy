package ru.doneathome;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.doneathome.forms.MainController;
import ru.doneathome.functional.MainTableFunctional;
import ru.doneathome.model.Pipe;

public class Main extends Application {






    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("forms/mainForm.fxml").openStream());
        primaryStage.setTitle("Proxy");
        primaryStage.setScene(new Scene(root, 850, 500));

        // получаем инстанс контроллера из нашей формы
        MainController mainController = fxmlLoader.getController();

        TableView<Pipe> table = mainController.pipeTable;

        MainTableFunctional.initTable(table);










        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(500);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}