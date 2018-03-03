package hbrown.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LuhnJava8Test {

    private final LuhnJava8 cut = new LuhnJava8();

    @Test
    void happyPath() {
        final String number = "8763";

        final boolean valid = cut.isValid(number);

        assertTrue(valid);
    }

}