package ru.skubatko.dev.hyperskill.case3824;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.skubatko.dev.hyperskill.case3824.Case3824.proceed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case3824Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int value = 0;
        int expected = 1;

        int result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int value = 3;
        int expected = 2;

        int result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase3() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int value = 5;
        int expected = 4;

        int result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase4() {
        int[] array = {0, 1, 2, 3, 4, 5, 7, 8, 9};
        int value = 6;
        int expected = 4;

        int result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase5() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int value = 9;
        int expected = 4;

        int result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase05() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int value = 10;
        int expected = 5;

        int result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase6() {
        int[] array = {2 ,4 ,6 ,8 ,10, 12, 14, 16};
        int value = 3;
        int expected = 3;

        int result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }
}
