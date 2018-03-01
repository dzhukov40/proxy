package ru.doneathome.functional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import ru.doneathome.model.Pipe;

public class MainTableFunctional {

    private TableView<Pipe> mainTable;



    public MainTableFunctional(TableView<Pipe> table) {

        mainTable = table;

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


        mainTable.getColumns().addAll(name, localhostPort, remoteIP, remotePort);
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        mainTable.setEditable(true);

    }


    public void setItems (ObservableList<Pipe> data) {
        mainTable.setItems(data);
    }

    public ObservableList<Pipe> getItems () {
        return mainTable.getItems();
    }




}
