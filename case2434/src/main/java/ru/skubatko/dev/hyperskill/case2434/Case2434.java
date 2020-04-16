package ru.skubatko.dev.hyperskill.case2434;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Case2434 {

    /**
     * The function accepts a list of mappers and returns an operator that accepts a list of integers
     * and sequentially applies each mapper to each value (perform a transformation)
     */
    public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper =
            mappers ->
                    numbers -> numbers.stream()
                                       .map(mappers.stream().reduce(IntUnaryOperator.identity(), IntUnaryOperator::andThen)::applyAsInt)
                                       .collect(Collectors.toList());

    //    public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper =
//            mappers -> numbers -> {
//                List<Integer> results = new ArrayList<>();
//                for (Integer number : numbers) {
//                    int result = number;
//                    for (IntUnaryOperator mapper : mappers) {
//                        result = mapper.applyAsInt(result);
//                    }
//                    results.add(result);
//                }
//                return results;
//            };

    /**
     * EXAMPLE: the operator transforms each number to the same number (perform the identity transformation)
     * <p>
     * It returns a list of the same numbers.
     */
    public static final UnaryOperator<List<Integer>> identityTransformation =
            multifunctionalMapper.apply(Arrays.asList(x -> x, x -> x, x -> x));

    /**
     * The operator accepts an integer list.
     * It multiplies by two each integer number and then add one to its.
     * <p>
     * The operator returns transformed integer list.
     */
    public static final UnaryOperator<List<Integer>> multTwoAndThenAddOneTransformation =
            multifunctionalMapper.apply(Arrays.asList(x -> x * 2, x -> x + 1));

    /**
     * The operator accepts an integer list.
     * It squares each integer number and then get the next even number following it.
     * <p>
     * The operator returns transformed integer list.
     */
    public static final UnaryOperator<List<Integer>> squareAndThenGetNextEvenNumberTransformation =
            multifunctionalMapper.apply(Arrays.asList(x -> x * x, x -> x % 2 == 0 ? x + 2 : x + 1));
}
