//Created by Risto Pärnapuu and Mart Mägi on 12.03.2015.

import java.util.Random;

public class Matrix {
    private int rows;
    private int columns;
    private double[][] mat;

    public Matrix (double[][] mat) {
        this.rows = mat.length;
        this.columns = mat[0].length;
        this.mat = mat;
    }

    public Matrix(int rows, int columns) {
        this(new double[rows][columns]);
    }

    public Matrix (int rows, int columns, int min, int max) {
        this(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Random rand = new Random();
                int newValue = rand.nextInt((max-min) + 1) + min;
                setValueAt(i, j, newValue);
            }
        }
    }

    public int getrows() {
        return rows;
    }

    public int getcolumns() {
        return columns;
    }

    public String printMatrix() {
        String fullMatrix= "";
        int maxLength = 1;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                String nrString = String.valueOf(getValueAt(i, j));
                int nrLength = nrString.length();
                if (nrLength > maxLength) {
                    maxLength = nrLength;
                }
            }
        }
        for (int x = 0; x < this.rows; x++) {
            for (int y = 0; y < this.columns; y++) {
                String nrString = String.valueOf(getValueAt(x, y));
                int nrLength = nrString.length();
                int pikkus = maxLength - nrLength;
                if (pikkus > 0) pikkus += pikkus;
                for (int z = 0; z <= pikkus/2 + 2; z++) {
                    fullMatrix += " ";
                }
                fullMatrix += getValueAt(x, y);
                if (pikkus > 0) pikkus -= pikkus;
                for (int z = 0; z <= pikkus/2 + 2; z++) {
                    fullMatrix += " ";
                }
            }
            fullMatrix += "\n";
        }
        return fullMatrix;
    }

    public void setValueAt(int row, int column, double value){
        this.mat[row][column] = value;
    }

    public double getValueAt(int row, int column){
        return mat[row][column];
    }

    @Override
    public String toString() {
        return printMatrix();
    }
}