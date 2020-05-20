package ru.skubatko.dev.hyperskill.case1932;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.List;

public class Case1932Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testCase1() {
        List<List<Integer>> matrix = Arrays.asList(
                Arrays.asList(9, 5, 3),
                Arrays.asList(0, 7, -1),
                Arrays.asList(-5, 2, 9)
        );

        int[][] expected = {
                {3, 21, 22},
                {10, 6, 19},
                {20, 16, -1}
        };

        int[][] actual = Case1932.neighborsSum(matrix);

        assertThat(actual).isNotNull();
        assertThat(actual).containsExactly(expected);
    }
}
