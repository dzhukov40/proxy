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



    }


    public void setItems (ObservableList<Pipe> data) {
        mainTable.setItems(data);
    }

    public ObservableList<Pipe> getItems () {
        return mainTable.getItems();
    }




}
