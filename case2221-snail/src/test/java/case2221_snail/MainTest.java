package case2221_snail;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MainTest {

    @Test
    public void runOneDay() throws Exception {
        assertRunSnail(1, 1, 0, 1);
    }

    @Test
    public void runTwoDays() throws Exception {
        assertRunSnail(2, 1, 0, 2);
    }

    @Test
    public void runThreeFloorsInTwoDays() throws Exception {
        assertRunSnail(3, 2, 1, 2);
    }

    @Test
    public void runTenFloorInEightDays() throws Exception {
        assertRunSnail(10, 3, 2, 8);
    }

    @Test
    public void runTwentyFloorsInFiveDays() throws Exception {
        assertRunSnail(20, 7, 3, 5);
    }

    private void assertRunSnail(int height, int day, int night, int goalDay) {
        assertThat(runSnail(height, day, night), equalTo(goalDay));
    }

    private int runSnail(int height, int day, int night) {
        return (height - night - 1) / (day - night) + 1;
    }

}
