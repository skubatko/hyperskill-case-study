package ru.skubatko.dev.hyperskill.case5704;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5704.Case5704.Alignment;
import static ru.skubatko.dev.hyperskill.case5704.Case5704.multipleSequenceAlignment;
import static ru.skubatko.dev.hyperskill.case5704.Case5704.cost;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Case5704Test {

    @Rule
    public Timeout timeout = Timeout.seconds(3);

    @Test
    public void multipleSequenceAlignmentCase1() {
        String s = "ACGGGT";
        String t = "ACACCGGT";
        String u = "ACCGG";

        Alignment result = multipleSequenceAlignment(s, t, u);

        assertThat(result.editDistance).isEqualTo(14);
        assertThat(result.s).isEqualTo("--ACGGGT");
        assertThat(result.t).isEqualTo("ACACCGGT");
        assertThat(result.u).isEqualTo("--ACCGG-");
    }

    @Test
    public void costCase1() {
        char c1 = 'A';
        char c2 = 'A';
        char c3 = 'A';

        int result = cost(c1, c2, c3);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void costCase2() {
        char c1 = 'A';
        char c2 = 'A';
        char c3 = 'B';

        int result = cost(c1, c2, c3);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void costCase3() {
        char c1 = 'A';
        char c2 = 'B';
        char c3 = 'C';

        int result = cost(c1, c2, c3);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void costCase4() {
        char c1 = 'A';
        char c2 = '-';
        char c3 = '-';

        int result = cost(c1, c2, c3);

        assertThat(result).isEqualTo(4);
    }

    @Test
    public void costCase5() {
        char c1 = 'A';
        char c2 = 'A';
        char c3 = '-';

        int result = cost(c1, c2, c3);

        assertThat(result).isEqualTo(4);
    }

    @Test
    public void costCase6() {
        char c1 = 'A';
        char c2 = 'B';
        char c3 = '-';

        int result = cost(c1, c2, c3);

        assertThat(result).isEqualTo(5);
    }
}
