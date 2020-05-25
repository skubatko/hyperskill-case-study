package ru.skubatko.dev.hyperskill.case3785;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case3785Test {

    @Rule
    public Timeout timeout = Timeout.seconds(8);

    @Test
    public void testCase1() {
        int[][] arr = {
                {5, 8, 9, 6, 7, 4, 2, 1, 3},
                {7, 4, 3, 1, 8, 2, 9, 5, 6},
                {1, 2, 6, 9, 5, 3, 8, 7, 4},
                {9, 3, 5, 4, 2, 1, 7, 6, 8},
                {4, 1, 2, 8, 6, 7, 3, 9, 5},
                {6, 7, 8, 3, 9, 5, 1, 4, 2},
                {8, 6, 4, 2, 1, 9, 5, 3, 7},
                {3, 9, 7, 5, 4, 8, 6, 2, 1},
                {2, 5, 1, 7, 3, 6, 4, 8, 9}
        };

        boolean expected = true;

        boolean actual = Case3785.checkSudoku(arr, 3);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCase2() {
        int[][] arr = {
                {1, 1, 2, 2},
                {1, 1, 2, 2},
                {3, 3, 4, 4},
                {3, 3, 4, 4}
        };

        boolean expected = false;

        boolean actual = Case3785.checkSudoku(arr, 2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCase3() {
        int[][] arr = {
                {1}
        };

        boolean expected = true;

        boolean actual = Case3785.checkSudoku(arr, 1);

        assertThat(actual).isEqualTo(expected);
    }
}
