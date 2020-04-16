package ru.skubatko.dev.hyperskill.case3655;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.skubatko.dev.hyperskill.case3655.Case3655.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case3655Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        String s1 = "case";
        String s2 = "seal";

        int result = proceed(s1, s2);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void proceedCase04() {
        String s1 = "Poooohgf";
        String s2 = "poohgf";

        int result = proceed(s1, s2);

        assertThat(result).isEqualTo(2);
    }
}
