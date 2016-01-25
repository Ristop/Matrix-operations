/*
Created by Risto Pärnapuu on 30.04.2015.
*/

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Controller {

    private ArrayList<ArrayList<TextField>> matrixElementFields;
    private ArrayList<ArrayList<TextField>> matrixElementFields2;

    @FXML private TabPane Box; // Main container

    // First tab (transpose)
    @FXML private Button calculateButtonTab1;
    @FXML private Pane enteredPaneTab1;
    @FXML private Pane answerPaneTab1;
    @FXML private TextField rowCountTab1;
    @FXML private TextField columnCountTab1;
    @FXML private Label equalsTab1;
    @FXML private Label transposeSignTab1;

    // Second tab (inverse)
    @FXML private Button calculateButtonTab2;
    @FXML private Pane enteredPaneTab2;
    @FXML private Pane answerPaneTab2;
    @FXML private TextField rowAndColumnCountTab2;
    @FXML private Label equalsTab2;
    @FXML private Label reverseSignTab2;

    // Third tab (getMinor)
    @FXML private Button calculateButtonTab3;
    @FXML private Pane enteredPaneTab3;
    @FXML private Pane answerPaneTab3;
    @FXML private TextField rowCountTab3;
    @FXML private TextField columnCountTab3;
    @FXML private Label equalsTab3;
    @FXML private Label minorRowsLabelTab3;
    @FXML private Label minorColumnsLabelTab3;
    @FXML private TextField minorRowsTab3;
    @FXML private TextField minorColumnsTab3;

    // Fourth tab (determinant)
    @FXML private Button calculateButtonTab4;
    @FXML private Pane enteredPaneTab4;
    @FXML private Pane answerPaneTab4;
    @FXML private TextField rowAndColumnCountTab4;
    @FXML private Label equalsTab4;

    // Fourth tab (isSymmetric)
    @FXML private Button calculateButtonTab5;
    @FXML private Pane enteredPaneTab5;
    @FXML private Pane answerPaneTab5;
    @FXML private TextField rowAndColumnCountTab5;
    @FXML private Label equalsTab5;

    // Fifth tab (multiplyWithNumber)
    @FXML private Button calculateButtonTab6;
    @FXML private Pane enteredPaneTab6;
    @FXML private Pane entered2PaneTab6;
    @FXML private Pane answerPaneTab6;
    @FXML private TextField rowCountTab6;
    @FXML private TextField columnCountTab6;
    @FXML private TextField multiplierTab6;
    @FXML private Label equalsTab6;
    @FXML private Label multiplySignTab6;

    // Sixth tab (add)
    @FXML private Button calculateButtonTab7;
    @FXML private Pane enteredPaneTab7;
    @FXML private Pane entered2PaneTab7;
    @FXML private Pane answerPaneTab7;
    @FXML private TextField rowCountTab7;
    @FXML private TextField columnCountTab7;
    @FXML private TextField operationSighTab7;
    @FXML private Label equalsTab7;

    // Seventh tab (multiply)
    @FXML private Button calculateButtonTab8;
    @FXML private Pane enteredPaneTab8;
    @FXML private Pane entered2PaneTab8;
    @FXML private Pane answerPaneTab8;
    @FXML private TextField rowCountTab8;
    @FXML private TextField columnCountTab8;
    @FXML private TextField rowCount2Tab8;
    @FXML private TextField columnCount2Tab8;
    @FXML private Label equalsTab8;
    @FXML private Label multiplySignTab8;

    // Eighth tab (multiply image with matrix)
    @FXML private Pane imagePaneTab9;
    @FXML private TextField ImageMatrix1Tab9;
    @FXML private TextField ImageMatrix2Tab9;
    @FXML private TextField ImageMatrix3Tab9;
    @FXML private TextField ImageMatrix4Tab9;
    @FXML private CheckBox showAxisCBoxTab9;

    @FXML private void showOriginalImage(){
        double[][] multiplier = {{1, 0},
                                {0, 1}};
        ImageMatrix1Tab9.setText("1");
        ImageMatrix2Tab9.setText("0");
        ImageMatrix3Tab9.setText("0");
        ImageMatrix4Tab9.setText("1");
        showAxisCBoxTab9.setSelected(true);
        ImageView a = new ImageView(drawImage(multiplier, showAxisCBoxTab9.isSelected()));
        a.setLayoutY(5);
        a.setLayoutX(5);
        imagePaneTab9.getChildren().add(a);
    }

    @FXML private void multiplyImageWithMatrix(){
        double[][] multiplier = {{Double.parseDouble(ImageMatrix1Tab9.getText()), Double.parseDouble(ImageMatrix2Tab9.getText())},
                                {Double.parseDouble(ImageMatrix3Tab9.getText()), Double.parseDouble(ImageMatrix4Tab9.getText())}};

        ImageView a = new ImageView(drawImage(multiplier, showAxisCBoxTab9.isSelected()));
        a.setLayoutY(5);
        a.setLayoutX(5);
        imagePaneTab9.getChildren().add(a);
    }

    public static WritableImage drawImage(double[][] multiplier, boolean showAxis){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/Earth.jpg").toURI().toURL());
        } catch (IOException e) {
            System.out.println("Pilti ei leitud");
        }
        int halfLength = 330;
        int color;

        double matrix[][] = {{multiplier[0][0]/4.0, multiplier[0][1]/4.0},
                {multiplier[1][0]/4.0, multiplier[1][1]/4.0}};

        BufferedImage generatedImage = new BufferedImage(660, 660, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) generatedImage.getGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, img.getWidth()*4+20, img.getWidth()*4+20);
        g.dispose();


        for (int i = 0;i < img.getWidth(); i++){ // All rows
            for (int j = 0;j < img.getHeight(); j++){ // All columns
                if(i >= img.getWidth()/2 && j <= img.getHeight()/2) { // First quadrant I
                    // Convert to coordinates
                    int a = i - (img.getWidth()/2);
                    int b = (img.getHeight()/2)-j;
                    // Multiply by matrix
                    int x = (int)Math.round(((a * matrix[0][0]) + (b * matrix[0][1])));
                    int y = (int)Math.round(((a*matrix[1][0]) + (b*matrix[1][1])));
                    // Draw new pixel according to new coordinates
                    try {
                        if (matrix[0][0] == matrix[0][1] && matrix[1][0] == matrix[1][1] && matrix[0][0] == matrix[1][0]){
                            color = 0;
                        }else{
                            color = img.getRGB(i, j);
                        }
                        if (x >= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x >= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength + -y, color);
                        } else if (x <= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x <= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        }
                    }catch (Exception e){}
                } else if(i <= img.getWidth()/2 && j <= img.getHeight()/2) { // Second quadrant II
                    // Convert to coordinates
                    int a = -((img.getWidth()/2)-i);
                    int b = (img.getHeight()/2)-j;
                    // Multiply by matrix
                    int x = (int)Math.round(((a*matrix[0][0]) + (b*matrix[0][1])));
                    int y = (int)Math.round(((a*matrix[1][0]) + (b*matrix[1][1])));
                    // Draw new pixel according to new coordinates
                    try {
                        if (matrix[0][0] == matrix[0][1] && matrix[1][0] == matrix[1][1] && matrix[0][0] == matrix[1][0]){
                            color = 0;
                        }else{
                            color = img.getRGB(i, j);
                        }
                        if (x >= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x >= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength + -y, color);
                        } else if (x <= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x <= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        }
                    }catch (Exception e){}
                } else if(i <= img.getWidth()/2 && j >= img.getHeight()/2) { // Third quadrant II
                    // Convert to coordinates
                    int a = -((img.getWidth()/2)-i);
                    int b = (img.getHeight()/2)-j;
                    // Multiply by matrix
                    int x = (int)Math.round(((a*matrix[0][0]) + (b*matrix[0][1])));
                    int y = (int)Math.round(((a*matrix[1][0]) + (b*matrix[1][1])));
                    // Draw new pixel according to new coordinates
                    try {
                        if (matrix[0][0] == matrix[0][1] && matrix[1][0] == matrix[1][1] && matrix[0][0] == matrix[1][0]){
                            color = 0;
                        }else{
                            color = img.getRGB(i, j);
                        }
                        if (x >= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x >= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength + -y, color);
                        } else if (x <= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x <= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        }
                    }catch (Exception e){}
                } else if(i >= img.getWidth()/2 && j >= img.getHeight()/2) { // Fourth quadrant III
                    // Convert to coordinates
                    int a = i - (img.getWidth()/2);
                    int b = (img.getHeight()/2)-j;
                    // Multiply by matrix
                    int x = (int)Math.round(((a*matrix[0][0]) + (b*matrix[0][1])));
                    int y = (int)Math.round(((a*matrix[1][0]) + (b*matrix[1][1])));
                    // Draw new pixel according to new coordinates
                    try {
                        if (matrix[0][0] == matrix[0][1] && matrix[1][0] == matrix[1][1] && matrix[0][0] == matrix[1][0]){
                            color = 0;
                        }else{
                            color = img.getRGB(i, j);
                        }
                        if (x >= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x >= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength + -y, color);
                        } else if (x <= 0 && y >= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        } else if (x <= 0 && y <= 0) {
                            generatedImage.setRGB(halfLength + x, halfLength - y, color);
                        }
                    }catch (Exception e){}
                }
            }
        }
        // Show x-y axis
        if (showAxis){
            try {
                for (int i = 0; i < img.getWidth()*4+20; i++){
                    generatedImage.setRGB(i, halfLength, 0);
                    generatedImage.setRGB(halfLength, i, 0);
                }
            }catch (Exception e){}
        }

        WritableImage wr = null;
        if (generatedImage != null) {
            wr = new WritableImage(generatedImage.getWidth(), generatedImage.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < generatedImage.getWidth(); x++) {
                for (int y = 0; y < generatedImage.getHeight(); y++) {
                    pw.setArgb(x, y, generatedImage.getRGB(x, y));
                }
            }
        }

        return wr;
    }

    @FXML private void clearOldContent(){ // Clear old stuff when selecting a tab
        Tab elements = getTabElements(Box.getSelectionModel().getSelectedIndex());
        assert elements != null;
        int tabNum = elements.getTabNumber();

        elements.getEnteredPane().getChildren().clear();
        elements.getResultPane().getChildren().clear();
        elements.getEquals().setVisible(false);
        elements.getCalculate().setVisible(false);

        switch (tabNum) {
            case 0:
            case 1:
                elements.getSign().setVisible(false);
                break;
            case 2:
                elements.getMinorRows().setVisible(false);
                elements.getMinorColumns().setVisible(false);
                elements.getMinorColumnsLabel().setVisible(false);
                elements.getMinorRowsLabel().setVisible(false);
                break;
            case 5:
                elements.getMultiplier().clear();
                elements.getMultiplier().setVisible(false);
                elements.getSign().setVisible(false);
                break;
            case 6:
                elements.getEnteredPane2().getChildren().clear();
                elements.getOperationSign().clear();
                elements.getOperationSign().setVisible(false);
                break;
            case 7:
                elements.getEnteredPane2().getChildren().clear();
                elements.getSign().setVisible(false);
                break;
        }

    }

    @FXML private void onEnterClick() { // Method for showing matrix with correct dimensions
        // Get corresponding tab elements

        Tab elements = getTabElements(Box.getSelectionModel().getSelectedIndex());
        matrixElementFields = new ArrayList<ArrayList<TextField>>();
        matrixElementFields2 = new ArrayList<ArrayList<TextField>>();
        int rows, columns;
        assert elements != null;
        int tabNum = elements.getTabNumber();
        // -------------------------------------------------------------------------------------------------------------

        // Try to get first input matrix dimensions
        try {
            rows = Integer.parseInt(elements.getRowCount().getText());
            columns = Integer.parseInt(elements.getColumnCount().getText());
            if (rows > 10 || rows < 1 || columns > 10 || columns < 1){
                throw new IllegalArgumentException("Wrong row or column count");
            }
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            throw e;
        }
        // -------------------------------------------------------------------------------------------------------------

        // Show and hide elements
        elements.getCalculate().setVisible(true);
        elements.getEquals().setVisible(false);
        elements.getEnteredPane().getChildren().clear();
        elements.getResultPane().getChildren().clear();
        generateInputMatrix(matrixElementFields, elements.getEnteredPane(), rows, columns); // Generate input matrix

        switch (tabNum) {
            case 0:
            case 1:  // Special cases for transpose and inverse tab
                elements.getSign().setVisible(false);
                break;
            case 2:  // Special cases for getMinor tab
                elements.getMinorColumns().setVisible(true);
                elements.getMinorColumns().clear();
                elements.getMinorRows().setVisible(true);
                elements.getMinorRows().clear();
                elements.getMinorRowsLabel().setVisible(true);
                elements.getMinorColumnsLabel().setVisible(true);
                break;
            case 5:  // Special cases for multiply with number tab
                elements.getMultiplier().clear();
                elements.getMultiplier().setVisible(true);
                elements.getMultiplier().setLayoutY((rows * 35 / 2) - 15);
                elements.getMultiplier().setMaxWidth(50);
                elements.getMultiplier().setMinWidth(50);
                elements.getSign().setVisible(true);
                elements.getSign().setLayoutY((rows * 35 / 2) - 12);
                break;
            case 6:  // Special cases for adding and subtracting
                elements.getEnteredPane2().getChildren().clear();
                generateInputMatrix(matrixElementFields2, elements.getEnteredPane2(), rows, columns);
                elements.getOperationSign().clear();
                elements.getOperationSign().setVisible(true);
                elements.getOperationSign().setMaxWidth(35);
                elements.getOperationSign().setMinWidth(35);
                elements.getOperationSign().setPromptText("+/-");
                elements.getOperationSign().setLayoutY((rows * 35 / 2) - 17);
                break;
            case 7:
                int rows2;
                int columns2;
                try {
                    rows2 = Integer.parseInt(elements.getRowCount2().getText());
                    columns2 = Integer.parseInt(elements.getColumnCount2().getText());
                    if (rows2 > 10 || rows2 < 1 || columns2 > 10 || columns2 < 1) {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception err) {
                    throw new IllegalArgumentException("Väär sisend!");
                }
                elements.getEnteredPane2().getChildren().clear();
                generateInputMatrix(matrixElementFields2, elements.getEnteredPane2(), rows2, columns2);
                elements.getSign().setVisible(true);
                elements.getSign().setLayoutY((rows * 35 / 2) - 17);
                break;
        }
        // -------------------------------------------------------------------------------------------------------------
    }

    public void generateInputMatrix(ArrayList<ArrayList<TextField>> fields, Pane enteredPane, int rows, int columns){
        int height = 0;
        for (int i = 0; i < rows; i++) {
            int width = 0;
            ArrayList<TextField> row = new ArrayList<TextField>();
            for (int j = 0; j < columns; j++) {
                TextField box = new TextField();
                box.setPromptText("(" + (i+1) + "," + (j+1) + ")");
                box.setMaxWidth(50);
                box.setMinWidth(50);
                box.setLayoutX(width);
                box.setLayoutY(height);
                row.add(box);
                enteredPane.getChildren().add(box);
                width += 55;
            }
            fields.add(row);
            height += 35;
        }
    }

    @FXML private void onCalculateClick(){ // Method for showing result matrix
        // Get corresponding tab elements
        Tab elements = getTabElements(Box.getSelectionModel().getSelectedIndex());

        // Get current tab index
        assert elements != null;
        int tabNum = elements.getTabNumber();

        // Clear old content
        elements.getResultPane().getChildren().clear();
        elements.getEquals().setVisible(false);

        // Check if entered matrices are legal
        checkInputMatrixLegality(matrixElementFields);
        if (tabNum == 6 || tabNum == 7){ checkInputMatrixLegality(matrixElementFields2); }

        // Get first input matrix
        double[][] inputMatrix = getInputMatrix(matrixElementFields);
        Matrix s = new Matrix(inputMatrix);

        // Show new content
        elements.getEquals().setVisible(true);
        elements.getEquals().setLayoutY((matrixElementFields.size() * 35 / 2) - 12);

        Operations op = new Operations();
        Matrix answerMatrix;
        // -------------------------------------------------------------------------------------------------------------
        switch (tabNum) {
            case 0: // transpose
                answerMatrix = op.transpose(s);
                elements.getSign().setVisible(true);
                setAnswerMatrix(answerMatrix, elements.getResultPane());
                break;
            case 1: // inverse
                try {
                    answerMatrix = op.inverse(s);
                    setAnswerMatrix(answerMatrix, elements.getResultPane());
                } catch (IllegalArgumentException e) {
                    setAnswerField("Reverse matrix doesn't exist", elements.getResultPane(), (matrixElementFields.size() * 35 / 2) - 12);
                }
                elements.getSign().setVisible(true);
                break;
            case 2: // minor
                answerMatrix = op.getMinor(s, elements.getMinorRowsValues(), elements.getMinorColumnsValues());
                setAnswerMatrix(answerMatrix, elements.getResultPane());
                break;
            case 3: // determinant
                setAnswerField(Double.toString(op.determinant(s)), elements.getResultPane(), (matrixElementFields.size() * 35 / 2) - 12);
                break;
            case 4: // symmetry
                String answer;
                if (op.isSymmetric(s) && op.isAntiSymmetric(s)) {
                    answer = "Is symmetric and antisymmetric";
                } else if (op.isSymmetric(s)) {
                    answer = "Is symmetric";
                } else if (op.isAntiSymmetric(s)) {
                    answer = "Is antisymmetric";
                } else {
                    answer = "Not symmetric or antisymmetric";
                }
                setAnswerField(answer, elements.getResultPane(), (matrixElementFields.size() * 35 / 2) - 12);
                break;
            case 5: // multiply with number
                answerMatrix = op.multiplyWithNumber(s, Double.parseDouble(elements.getMultiplier().getText().replace(",", ".")));
                setAnswerMatrix(answerMatrix, elements.getResultPane());
                break;
            case 6: { // add/subtract
                // Get second entered matrix
                double[][] inputMatrix2 = getInputMatrix(matrixElementFields2);
                Matrix s2 = new Matrix(inputMatrix2);

                String sign = elements.getOperationSign().getText();
                switch (sign) {
                    case "+":
                        elements.getOperationSign().setStyle("-fx-control-inner-background: white;");
                        answerMatrix = op.add(s, s2);
                        break;
                    case "-":
                        elements.getOperationSign().setStyle("-fx-control-inner-background: white;");
                        answerMatrix = op.subtract(s, s2);
                        break;
                    default:
                        elements.getOperationSign().setStyle("-fx-control-inner-background: red;");
                        throw new IllegalArgumentException("Wrong operation sign (use + or -)");
                }
                setAnswerMatrix(answerMatrix, elements.getResultPane());
                break;
            }
            case 7: { // multiply
                // Get second entered matrix
                double[][] inputMatrix2 = getInputMatrix(matrixElementFields2);
                Matrix s2 = new Matrix(inputMatrix2);
                answerMatrix = op.multiply(s, s2);
                setAnswerMatrix(answerMatrix, elements.getResultPane());
                break;
            }
        }
    }

    public void checkInputMatrixLegality(ArrayList<ArrayList<TextField>> fields){
        int rows = fields.size();
        int columns = fields.get(0).size();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                try{
                    Double.parseDouble(fields.get(i).get(j).getText());
                    fields.get(i).get(j).setStyle("-fx-control-inner-background: white;");
                }catch (NumberFormatException e){
                    fields.get(i).get(j).setStyle("-fx-control-inner-background: red;");
                }
            }
        }
    }

    public double[][] getInputMatrix(ArrayList<ArrayList<TextField>> fields){
        int rows = fields.size();
        int columns = fields.get(0).size();
        double[][] inputMatrix = new double[rows][columns];
        try{
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < columns; j++){
                    inputMatrix[i][j] = Double.parseDouble(fields.get(i).get(j).getText());
                }
            }
        }
        catch (Exception err){
            throw new IllegalArgumentException("Illegal input");
        }
        return inputMatrix;
    }

    private void setAnswerField(String result, Pane resultPane, double middle){ // Matirx type answer
        Label answer = new Label(result);
        answer.setLayoutY(middle);
        resultPane.getChildren().add(answer);
    }

    private void setAnswerMatrix(Matrix result, Pane resultPane){ // Field type answer
        int height = 0;
        for (int i = 0; i < result.getrows(); i++) {
            int width = 0;
            for (int j = 0; j < result.getcolumns(); j++) {
                TextField answer = new TextField();
                answer.setText(String.valueOf(result.getValueAt(i, j)));
                answer.setEditable(false);
                answer.setMaxWidth(50);
                answer.setMinWidth(50);
                answer.setLayoutX(width);
                answer.setLayoutY(height);
                resultPane.getChildren().add(answer);
                width += 55;
            }
            height += 35;
        }
    }

    @FXML public void handleEnterPressed(){
        Tab elements = getTabElements(Box.getSelectionModel().getSelectedIndex());
        assert elements != null;
        elements.getRowCount2().setText(elements.getColumnCount().getText());
    }

    @FXML public void handleEnterPressed2(){
        Tab elements = getTabElements(Box.getSelectionModel().getSelectedIndex());
        assert elements != null;
        elements.getColumnCount().setText(elements.getRowCount2().getText());
    }

    public Tab getTabElements(int tab) {
        if (tab == 0){
            return new Tab(tab, calculateButtonTab1,
                    enteredPaneTab1, answerPaneTab1,
                    columnCountTab1, rowCountTab1,
                    equalsTab1, transposeSignTab1);
        }else if(tab == 1) {
            return new Tab(tab, calculateButtonTab2,
                    enteredPaneTab2, answerPaneTab2, rowAndColumnCountTab2,
                    equalsTab2, reverseSignTab2);
        }else if (tab == 2){
            return new Tab(tab, calculateButtonTab3,
                    enteredPaneTab3, answerPaneTab3,
                    rowCountTab3, columnCountTab3, minorRowsTab3, minorColumnsTab3,
                    equalsTab3, minorRowsLabelTab3, minorColumnsLabelTab3);
        }else if(tab == 3) {
            return new Tab(tab, calculateButtonTab4,
                    enteredPaneTab4, answerPaneTab4, rowAndColumnCountTab4,
                    equalsTab4);
        }else if(tab == 4) {
            return new Tab(tab, calculateButtonTab5,
                    enteredPaneTab5, answerPaneTab5, rowAndColumnCountTab5,
                    equalsTab5);
        }else if(tab == 5) {
            return new Tab(tab, calculateButtonTab6,
                    enteredPaneTab6, entered2PaneTab6,
                    answerPaneTab6, columnCountTab6,
                    rowCountTab6, multiplierTab6, equalsTab6, multiplySignTab6);
        }
        else if(tab == 6) {
            return new Tab(tab, calculateButtonTab7,
                    enteredPaneTab7, entered2PaneTab7,
                    answerPaneTab7, columnCountTab7,
                    rowCountTab7, equalsTab7, operationSighTab7);
        }
        else if(tab == 7) {
            return new Tab(tab, calculateButtonTab8,
                    enteredPaneTab8, entered2PaneTab8,
                    answerPaneTab8, columnCountTab8,
                    rowCountTab8, rowCount2Tab8, columnCount2Tab8, equalsTab8, multiplySignTab8);
        }else {
            return null;
        }
    }
}