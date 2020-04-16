package ru.skubatko.dev.hyperskill.case5718;

import static ru.skubatko.dev.hyperskill.case5718.Case5718.proceed;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Case5718Test {

    @Test
    public void testFindCase1() {
        String pattern = "ab";
        String text = "ab";

        assertPatternFound(pattern, text, 0);
    }

    @Test
    public void testFindCase2() {
        String pattern = "ab";
        String text = "baba";

        assertPatternFound(pattern, text, 1);
    }

    @Test
    public void testFindTwoOccurrences() {
        String pattern = "ab";
        String text = "babcab";

        List<Integer> result = proceed(pattern, text);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(Arrays.asList(1, 4), result);
    }

    @Test
    public void testFindThreeOccurrences() {
        String pattern = "ab";
        String text = "babcabdab";

        List<Integer> result = proceed(pattern, text);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(Arrays.asList(1, 4, 7), result);
    }

    @Test
    public void testOccurrencesCase3() {
        String pattern = "aba";
        String text = "ababababab";

        List<Integer> result = proceed(pattern, text);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(Arrays.asList(0, 4), result);
    }

    private void assertPatternFound(String pattern, String text, int position) {
        int result = text.indexOf(pattern);
        Assert.assertEquals(position, result);
    }
}
