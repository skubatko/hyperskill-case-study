package case5718_substring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class Case5718Test {

    private Case5718 main;

    @Before
    public void setUp() {
        main = new Case5718();
    }

    @Test
    public void testFindCase1() throws Exception {
        String pattern = "ab";
        String text = "ab";
        assertPatternFound(pattern, text, 0);
    }

    @Test
    public void testFindCase2() throws Exception {
        String pattern = "ab";
        String text = "baba";
        assertPatternFound(pattern, text, 1);
    }

    @Test
    public void testFindTwoOccurrences() throws Exception {
        String pattern = "ab";
        String text = "babcab";
        main.proceed(pattern, text);
        int result = main.getNumberOfOccurrences();
        Assert.assertEquals(2, result);
        Assert.assertEquals(Arrays.asList(1, 4), main.getPositionList());
    }

    @Test
    public void testFindThreeOccurrences() throws Exception {
        String pattern = "ab";
        String text = "babcabdab";
        main.proceed(pattern, text);
        int result = main.getNumberOfOccurrences();
        Assert.assertEquals(3, result);
        Assert.assertEquals(Arrays.asList(1, 4, 7), main.getPositionList());
    }

    @Test
    public void testOccurrencesCase3() throws Exception {
        String pattern = "aba";
        String text = "ababababab";
        main.proceed(pattern, text);
        int result = main.getNumberOfOccurrences();
        Assert.assertEquals(2, result);
        Assert.assertEquals(Arrays.asList(0, 4), main.getPositionList());
    }

    private void assertPatternFound(String pattern, String text, int position) {
        int result = main.findFirstPositionOfPatternInTheText(pattern, text);
        Assert.assertEquals(position, result);
    }

}