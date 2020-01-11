package ru.skubatko.dev.hyperskill.case5731;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5731.Case5731.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case5731Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3);

    @Test
    public void proceedCase1() {
        int px = 1;
        int py = 1;
        String[] pMatrix = new String[]{"a"};

        int tx = 2;
        int ty = 2;
        String[] tMatrix = new String[]{"ab", "ba"};

        int expected = 2;

        int result = proceed(px, py, pMatrix, tx, ty, tMatrix);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        int px = 2;
        int py = 2;
        String[] pMatrix = new String[]{"aa", "bb"};

        int tx = 4;
        int ty = 4;
        String[] tMatrix = new String[]{"aabb", "bbaa", "aabb", "bbaa"};

        int expected = 3;

        int result = proceed(px, py, pMatrix, tx, ty, tMatrix);

        assertThat(result).isEqualTo(expected);
    }
}
