package cloud.coderipper;

import cloud.coderipper.model.Board;
import cloud.coderipper.utils.ValidationUtils;

import java.io.File;
import java.util.stream.IntStream;

public class App {

    public static final int ROWS_NUMBER = 9;
    public static final int COLUMNS_NUMBER = 9;
    public static final String DELIMITER_PATTERN = ",";

    public static void main(String[] args) {
        try {
            final File file = new File(args[0]);
            ValidationUtils.validateInputFileData(file, ROWS_NUMBER, COLUMNS_NUMBER, DELIMITER_PATTERN);

            final Board board = new Board(file);
            IntStream.range(0, board.getBoard().length)
                    .boxed()
                    .forEach(i -> {
                        ValidationUtils.validateArray(board.getRow(i));
                        ValidationUtils.validateArray(board.getColumn(i));
                    });
            System.out.println(0);
        } catch (Exception e) {
            System.out.println(1);
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
