package ru.skubatko.dev.hyperskill.case5704;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case5704.Case5704.Alignment;
import static ru.skubatko.dev.hyperskill.case5704.Case5704.multipleSequenceAlignment;

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
}
