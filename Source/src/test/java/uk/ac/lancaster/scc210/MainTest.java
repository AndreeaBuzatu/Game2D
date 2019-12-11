package uk.ac.lancaster.scc210;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final Main main = new Main();

    @Test
    void add() {
        assertEquals(2, main.add(1, 1));
    }
}