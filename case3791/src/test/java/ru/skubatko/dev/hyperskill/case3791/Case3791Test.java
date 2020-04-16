package ru.skubatko.dev.hyperskill.case3791;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.hyperskill.case3791.Case3791.proceed;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Case3791Test {

    @Rule
    public Timeout timeout = Timeout.seconds(5);

    @Test
    public void proceedCase1() {
        String s = "schedule";

        int result = proceed(s);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void proceedCase2() {
        String s = "garage";

        int result = proceed(s);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void proceedCase3() {
        String s = "player";

        int result = proceed(s);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void proceedCase4() {
        String s = "biiiiig";

        int result = proceed(s);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void proceedCase5() throws IOException {
        List<String> lines = IOUtils.readLines(
                this.getClass().getResourceAsStream("/" + "3791-5.txt"), StandardCharsets.UTF_8);

        String s = lines.get(0);

        int result = proceed(s);

        assertThat(result).isEqualTo(8);
    }
}
