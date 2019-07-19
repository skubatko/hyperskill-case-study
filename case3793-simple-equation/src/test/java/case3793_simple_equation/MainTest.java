package case3793_simple_equation;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MainTest {

    @Test
    public void calcEquation1() throws Exception {
        assertThat(calcEquation("5 + x = 15"), equalTo(10));
    }

    @Test
    public void calcEquation2() throws Exception {
        assertThat(calcEquation("x - 8 = 10"), equalTo(18));
    }

    @Test
    public void calcEquation3() throws Exception {
        assertThat(calcEquation("10 = 12 + x"), equalTo(-2));
    }


    private int calcEquation(String s) {
        String[] eqParts = s.split(" = ");
        String[] leftPart = new String[0];
        String[] rightPart = new String[0];
        if (eqParts[0].contains("x")) {
            leftPart = eqParts[0].split("\\s");
            rightPart = eqParts[1].split("\\s");
        } else {
            rightPart = eqParts[0].split("\\s");
            leftPart = eqParts[1].split("\\s");
        }

        int result = 0;
        if (leftPart.length > 1) {
            if (leftPart[0].equals("x")) {
                if (leftPart[1].equals("+")) {
                    result = Integer.valueOf(rightPart[0]) - Integer.valueOf(leftPart[2]);
                } else {
                    result = Integer.valueOf(rightPart[0]) + Integer.valueOf(leftPart[2]);
                }
            } else {
                if (leftPart[1].equals("+")) {
                    result = Integer.valueOf(rightPart[0]) - Integer.valueOf(leftPart[0]);
                } else {
                    result = Integer.valueOf(leftPart[0]) - Integer.valueOf(rightPart[0]);
                }
            }
        } else {
            if (rightPart[1].equals("+")) {
                result = Integer.valueOf(rightPart[0]) + Integer.valueOf(rightPart[2]);
            } else {
                result = Integer.valueOf(rightPart[0]) - Integer.valueOf(rightPart[2]);
            }
        }

        return result;
    }

}
