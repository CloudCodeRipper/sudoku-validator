package cloud.coderipper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

class AppTest {

    private ByteArrayOutputStream baos;

    @BeforeEach
    void init() {
        baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        System.setOut(out);
    }

    @Test
    void validBoard() {
        String filePath = ClassLoader.getSystemResource("correctSudoku.csv").getPath();
        String[] args = {filePath};

        App.main(args);
        System.out.flush();

        Assertions.assertEquals("0" + System.lineSeparator(), baos.toString(Charset.defaultCharset()));

    }

    @Test
    void invalidBoard() {
        String filePath = ClassLoader.getSystemResource("incorrectSudoku.csv").getPath();
        String[] args = {filePath};

        App.main(args);
        System.out.flush();

        String expectedString = String.format("1%nERROR: Sequence has duplicate values. Board is not valid%n");
        Assertions.assertEquals(expectedString, baos.toString(Charset.defaultCharset()));
    }
}