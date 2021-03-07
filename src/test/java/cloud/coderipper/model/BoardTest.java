package cloud.coderipper.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;

class BoardTest {

    @Test
    void getBoard() throws FileNotFoundException, URISyntaxException {
        int[][] expectedBoard = {{7, 3, 4, 1, 6, 2, 9, 8, 5},
                {6, 8, 5, 4, 7, 9, 3, 2, 1},
                {2, 1, 9, 5, 3, 8, 6, 4, 7},
                {5, 6, 8, 9, 1, 3, 2, 7, 4},
                {3, 4, 2, 6, 8, 7, 1, 5, 9},
                {1, 9, 7, 2, 5, 4, 8, 3, 6},
                {8, 5, 1, 7, 2, 6, 4, 9, 3},
                {9, 2, 6, 3, 4, 5, 7, 1, 8},
                {4, 7, 3, 8, 9, 1, 5, 6, 2}};
        final File file = Paths.get(ClassLoader.getSystemResource("correctSudoku.csv").toURI()).toFile();
        final Board board = new Board(file);
        Assertions.assertNotNull(board.getBoard(), "Boar was not initialized");
        Assertions.assertEquals(Arrays.deepToString(expectedBoard), Arrays.deepToString(board.getBoard()));
    }

    @Test
    void getColumn() throws FileNotFoundException, URISyntaxException {
        int[] expectedColumn = {7, 6, 2, 5, 3, 1, 8, 9, 4};
        final File file = Paths.get(ClassLoader.getSystemResource("correctSudoku.csv").toURI()).toFile();
        final Board board = new Board(file);
        Assertions.assertArrayEquals(expectedColumn, board.getColumn(0));
    }

    @Test
    void getRow() throws FileNotFoundException, URISyntaxException {
        int[] expectedColumn = {7, 3, 4, 1, 6, 2, 9, 8, 5};
        final File file = Paths.get(ClassLoader.getSystemResource("correctSudoku.csv").toURI()).toFile();
        final Board board = new Board(file);
        Assertions.assertArrayEquals(expectedColumn, board.getRow(0));
    }
}