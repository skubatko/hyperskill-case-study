package ru.skubatko.dev.hyperskill.case1931;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case1931Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testCase1() {
        int n = 5;

        int[][] expected = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9},
        };

        int[][] actual = Case1931.getSpiralMatrix(n);

        assertThat(actual).isNotNull();
        assertThat(actual).containsExactly(expected);
    }
}
