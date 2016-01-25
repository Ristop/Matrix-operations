//Created by Risto Pärnapuu and Mart Mägi on 12.03.2015.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operations {

    public Matrix add(Matrix mat1, Matrix mat2) {
        if (mat1.getrows() != mat2.getrows() || mat1.getcolumns() != mat2.getcolumns()) {
            throw new IllegalArgumentException("Matrices must have same number of rows and columns");
        }
        Matrix result = new Matrix(mat1.getrows(), mat1.getcolumns());
        for (int i = 0; i < mat1.getrows(); i++) {
            for (int j = 0; j < mat1.getcolumns(); j++) {
                result.setValueAt(i, j, mat1.getValueAt(i, j) + mat2.getValueAt(i, j));
            }
        }
        return result;
    }

    public Matrix add(Matrix[] mat) {
        if (mat.length == 0 || mat.length == 1) {
            throw new IllegalArgumentException("No matrices entered");
        }
        Matrix result = add(mat[0], mat[1]);
        for (int i = 2; i < mat.length; i++) {
            result = add(result, mat[i]);
        }
        return result;
    }

    public Matrix subtract(Matrix mat1, Matrix mat2) {
        if (mat1.getrows() != mat2.getrows() || mat1.getcolumns() != mat2.getcolumns()) {
            throw new IllegalArgumentException("Matrices must have same number of rows and columns");
        }
        Matrix result = new Matrix(mat1.getrows(), mat1.getcolumns());
        for (int i = 0; i < mat1.getrows(); i++) {
            for (int j = 0; j < mat1.getcolumns(); j++) {
                result.setValueAt(i, j, mat1.getValueAt(i, j) - mat2.getValueAt(i, j));
            }
        }
        return result;
    }

    public Matrix subtract(Matrix[] mat) {
        if (mat.length == 0 || mat.length == 1) {
            throw new IllegalArgumentException("No matrices entered");
        }
        Matrix result = subtract(mat[0], mat[1]);
        for (int i = 2; i < mat.length; i++) {
            result = subtract(result, mat[i]);
        }
        return result;
    }

    public Matrix multiply(Matrix mat1, Matrix mat2) {
        Matrix result = new Matrix(mat1.getrows(), mat2.getcolumns());

        if (mat1.getcolumns() != mat2.getrows()) {
            throw new IllegalArgumentException("Can't multiply those matrices");
        }

        for (int i = 0; i < result.getrows(); ++i) {
            for (int j = 0; j < result.getcolumns(); ++j) {
                result.setValueAt(i, 0, 0);
            }
        }

        for (int i = 0; i < mat1.getrows(); ++i) {
            for (int j = 0; j < mat2.getcolumns(); ++j) {
                for (int k = 0; k < mat1.getcolumns(); ++k) {
                    result.setValueAt(i, j, result.getValueAt(i, j) + (mat1.getValueAt(i, k) * mat2.getValueAt(k, j)));
                }
            }
        }

        return result;
    }

    public Matrix multiply(Matrix[] mat) {
        Matrix result;
        if (mat[0].getcolumns() == mat[1].getrows()) {
            result = multiply(mat[0], mat[1]);
            for (int i = 2; i < mat.length; i++) {
                if (result.getcolumns() == mat[i].getrows()) {
                    result = multiply(result, mat[i]);
                } else {
                    throw new IllegalArgumentException("Illegal input");
                }
            }
        } else {
            throw new IllegalArgumentException("Illegal input");
        }
        return result;
    }

    public Matrix multiplyWithNumber(Matrix mat, double k) {
        Matrix result = new Matrix(mat.getrows(), mat.getcolumns());
        for (int i = 0; i < mat.getrows(); i++) {
            for (int j = 0; j < mat.getcolumns(); j++) {
                result.setValueAt(i, j, k * mat.getValueAt(i, j));
            }
        }

        return result;
    }

    public Matrix transpose(Matrix mat) {
        Matrix result = new Matrix(mat.getcolumns(), mat.getrows());
        for (int i = 0; i < mat.getrows(); i++) {
            for (int j = 0; j < mat.getcolumns(); j++) {
                result.setValueAt(j, i, mat.getValueAt(i, j));
            }
        }

        return result;
    }

    public Matrix opposite(Matrix mat) {
        Matrix result = new Matrix(mat.getrows(), mat.getcolumns());
        for (int i = 0; i < mat.getrows(); i++) {
            for (int j = 0; j < mat.getcolumns(); j++) {
                result.setValueAt(i, j, -mat.getValueAt(i, j));
            }
        }

        return result;
    }

    public Matrix getMinor(Matrix mat, Integer[] rows, Integer[] columns) {
        Matrix minor = new Matrix(mat.getrows() - rows.length, mat.getcolumns() - columns.length);
        List<Integer> allRows = new ArrayList<Integer>();
        List<Integer> allColmuns = new ArrayList<Integer>();
        // Save all rows and columns that are needed
        for (int i = 0; i < mat.getrows(); i++) {
            if (!Arrays.asList(rows).contains(i + 1)) {
                allRows.add(i + 1);
            }
        }
        for (int i = 0; i < mat.getcolumns(); i++) {
            if (!Arrays.asList(columns).contains(i + 1)) {
                allColmuns.add(i + 1);
            }
        }

        int minorRow = 0;
        int minorColumn = 0;
        // Make a new matrix with element in those rows and columns
        for (Integer row : allRows) {
            for (Integer column : allColmuns) {
                minor.setValueAt(minorRow, minorColumn, mat.getValueAt(row - 1, column - 1));
                minorColumn++;
            }
            minorColumn = 0;
            minorRow++;
        }
        return minor;
    }

    public Matrix inverse(Matrix mat) {
        if (!isSquareMatrix(mat) || determinant(mat) == 0) {
            throw new IllegalArgumentException("Inverse matrix not doesn't exist ");
        }
        double det = 1 / determinant(mat);
        Matrix reverse = new Matrix(mat.getrows(), mat.getcolumns());
        Integer[] row = new Integer[1];
        Integer[] column = new Integer[1];
        for (int i = 0; i < mat.getrows(); i++) {
            for (int j = 0; j < mat.getcolumns(); j++) {
                row[0] = i + 1;
                column[0] = j + 1;
                reverse.setValueAt(i, j, Math.round(Math.pow(-1, i + j) * det * determinant(getMinor(mat, row, column)) * 1000.0) / 1000.0);
            }
        }

        return transpose(reverse);
    }

    public double determinant(Matrix mat) {
        if (!isSquareMatrix(mat)) {
            throw new IllegalArgumentException("Can't calculate non square matrices determinant");
        }
        if (mat.getcolumns() == 1 && mat.getrows() == 1) {
            return mat.getValueAt(0, 0);
        }
        boolean changed = false;
        if (mat.getValueAt(0, 0) == 0) { // If first element in first row is zero
            for (int i = 0; i < mat.getrows(); i++) {
                if (mat.getValueAt(i, 0) != 0) { // Change first row with a row whose first element isn't zero
                    double[] s = new double[mat.getcolumns()];
                    for (int j = 0; j < mat.getcolumns(); j++) {
                        s[j] = mat.getValueAt(i, j);
                        mat.setValueAt(i, j, mat.getValueAt(0, j));
                        mat.setValueAt(0, j, s[j]);
                        changed = true;
                    }
                    break;
                }
            }
        }

        if (!changed && mat.getValueAt(0, 0) == 0) { // If row elements are all zeros
            return 0;
        }

        Matrix teisendatudMat = new Matrix(mat.getrows(), mat.getcolumns()); // Create new Ma
        for (int i = 0; i < teisendatudMat.getcolumns(); i++) {
            teisendatudMat.setValueAt(0, i, mat.getValueAt(0, i) * (1 / (mat.getValueAt(0, 0))));
        }

        // Multiply first row so all first column elements become zero (except first)
        for (int i = 1; i < mat.getrows(); i++) { // Rows
            for (int j = 0; j < mat.getcolumns(); j++) { // Columns
                teisendatudMat.setValueAt(i, j, mat.getValueAt(i, j) + (mat.getValueAt(0, j) * -(mat.getValueAt(i, 0) / mat.getValueAt(0, 0))));
            }
        }

        if (teisendatudMat.getrows() > 2) { // Recursively do the same for minor
            Matrix arendatud = new Matrix(teisendatudMat.getrows() - 1, teisendatudMat.getcolumns() - 1);
            for (int i = 0; i < teisendatudMat.getrows() - 1; i++) { // Rows
                for (int j = 0; j < teisendatudMat.getcolumns() - 1; j++) { // Columns
                    arendatud.setValueAt(i, j, teisendatudMat.getValueAt(i + 1, j + 1));
                }
            }
            if (changed) {
                return -(mat.getValueAt(0, 0) * determinant(arendatud));
            } else {
                return mat.getValueAt(0, 0) * determinant(arendatud);
            }
        } else {
            if (changed) {
                return -(mat.getValueAt(0, 0) * teisendatudMat.getValueAt(1, 1));
            } else {
                return mat.getValueAt(0, 0) * teisendatudMat.getValueAt(1, 1);
            }
        }
    }

    public boolean isSquareMatrix(Matrix mat) {
        return mat.getrows() == mat.getcolumns();
    }

    public boolean isSymmetric(Matrix mat) {
        if (!isSquareMatrix(mat)) {
            return false;
        }
        for (int i = 0; i < mat.getrows(); i++) {
            for (int j = 0; j < mat.getcolumns(); j++) {
                if (mat.getValueAt(i, j) != mat.getValueAt(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isAntiSymmetric(Matrix mat) {
        if (!isSquareMatrix(mat)) {
            return false;
        }
        for (int i = 0; i < mat.getrows(); i++) {
            for (int j = 0; j < mat.getcolumns(); j++) {
                if (mat.getValueAt(i, j) != -mat.getValueAt(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEqual(Matrix mat1, Matrix mat2) {
        if (mat1.getrows() == mat2.getrows() && mat1.getcolumns() == mat2.getcolumns()) {
            for (int i = 0; i < mat1.getrows(); i++) {
                for (int j = 0; j < mat1.getcolumns(); j++) {
                    if (mat1.getValueAt(i, j) != mat2.getValueAt(i, j)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}