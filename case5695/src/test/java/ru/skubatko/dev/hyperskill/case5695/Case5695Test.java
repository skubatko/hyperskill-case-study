package ru.skubatko.dev.hyperskill.case5695;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5695.Case5695.editDistance;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case5695Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testCase1() {
        String s = "hello";
        String t = "yellow";

        int result = editDistance(s, t);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void testCase2() {
        String s = "green";
        String t = "red";

        int result = editDistance(s, t);

        assertThat(result).isEqualTo(4);
    }
}
