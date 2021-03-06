package ru.skubatko.dev.hyperskill.case5722;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;

public class Case5722Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        String s = "ACTTGATTGA";
        int expected = 4;

        int result = Case5722.proceed(s);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        String s = "ABCD";
        int expected = 0;

        int result = Case5722.proceed(s);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase21() throws IOException {
        String s = IOUtils.toString(
                this.getClass().getResourceAsStream(
                        "/" + "hyperskill-5722-test-21.txt"), "UTF-8");

        int expected = 82;

        int result = Case5722.proceed(s);

        assertThat(result).isEqualTo(expected);
    }
}
