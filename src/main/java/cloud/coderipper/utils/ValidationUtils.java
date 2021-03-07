package cloud.coderipper.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public final class ValidationUtils {

    public ValidationUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static void validateInputFileData(final File file,
                                             final long allowableRowsNumber,
                                             final long allowableColumnsNumber,
                                             final String delimiter) throws FileNotFoundException {
        final Scanner sc = new Scanner(file);
        long rowsNumber = 0L;
        while (sc.hasNextLine()) {
            String[] row = sc.nextLine().trim().split(delimiter);
            int rowLength = row.length;
            if (rowLength != allowableColumnsNumber) {
                String errorMessage = String.format("Incorrect number of columns: requiredValue=%d, actualValue=%d",
                        allowableColumnsNumber, rowLength);
                throw new IllegalArgumentException(errorMessage);
            }
            rowsNumber++;
        }

        if (rowsNumber != allowableRowsNumber) {
            String errorMessage = String.format("Incorrect number of rows: requiredValue=%d, actualValue=%d",
                    allowableRowsNumber, rowsNumber);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateInputValue(final CharSequence cs) {
        if (!ValidationUtils.isNumeric(cs)) {
            throw new IllegalArgumentException("Value is not numeric or negative: value = " + cs);
        }
    }

    public static void validateInputNumber(int number) {
        if (number < 1 || number > 9) {
            String errorMessage = String.format("Incorrect input number: number = %d. Valid values are from 1 to 9", number);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateArray(int[] array) {
        var set = new HashSet<Integer>();
        for (int i : array) {
            if (!set.add(i)) {
                throw new IllegalArgumentException("Sequence has duplicate values. Board is not valid");
            }
        }
    }

    private static boolean isNumeric(final CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
