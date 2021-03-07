package cloud.coderipper.utils;

import cloud.coderipper.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationUtilsTest {

    @Test
    void validateInstanceCreation() {
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, ValidationUtils::new);
        Assertions.assertEquals("Utility class cannot be instantiated", exception.getMessage());
    }

    @Test
    void validateInputFileData() throws FileNotFoundException, URISyntaxException {
        final File file = Paths.get(ClassLoader.getSystemResource("correctSudoku.csv").toURI()).toFile();
        ValidationUtils.validateInputFileData(file, App.ROWS_NUMBER, App.COLUMNS_NUMBER, App.DELIMITER_PATTERN);
    }

    @Test
    void validateInputFileDataWithIncorrectColumnsNumber() throws URISyntaxException {
        final File file = Paths.get(ClassLoader.getSystemResource("incorrectColumnsNumberSudoku.csv").toURI()).toFile();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtils.validateInputFileData(file, App.ROWS_NUMBER, App.COLUMNS_NUMBER, App.DELIMITER_PATTERN));
        Assertions.assertTrue(exception.getMessage().contains("Incorrect number of columns:"));
    }

    @Test
    void validateInputFileDataWithIncorrectRowsNumber() throws URISyntaxException {
        final File file = Paths.get(ClassLoader.getSystemResource("incorrectRowsNumberSudoku.csv").toURI()).toFile();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtils.validateInputFileData(file, App.ROWS_NUMBER, App.COLUMNS_NUMBER, App.DELIMITER_PATTERN));
        Assertions.assertTrue(exception.getMessage().contains("Incorrect number of rows:"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "15"})
    void validateInputValue(final String value) {
        ValidationUtils.validateInputValue(value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "-1", "a", "A"})
    void validateIncorrectInputValue(final String value) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtils.validateInputValue(value));
        Assertions.assertTrue(exception.getMessage().contains("Value is not numeric or negative:"));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void validateInputNumber(final int number) {
        ValidationUtils.validateInputNumber(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 10})
    void validateIncorrectInputNumber(final int number) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtils.validateInputNumber(number));
        Assertions.assertTrue(exception.getMessage().contains("Incorrect input number:"));
    }

    @Test
    void validateArray() {
        int[] expectedArray = {7, 6, 2, 5, 3, 1, 8, 9, 4};
        ValidationUtils.validateArray(expectedArray);
    }

    @Test
    void validateArrayWithWrongArguments() {
        int[] expectedArray = {7, 6, 2, 5, 8, 1, 8, 9, 4};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ValidationUtils.validateArray(expectedArray));
        Assertions.assertEquals("Sequence has duplicate values. Board is not valid", exception.getMessage());
    }
}