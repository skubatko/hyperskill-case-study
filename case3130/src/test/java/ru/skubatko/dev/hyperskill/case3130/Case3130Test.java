package ru.skubatko.dev.hyperskill.case3130;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case3130.Case3130.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case3130Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    @Test
    public void proceedCase1() {
        int[] arrayA = {1, 5, 8, 12, 13};
        int[] arrayB = {8, 1, 23, 1, 11};

        int[] expected = {3, 1, -1, 1, -1};

        int[] result = proceed(arrayA, arrayB);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        int[] arrayA = {1, 3, 5, 7, 8, 10, 12, 13};
        int[] arrayB = {8, 1, 23, 10, 11};

        int[] expected = {5, 1, -1, 6, -1};

        int[] result = proceed(arrayA, arrayB);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase02() {
        int[] arrayA = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] arrayB = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        int[] expected = {-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1};

        int[] result = proceed(arrayA, arrayB);

        assertThat(result).isEqualTo(expected);
    }
}
