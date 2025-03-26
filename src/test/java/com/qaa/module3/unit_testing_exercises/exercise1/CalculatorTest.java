package com.qaa.module3.unit_testing_exercises.exercise1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CalculatorTest {

    static Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void add() {
        assertEquals(5.0, calculator.add(2.0, 3.0));
        assertEquals(-1.0, calculator.add(2.0, -3.0));
        assertEquals(0.0, calculator.add(0.0, 0.0));
    }

    @Test
    void subtract() {
        assertEquals(-1.0, calculator.subtract(2.0, 3.0));
        assertEquals(5.0, calculator.subtract(2.0, -3.0));
        assertEquals(0.0, calculator.subtract(0.0, 0.0));
    }

    @Test
    void multiply() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0));
        assertEquals(-6.0, calculator.multiply(2.0, -3.0));
        assertEquals(0.0, calculator.multiply(2.0, 0.0));
    }

    @Test
    void divide() {
        assertEquals(2.0, calculator.divide(6.0, 3.0));
        assertEquals(-2.0, calculator.divide(6.0, -3.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(6.0, 0.0));
    }
}