package hbrown.demo;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

public class LuhnJava8 {

    public boolean isValid(String number) {
        final PrimitiveIterator.OfInt factor = IntStream.iterate(1, i -> 3 - i).iterator();

        final int sum = new StringBuilder(number)
                .reverse()
                .toString()
                .chars()
                .map(c -> c - '0')
                .map(i -> i * factor.nextInt())
                .reduce(0, (a, b) -> a + b / 10 + b % 10);

        return (sum % 10) == 0;
    }

}
