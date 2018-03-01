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
import ru.doneathome.functional.ConfigurationFunctional;
import ru.doneathome.functional.MainTableFunctional;
import ru.doneathome.model.Configuration;
import ru.doneathome.model.Pipe;
import ru.doneathome.model.Profile;

import java.util.List;


public class Main extends Application {

    private static ObservableList<Pipe> data = FXCollections.observableArrayList(
            new Pipe("tort", "9510", "a@example.com", "2301"),
            new Pipe("Doc", "2012", "b@example.com", "2301"),
            new Pipe("Mok", "3456", "c@example.com", "2301")
    );




    // управляем главной табличкой
    private MainTableFunctional mainTableFunctional;

    // управляем конфигурацией
    private ConfigurationFunctional configurationFunctional;



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("forms/mainForm.fxml").openStream());
        primaryStage.setTitle("Proxy");
        primaryStage.setScene(new Scene(root, 850, 500));

        // получаем инстанс контроллера из нашей формы
        MainController mainController = fxmlLoader.getController();
        TableView<Pipe> table = mainController.pipeTable;

        // инициализируем табличку
        mainTableFunctional = new MainTableFunctional(table);
        mainTableFunctional.setItems(data);

        // работаем с конфигурацией
        configurationFunctional = new ConfigurationFunctional();

        Profile profile = new Profile();
        profile.setPipes(data);



        Configuration configuration = new Configuration();
        configuration.addProfile(profile);

        configurationFunctional.writeConfiguration("./tort.txt", configuration);


        Configuration configuration1 = configurationFunctional.readConfiguration("./tort.txt");

        System.out.print(configuration1);





        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(500);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}