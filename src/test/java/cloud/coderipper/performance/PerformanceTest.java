package cloud.coderipper.performance;

import cloud.coderipper.App;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

public class PerformanceTest {

    @Test
    void sudokuValidationPerformance() {
        String filePath = ClassLoader.getSystemResource("correctSudoku.csv").getPath();
        String[] args = {filePath};

        Instant start = Instant.now();

        App.main(args);

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.println("Execution time: " + timeElapsed);
    }
}
