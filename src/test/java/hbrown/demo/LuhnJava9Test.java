package hbrown.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LuhnJava9Test {

    private final LuhnJava9 cut = new LuhnJava9();

    @Test
    void happyPath() {
        final long number = 8763L;

        final boolean valid = cut.isValid(number);

        assertTrue(valid);
    }
}