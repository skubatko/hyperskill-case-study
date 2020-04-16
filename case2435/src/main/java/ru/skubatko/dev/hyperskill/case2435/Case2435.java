package ru.skubatko.dev.hyperskill.case2435;

import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class Case2435 {

    /**
     * The operator combines all values in the given range into one value
     * using combiner and initial value (seed)
     */
    public static final BiFunction<Integer, IntBinaryOperator, IntBinaryOperator> reduceIntOperator =
            (seed, f) -> (left, right) -> IntStream.rangeClosed(left, right).reduce(seed, f);
    /**
     * The operator calculates the sum in the given range (inclusively)
     */
    public static final IntBinaryOperator sumOperator =
            (left, right) -> IntStream.rangeClosed(left, right).reduce(Integer::sum).orElse(0);

    /**
     * The operator calculates the product in the given range (inclusively)
     */
    public static final IntBinaryOperator productOperator =
            (left, right) -> IntStream.rangeClosed(left, right).reduce(1, (x, y) -> x * y);

}
