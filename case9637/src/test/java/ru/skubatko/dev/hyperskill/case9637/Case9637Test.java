package ru.skubatko.dev.hyperskill.case9637;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case9637.Case9637.Pair;
import static ru.skubatko.dev.hyperskill.case9637.Case9637.Result;
import static ru.skubatko.dev.hyperskill.case9637.Case9637.calcStringPolynomialHash;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.List;

public class Case9637Test {

    @Rule
    public Timeout timeout = Timeout.seconds(8);

    @Test
    public void testCase1() {
        String s = "abacabad";
        List<Pair> pairs = Arrays.asList(
                new Pair(1, 3),
                new Pair(3, 7),
                new Pair(7, 8)
        );

        Result expected = new Result();
        expected.prefixesHashList = Arrays.asList(33L, 1835L, 94532L, 5305227L, 265691100L, 484337736L, 908248414L, 508901936L);
        expected.givenHashes = Arrays.asList(94499L, 908153882L, 600653531L);

        Result actual = calcStringPolynomialHash(s, pairs);

        assertThat(actual.prefixesHashList).containsExactlyElementsOf(expected.prefixesHashList);
        assertThat(actual.givenHashes).containsExactlyElementsOf(expected.givenHashes);
    }
}
