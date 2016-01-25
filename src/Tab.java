/*
Created by Risto Pärnapuu on 30.04.2015.
*/

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Tab {

    private int tabNumber; // 0, 1, 2, 3, 4, 5 Tab

    private Button calculate; // 0, 1, 2, 3, 4, 5 Tab

    private Pane enteredPane; // 0, 1, 2, 3, 4, 5 Tab
    private Pane enteredPane2;
    private Pane resultPane; // 0, 1, 2, 3, 4, 5 Tab

    private TextField rowCount; // 0, 2 Tab
    private TextField columnCount; // 0, 2 Tab
    private TextField rowCount2; // 0, 2 Tab
    private TextField columnCount2; // 0, 2 Tab
    private TextField rowAndColumnCount; // 1, 3, 4, 5 Tab
    private TextField minorRows; // 2 Tab
    private TextField minorColumns; // 2 Tab
    private TextField multiplier;
    private TextField operationSign;

    private Label equals; // 0, 1, 2, 3, 4, 5 Tab
    private Label sign; // 0, 1 Tab
    private Label minorRowsLabel; // 2 Tab
    private Label minorColumnsLabel; // 2 Tab

    // Constructor for tansposing javafx elements
    public Tab(int tabNumber, Button calculate,
               Pane enteredPane, Pane resultPane, TextField columnCount,
               TextField rowCount, Label equals, Label sign) {
        this.tabNumber = tabNumber;
        this.calculate = calculate;
        this.enteredPane = enteredPane;
        this.resultPane = resultPane;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.equals = equals;
        this.sign = sign;
    }

    // Constructor for reverse Matrix javafx elements
    public Tab(int tabNumber, Button calculate,
               Pane enteredPane, Pane resultPane, TextField rowAndColumnCount, Label equals, Label sign) {
        this.tabNumber = tabNumber;
        this.calculate = calculate;
        this.enteredPane = enteredPane;
        this.resultPane = resultPane;
        this.rowAndColumnCount = rowAndColumnCount;
        this.equals = equals;
        this.sign = sign;
    }

    // Constructor for minor Matrix javafx elements
    public Tab(int tabNumber, Button calculate, Pane enteredPane, Pane resultPane,
               TextField rowCount, TextField columnCount, TextField minorRows, TextField minorColumns, Label equals, Label minorRowsLabel, Label minorColumnsLabel) {
        this.tabNumber = tabNumber;
        this.calculate = calculate;
        this.enteredPane = enteredPane;
        this.resultPane = resultPane;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.minorRows = minorRows;
        this.minorColumns = minorColumns;
        this.equals = equals;
        this.minorRowsLabel = minorRowsLabel;
        this.minorColumnsLabel = minorColumnsLabel;
    }

    // Constructor for Matrix determinant and symmetry javafx elements
    public Tab(int tabNumber, Button calculate,
               Pane enteredPane, Pane resultPane, TextField rowAndColumnCount, Label equals) {
        this.tabNumber = tabNumber;
        this.calculate = calculate;
        this.enteredPane = enteredPane;
        this.resultPane = resultPane;
        this.rowAndColumnCount = rowAndColumnCount;
        this.equals = equals;
    }

    // Constructor for multiplying javafx elements
    public Tab(int tabNumber, Button calculate,
               Pane enteredPane, Pane enteredPane2, Pane resultPane, TextField columnCount,
               TextField rowCount, TextField multiplier, Label equals, Label sign) {
        this.tabNumber = tabNumber;
        this.calculate = calculate;
        this.enteredPane = enteredPane;
        this.enteredPane2 = enteredPane2;
        this.resultPane = resultPane;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.multiplier = multiplier;
        this.equals = equals;
        this.sign = sign;
    }

    // Constructor for adding and subtracting javafx elements
    public Tab(int tabNumber, Button calculate,
               Pane enteredPane, Pane enteredPane2, Pane resultPane, TextField columnCount,
               TextField rowCount, Label equals, TextField operationSign) {
        this.tabNumber = tabNumber;
        this.calculate = calculate;
        this.enteredPane = enteredPane;
        this.enteredPane2 = enteredPane2;
        this.resultPane = resultPane;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.equals = equals;
        this.operationSign = operationSign;
    }

    // Constructor for multiplying javafx elements
    public Tab(int tabNumber, Button calculate,
               Pane enteredPane, Pane enteredPane2, Pane resultPane, TextField columnCount,
               TextField rowCount, TextField rowCount2, TextField columnCount2, Label equals, Label operationSign) {
        this.tabNumber = tabNumber;
        this.calculate = calculate;
        this.enteredPane = enteredPane;
        this.enteredPane2 = enteredPane2;
        this.resultPane = resultPane;
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.rowCount2 = rowCount2;
        this.columnCount2 = columnCount2;
        this.equals = equals;
        this.sign = operationSign;
    }

    public TextField getOperationSign() {
        return operationSign;
    }

    public TextField getRowCount2() {
        return rowCount2;
    }

    public TextField getColumnCount2() {
        return columnCount2;
    }

    public Label getMinorRowsLabel() {
        return minorRowsLabel;
    }

    public Label getMinorColumnsLabel() {
        return minorColumnsLabel;
    }

    public int getTabNumber() {
        return tabNumber;
    }

    public Button getCalculate() {
        return calculate;
    }

    public Pane getEnteredPane() {
        return enteredPane;
    }

    public Pane getEnteredPane2() {
        return enteredPane2;
    }

    public Pane getResultPane() {
        return resultPane;
    }

    public TextField getMinorRows() {
        return minorRows;
    }

    public TextField getMinorColumns() {
        return minorColumns;
    }

    public TextField getRowCount() {
        if (this.tabNumber == 0 || this.tabNumber == 2 || this.tabNumber == 5 || this.tabNumber == 6 || this.tabNumber == 7){
            return this.rowCount;
        }else if(this.tabNumber == 1 || this.tabNumber == 3 || this.tabNumber == 4){
            return this.rowAndColumnCount;
        }
        else{
            return null;
        }
    }

    public TextField getColumnCount() {
        if (this.tabNumber == 0 || this.tabNumber == 2 || this.tabNumber == 5 || this.tabNumber == 6 || this.tabNumber == 7){
            return this.columnCount;
        }else if(this.tabNumber == 1 || this.tabNumber == 3 || this.tabNumber == 4){
            return this.rowAndColumnCount;
        }
        else{
            return null;
        }
    }

    public TextField getMultiplier() {
        return multiplier;
    }

    public Label getEquals() {
        return equals;
    }

    public Label getSign() {
        return sign;
    }

    public Integer[] getMinorRowsValues() {
        String[] rowsString = minorRows.getText().split(" ");
        Integer[] rows = new Integer[rowsString.length];
        for (int i = 0; i < rowsString.length; i++){
            rows[i] = Integer.parseInt(rowsString[i]);
        }
        return rows;
    }

    public Integer[] getMinorColumnsValues() {
        String[] columnString = minorColumns.getText().split(" ");
        Integer[] columns = new Integer[columnString.length];
        for (int i = 0; i < columnString.length; i++){
            columns[i] = Integer.parseInt(columnString[i]);
        }
        return columns;
    }
}