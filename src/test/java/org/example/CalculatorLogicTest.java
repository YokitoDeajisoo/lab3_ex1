package org.example;


import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorLogicTest {

    private static final double EPS = 1e-6;
    private final CalculatorLogic logic = new CalculatorLogic();

    // --- Тести для Завдання 1 ---

    @Test
    public void testControlExampleTask1() {
        // Контрольний приклад з методички: a = 3, b = 27 -> sa = 15, sg = 9[cite: 6]
        double a = 3.0;
        double b = 27.0;

        assertEquals(15.0, logic.calcArithmeticMean(a, b), EPS);
        assertEquals(9.0, logic.calcGeometricMean(a, b), EPS);
    }

    @Test
    public void testArithmeticMeanWithNegativeNumbers() {
        assertEquals(-5.0, logic.calcArithmeticMean(-15.0, 5.0), EPS);
    }

    @Test
    public void testGeometricMeanWithZero() {
        assertEquals(0.0, logic.calcGeometricMean(0.0, 25.0), EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGeometricMeanNegativeThrowsException() {
        logic.calcGeometricMean(-4.0, 9.0);
    }

    // --- Тести для Завдання 2 ---

    @Test
    public void testFloorBoundaries() {
        // 1-й поверх: квартири 1..5
        assertEquals(1, logic.getFloorByApartment(1));
        assertEquals(1, logic.getFloorByApartment(5));

        // 2-й поверх: квартири 6..10
        assertEquals(2, logic.getFloorByApartment(6));
        assertEquals(2, logic.getFloorByApartment(10));

        // 5-й поверх: квартири 21..25
        assertEquals(5, logic.getFloorByApartment(23));

        // 9-й поверх: квартири 41..45
        assertEquals(9, logic.getFloorByApartment(41));
        assertEquals(9, logic.getFloorByApartment(45));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApartmentZeroThrowsException() {
        logic.getFloorByApartment(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApartmentOutOfRangeThrowsException() {
        logic.getFloorByApartment(46);
    }
}