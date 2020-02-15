package ru.skubatko.dev.hyperskill.case2221;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static ru.skubatko.dev.hyperskill.case2221.Case2221.runSnail;

import org.junit.Test;

public class Case2221Test {

    @Test
    public void runOneDay() {
        assertRunSnail(1, 1, 0, 1);
    }

    @Test
    public void runTwoDays() {
        assertRunSnail(2, 1, 0, 2);
    }

    @Test
    public void runThreeFloorsInTwoDays() {
        assertRunSnail(3, 2, 1, 2);
    }

    @Test
    public void runTenFloorInEightDays() {
        assertRunSnail(10, 3, 2, 8);
    }

    @Test
    public void runTwentyFloorsInFiveDays() {
        assertRunSnail(20, 7, 3, 5);
    }

    private void assertRunSnail(int height, int day, int night, int goalDay) {
        assertThat(runSnail(height, day, night), equalTo(goalDay));
    }
}
