package ru.skubatko.dev.hyperskill.case2435;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case2435.Case2435.productOperator;
import static ru.skubatko.dev.hyperskill.case2435.Case2435.reduceIntOperator;
import static ru.skubatko.dev.hyperskill.case2435.Case2435.sumOperator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case2435Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void testCase1() {
        int start = 20;
        int left = 3;
        int right = 5;

        int result = reduceIntOperator
                             .apply(start, (accumulator, x) -> accumulator + 2 * x)
                             .applyAsInt(left, right);

        assertThat(result).isEqualTo(44);
    }

    @Test
    public void testCase2() {
        int left = 3;
        int right = 5;

        int result = sumOperator.applyAsInt(left, right);

        assertThat(result).isEqualTo(12);
    }

    @Test
    public void testCase3() {
        int left = 3;
        int right = 5;

        int result = productOperator.applyAsInt(left, right);

        assertThat(result).isEqualTo(60);
    }
}
