package ru.skubatko.dev.hyperskill.case5723;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5723.Case5723.proceed;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Case5723Test {

//    @Rule
//    public Timeout globalTimeout = Timeout.seconds(3);

    @Test
    public void proceedCase1() {
        String s = "abacabad";
        int t = 2;
        int[][] pairs = new int[][]{
                {1, 4, 5, 8},
                {0, 3, 4, 7}
        };

        int expected = 1;

        int result = proceed(s, t, pairs);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        String s = "seamlessness";
        int t = 3;
        int[][] pairs = new int[][]{
                {4, 8, 8, 12},
                {5, 8, 9, 12},
                {0, 1, 11, 12}
        };

        int expected = 2;

        int result = proceed(s, t, pairs);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase3() {
        String s = "aaacabad";
        int t = 2;
        int[][] pairs = new int[][]{
                {0, 1, 1, 2},
                {0, 1, 2, 3}
        };

        int expected = 2;

        int result = proceed(s, t, pairs);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase06() throws IOException {
        List<String> lines = IOUtils.readLines(
                this.getClass().getResourceAsStream(
                        "/" + "hyperskill-5723-test-06.txt"), "UTF-8");
        String s = lines.get(0);
        int t = Integer.parseInt(lines.get(1));

        int[][] pairs = new int[t][4];
        for (int i = 0; i < t; i++) {
            pairs[i] = Arrays.stream(lines.get(2 + i).split("\\s")).mapToInt(Integer::parseInt).toArray();
        }

        int expected = 140;

        int result = proceed(s, t, pairs);

        assertThat(result).isEqualTo(expected);
    }
}
