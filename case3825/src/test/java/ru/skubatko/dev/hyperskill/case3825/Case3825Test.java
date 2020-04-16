package ru.skubatko.dev.hyperskill.case3825;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case3825.Case3825.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case3825Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3);

    @Test
    public void proceedCase1() {
        int n = 11;
        int[] expected = new int[]{1, 4, 3, 2, 5, 4, 3, 6, 5, 4, 5};

        int[] result = proceed(n);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        int n = 12;
        int[] expected = new int[]{1, 4, 3, 2, 5, 4, 3, 6, 5, 4, 6, 5};

        int[] result = proceed(n);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase3() {
        int n = 13;
        int[] expected = new int[]{1, 4, 3, 2, 5, 4, 3, 6, 5, 4, 7, 6, 5};

        int[] result = proceed(n);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase4() {
        int n = 10;
        int[] expected = new int[]{1, 4, 3, 2, 5, 4, 3, 6, 5, 4};

        int[] result = proceed(n);

        assertThat(result).isEqualTo(expected);
    }
}
