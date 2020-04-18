package ru.skubatko.dev.hyperskill.case6453;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Case6453Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        List<String> words = Arrays.asList(
                "bac",
                "db",
                "dbc",
                "dba",
                "caab"
        );

        String actual = Case6453.proceed(words);

        assertThat(actual).isEqualTo("bdca");
    }

    @Test
    public void proceedCase2() throws IOException {
        List<String> words = IOUtils.readLines(
                this.getClass().getResourceAsStream("/" + "hyperskill-6453-test-03.txt"), StandardCharsets.UTF_8);
        words.remove(0);

        String actual = Case6453.proceed(words);

        assertThat(actual).isEqualTo("abcde");
    }
}
