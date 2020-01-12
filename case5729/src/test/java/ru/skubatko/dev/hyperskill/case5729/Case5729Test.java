package ru.skubatko.dev.hyperskill.case5729;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5729.Case5729.proceed;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Case5729Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3);

    @Test
    public void proceedCase1() {
        String p = "aba";
        String t = "ababababa";

        List<Integer> expected = Arrays.asList(0, 4);

        List<Integer> result = proceed(p, t);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        String p = "aba";
        String t = "abacabad";

        List<Integer> expected = Arrays.asList(0, 4);

        List<Integer> result = proceed(p, t);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase05() throws IOException {
        List<String> lines = IOUtils.readLines(
                this.getClass().getResourceAsStream(
                        "/" + "hyperskill-5729-test-05.txt"
                ), StandardCharsets.UTF_8
        );
        String p = lines.get(0);
        String t = lines.get(1);

        List<Integer> result = proceed(p, t);

        assertThat(result).isNull();
    }
}
