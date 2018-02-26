package ru.doneathome.functional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import ru.doneathome.model.Pipe;

public class MainTableFunctional {

    private static ObservableList<Pipe> data = FXCollections.observableArrayList(
            new Pipe("tort", "9510", "a@example.com", "2301"),
            new Pipe("Doc", "2012", "b@example.com", "2301"),
            new Pipe("Mok", "3456", "c@example.com", "2301")
    );

    public static void initTable(TableView<Pipe> table) {

        // создаем нашы столбцы для таблицы
        TableColumn<Pipe, String>  name = new TableColumn("Name");
        TableColumn<Pipe, String>  localhostPort = new TableColumn("Localhost Port");
        TableColumn<Pipe, String>  remoteIP = new TableColumn("Remote IP");
        TableColumn<Pipe, String>  remotePort = new TableColumn("Remote Port");

        name.setCellValueFactory(new PropertyValueFactory("name"));
        localhostPort.setCellValueFactory(new PropertyValueFactory("localhostPort"));
        remoteIP.setCellValueFactory(new PropertyValueFactory("remoteIP"));
        remotePort.setCellValueFactory(new PropertyValueFactory("remotePort"));


        name.setCellFactory(TextFieldTableCell.<Pipe>forTableColumn());
        localhostPort.setCellFactory(TextFieldTableCell.<Pipe>forTableColumn());
        remoteIP.setCellFactory(TextFieldTableCell.<Pipe>forTableColumn());
        remotePort.setCellFactory(TextFieldTableCell.<Pipe>forTableColumn());

        // ставим обработчики на события изменения данных в столбцах таблицы
        name.setOnEditCommit(
                (TableColumn.CellEditEvent<Pipe, String> t) -> {
                     ((Pipe) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                });

        localhostPort.setOnEditCommit(
                (TableColumn.CellEditEvent<Pipe, String> t) -> {
                    ((Pipe) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLocalhostPort(t.getNewValue());
                });

        remoteIP.setOnEditCommit(
                (TableColumn.CellEditEvent<Pipe, String> t) -> {
                    ((Pipe) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setRemoteIP(t.getNewValue());
                });

        remotePort.setOnEditCommit(
                (TableColumn.CellEditEvent<Pipe, String> t) -> {
                    ((Pipe) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setRemotePort(t.getNewValue());
                });



        name.setMinWidth(10);
        localhostPort.setMinWidth(10);
        remoteIP.setMinWidth(10);
        remotePort.setMinWidth(10);


        table.getColumns().addAll(name, localhostPort, remoteIP, remotePort);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.setEditable(true);
        table.setItems(data);


    }
}
