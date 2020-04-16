package ru.skubatko.dev.hyperskill.case5676;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.skubatko.dev.hyperskill.case5676.Case5676.printPreOrder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Case5676Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void printPreOrderCase1() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        printPreOrder(0, 0, 2);

        String result = out.toString(StandardCharsets.UTF_8.name());

        assertThat(result).isEqualTo("0 1 4 5 6 2 7 8 9 3 10 11 12 ");
    }

    @Test
    public void printPreOrderCase2() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        printPreOrder(0, 0, 3);

        String result = out.toString(StandardCharsets.UTF_8.name());

        assertThat(result).isEqualTo("0 1 4 13 14 15 5 16 17 18 6 19 20 21 " +
                                             "2 7 22 23 24 8 25 26 27 9 28 29 30 " +
                                             "3 10 31 32 33 11 34 35 36 12 37 38 39 ");
    }
}
