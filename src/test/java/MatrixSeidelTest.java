import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MatrixSeidelTest {

    public static double roundDown(double value) {
        if (value >= 0) return Math.floor(value * 1000000) / 1000000;
        return -Math.floor(Math.abs(value) * 1000000) / 1000000;
    }

    private static Stream<Arguments> functionAndXGridNodes() {
        return Stream.of(
                arguments("C:\\Users\\matve\\IdeaProjects\\matrix\\src\\test\\resources\\gaussSeidel_1.txt",
                        new double[] {-0.7354092544, -4.4858863616, 1.3969864294399998})
        );
    }

    @ParameterizedTest
    @MethodSource("functionAndXGridNodes")
    void resultMatrix(String path, double[] ans) throws FileNotFoundException {
        MatrixGaussSeidel gaussSeidel = new MatrixGaussSeidel();
        gaussSeidel.receiveMatrixFromFile(path);
        gaussSeidel.resolve();
        Assertions.assertArrayEquals(ans, gaussSeidel.solution);
    }
}

