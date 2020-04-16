package ru.skubatko.dev.hyperskill.case3131;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case3131.Case3131.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case3131Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    @Test
    public void proceedCase1() {
        int[] array = {-8, -2, 0, 3, 9};

        boolean result = proceed(array);

        assertThat(result).isTrue();
    }

    @Test
    public void proceedCase2() {
        int[] array = {4, 7, 9, 12, 14, 19};

        boolean result = proceed(array);

        assertThat(result).isFalse();
    }

    @Test
    public void proceedCase3() {
        int[] array = {-2, 1, 3, 5};

        boolean result = proceed(array);

        assertThat(result).isTrue();
    }
}
