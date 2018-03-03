package hbrown.demo;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class LuhnJava9 {

    public boolean isValid(long number) {
        final PrimitiveIterator.OfInt factor = IntStream.iterate(1, i -> 3 - i).iterator();

        final long sum = LongStream.iterate(number, n -> n / 10)
                .takeWhile(n -> n > 0)
                .map(n -> n % 10)
                .map(i -> i * factor.nextInt())
                .reduce(0, (a, b) -> a + b / 10 + b % 10);

        return (sum % 10) == 0;
    }
}
