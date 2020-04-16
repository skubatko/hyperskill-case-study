package ru.skubatko.dev.hyperskill.case5697;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5697.Case5697.editDistance;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case5697Test {

    @Rule
    public Timeout timeout = Timeout.seconds(2);

    @Test
    public void editDistanceCase1() {
        String s1 = "editing";
        String s2 = "distance";

        int result = editDistance(s1, s2);

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void editDistanceCase2() {
        String s1 = "yellow";
        String s2 = "hello";

        int result = editDistance(s1, s2);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void editDistanceCase3() {
        String s1 = "brown";
        String s2 = "bone";

        int result = editDistance(s1, s2);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void editDistanceCase4() {
        String s1 = "";
        String s2 = "empty";

        int result = editDistance(s1, s2);

        assertThat(result).isEqualTo(5);
    }
}
