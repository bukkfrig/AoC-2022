package day20;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

public class Tests {

    public void part_1_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals((Object) null, solution.solvePart1(input));
        }
    }

    public void part_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals((Object) null, solution.solvePart1(input));
        }
    }

    public void part_2_example_1() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("example1.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals((Object) null, solution.solvePart2(input));
        }
    }

    public void part_2() throws Exception {
        try (InputStream input = Tests.class.getResourceAsStream("input.txt")) {
            Solution solution = new SolutionImpl();
            assertEquals((Object) null, solution.solvePart2(input));
        }
    }
}
