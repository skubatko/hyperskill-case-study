import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

public class MainTest {

//    @Rule
//    public Timeout globalTimeout = Timeout.seconds(8);

    @Test
    public void proceedCase1() {
        String s = "ACTTGATTGA";
        int expected = 4;

        int result = Main.proceed(s);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        String s = "ABCD";
        int expected = 0;

        int result = Main.proceed(s);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase21() throws IOException {
        String s = IOUtils.toString(
                this.getClass().getResourceAsStream(
                        "hyperskill-5722-test-21.txt"), "UTF-8");
        int expected = 498639;

        int result = Main.proceed(s);

        assertThat(result).isEqualTo(expected);
    }
}