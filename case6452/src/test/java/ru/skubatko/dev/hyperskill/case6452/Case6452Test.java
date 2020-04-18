package ru.skubatko.dev.hyperskill.case6452;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case6452Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        String[] variables = {"a", "x", "var", "b", "count"};
        String[] constraints = {
                "a<x",
                "var>x",
                "b<x",
                "count>b"
        };

        int actual = Case6452.proceed(variables, constraints);

        assertThat(actual).isEqualTo(2);
    }
}
