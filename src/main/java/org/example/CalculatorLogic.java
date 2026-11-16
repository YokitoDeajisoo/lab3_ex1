package org.example;

public class CalculatorLogic {

    /**
     * Обчислює середнє арифметичне двох чисел.
     */
    public double calcArithmeticMean(double a, double b) {
        return (a + b) / 2.0;
    }

    /**
     * Обчислює середнє геометричне двох невід'ємних чисел.
     */
    public double calcGeometricMean(double a, double b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Числа для середнього геометричного не можуть бути від'ємними!");
        }
        return Math.sqrt(a * b);
    }

    /**
     * Завдання 2 (Варіант 1): Визначає поверх у 9-поверховому будинку (по 5 квартир на поверсі)
     * з використанням конструкції switch...case.
     */
    public int getFloorByApartment(int apartment) {
        if (apartment < 1 || apartment > 45) {
            throw new IllegalArgumentException("Номер квартири має бути від 1 до 45 (у будинку 9 поверхів по 5 квартир).");
        }

        // Формула (apartment - 1) / 5 дає індекс поверху від 0 до 8
        int floorIndex = (apartment - 1) / 5;
        int floor;

        switch (floorIndex) {
            case 0: floor = 1; break; // кв. 1 - 5
            case 1: floor = 2; break; // кв. 6 - 10
            case 2: floor = 3; break; // кв. 11 - 15
            case 3: floor = 4; break; // кв. 16 - 20
            case 4: floor = 5; break; // кв. 21 - 25
            case 5: floor = 6; break; // кв. 26 - 30
            case 6: floor = 7; break; // кв. 31 - 35
            case 7: floor = 8; break; // кв. 36 - 40
            case 8: floor = 9; break; // кв. 41 - 45
            default:
                throw new IllegalArgumentException("Некоректний номер квартири.");
        }
        return floor;
    }
}