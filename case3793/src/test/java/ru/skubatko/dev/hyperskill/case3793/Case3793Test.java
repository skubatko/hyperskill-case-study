package ru.skubatko.dev.hyperskill.case3793;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case3793.Case3793.calcEquation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case3793Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void calcEquation1() {
        String s = "5 + x = 15";

        assertThat(calcEquation(s)).isEqualTo(10);
    }

    @Test
    public void calcEquation2() {
        String s = "x - 8 = 10";

        assertThat(calcEquation(s)).isEqualTo(18);
    }

    @Test
    public void calcEquation3() {
        String s = "10 = 12 + x";

        assertThat(calcEquation(s)).isEqualTo(-2);
    }
}
