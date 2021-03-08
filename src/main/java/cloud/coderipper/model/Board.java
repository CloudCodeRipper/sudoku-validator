package cloud.coderipper.model;

import cloud.coderipper.App;
import cloud.coderipper.utils.ValidationUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private final int[][] board;

    public Board(final File file) throws FileNotFoundException {
        this.board = readMatrixFromFile(file);
    }

    public int[][] getBoard() {
        return Arrays.stream(board)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }

    private int[][] readMatrixFromFile(File file) throws FileNotFoundException {
        ValidationUtils.validateInputFileData(file, App.ROWS_NUMBER, App.COLUMNS_NUMBER, App.DELIMITER_PATTERN);

        Scanner sc = new Scanner(file);
        sc.useDelimiter(App.DELIMITER_PATTERN);
        int[][] matrix = new int[App.ROWS_NUMBER][App.COLUMNS_NUMBER];
        while (sc.hasNextLine()) {
            for (int i = 0; i < matrix.length; i++) {
                String[] line = sc.nextLine().trim().split(App.DELIMITER_PATTERN);
                for (int j = 0; j < matrix[0].length; j++) {
                    ValidationUtils.validateInputValue(line[j]);

                    int number = Integer.parseInt(line[j]);
                    ValidationUtils.validateInputNumber(number);

                    matrix[i][j] = number;
                }
            }
        }
        return matrix;
    }

    public int[] getColumn(final int columnNumber) {
        return Arrays.stream(board)
                .mapToInt(ints -> ints[columnNumber]).toArray();
    }

    public int[] getRow(final int rowNumber) {
        return board[rowNumber];
    }

}
