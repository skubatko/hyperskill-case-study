package ru.skubatko.dev.hyperskill.case5730;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5730.Case5730.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case5730Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3);

    @Test
    public void proceedCase1() {
        String p = "aba";

        int expected = 6;

        int result = proceed(p);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        String p = "x";

        int expected = 2;

        int result = proceed(p);

        assertThat(result).isEqualTo(expected);
    }
}
