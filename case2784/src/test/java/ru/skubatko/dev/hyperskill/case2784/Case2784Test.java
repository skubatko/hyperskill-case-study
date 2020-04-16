package ru.skubatko.dev.hyperskill.case2784;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.skubatko.dev.hyperskill.case2784.Case2784.MaxStack;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case2784Test {

    @Rule
    public Timeout timeout = Timeout.seconds(2);

    private MaxStack maxStack;

    @Before
    public void setUp() {
        maxStack = new MaxStack();
    }

    @Test
    public void testCase1() {
        maxStack.push(2);
        maxStack.push(1);
        assertThat(maxStack.max()).isEqualTo(2);
        maxStack.pop();
        assertThat(maxStack.max()).isEqualTo(2);
    }

    @Test
    public void testCase2() {
        maxStack.push(1);
        maxStack.push(2);
        assertThat(maxStack.max()).isEqualTo(2);
        maxStack.pop();
        assertThat(maxStack.max()).isEqualTo(1);
    }

    @Test
    public void testCase3() {
        maxStack.push(2);
        maxStack.push(3);
        maxStack.push(9);
        maxStack.push(7);
        maxStack.push(2);
        assertThat(maxStack.max()).isEqualTo(9);
        assertThat(maxStack.max()).isEqualTo(9);
        assertThat(maxStack.max()).isEqualTo(9);
        maxStack.pop();
        assertThat(maxStack.max()).isEqualTo(9);
    }

    @Test
    public void testCase54() {
        for (int i = 1; i < 100_001; i++) {
            maxStack.push(i);
        }
        assertThat(maxStack.max()).isEqualTo(100_000);

        for (int i = 1; i < 100_001; i++) {
            maxStack.pop();
            assertThat(maxStack.max()).isEqualTo(100_000 - i);
        }
    }
}
