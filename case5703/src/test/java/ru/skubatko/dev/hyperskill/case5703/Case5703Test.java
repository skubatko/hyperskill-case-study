package ru.skubatko.dev.hyperskill.case5703;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5703.Case5703.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case5703Test {

    @Rule
    public Timeout timeout = Timeout.seconds(3);

    @Test
    public void proceedCase1() {
        String s = "cab";
        String t = "abacabad";

        Case5703.Alignment result = proceed(s, t);

        assertThat(result.editDistance).isEqualTo(0);
        assertThat(result.source).isEqualTo("cab");
        assertThat(result.target).isEqualTo("cab");
    }

    @Test
    public void proceedCase2() {
        String s = "hello";
        String t = "greenyellowblue";

        Case5703.Alignment result = proceed(s, t);

        assertThat(result.editDistance).isEqualTo(3);
        assertThat(result.source).isEqualTo("hello");
        assertThat(result.target).isEqualTo("-ello");
    }

    @Test
    public void proceedCase3() {
        String s = "";
        String t = "hello";

        Case5703.Alignment result = proceed(s, t);

        assertThat(result.editDistance).isEqualTo(5);
        assertThat(result.source).isEqualTo("hello");
        assertThat(result.target).isEqualTo("hello");
    }

    @Test
    public void proceedCase4() {
        String s = "e";
        String t = "hello";

        Case5703.Alignment result = proceed(s, t);

        assertThat(result.editDistance).isEqualTo(0);
        assertThat(result.source).isEqualTo("e");
        assertThat(result.target).isEqualTo("e");
    }

    @Test
    public void proceedCase5() {
        String s = "abacabad";
        String t = "cab";

        Case5703.Alignment result = proceed(s, t);

        assertThat(result.editDistance).isEqualTo(8);
        assertThat(result.source).isEqualTo("abacabad");
        assertThat(result.target).isEqualTo("abacabad");
    }

    @Test
    public void proceedCase6() {
        String s = "hello";
        String t = "yello";

        Case5703.Alignment result = proceed(s, t);

        assertThat(result.editDistance).isEqualTo(3);
        assertThat(result.source).isEqualTo("hello");
        assertThat(result.target).isEqualTo("-ello");
    }
}
