package ru.skubatko.dev.hyperskill.case5696;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5696.Case5696.Result;
import static ru.skubatko.dev.hyperskill.case5696.Case5696.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case5696Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        String s = "aad";
        String[] words = {"mad", "sad", "bad"};

        Result result = proceed(s, words);

        assertThat(result.ed).isEqualTo(1);
        assertThat(result.t).isEqualTo("sad");
    }

    @Test
    public void proceedCase2() {
        String s = "asa";
        String[] words = {"ama", "aba", "ada"};

        Result result = proceed(s, words);

        assertThat(result.ed).isEqualTo(1);
        assertThat(result.t).isEqualTo("ada");
    }
}
