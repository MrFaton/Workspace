package com.nixsolutions.laba2;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Testing class for FractionNumberOperationImpl class
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class FractionNumberOperationImplTest {
    private FractionNumberOperation operation;
    private FractionNumber numberA;
    private FractionNumber numberB;

    @Before
    public void setUp() {
        operation = new FractionNumberOperationImpl();
        numberA = new FractionNumberImpl();
        numberB = new FractionNumberImpl();
    }

    @Test
    public void testAdd() {
        numberA.setDividend(1);
        numberA.setDivisor(2);

        numberB.setDividend(2);
        numberB.setDivisor(3);

        FractionNumber result = operation.add(numberA, numberB);

        assertTrue("Divident must equals 7", 7 == result.getDividend());
        assertTrue("Divisor must equals 6", 6 == result.getDivisor());
    }

    @Test
    public void testDiv() {
        numberA.setDividend(4);
        numberA.setDivisor(7);

        numberB.setDividend(15);
        numberB.setDivisor(17);

        FractionNumber result = operation.div(numberA, numberB);
        assertTrue("Dividend must = 68", 68 == result.getDividend());
        assertTrue("Divisor must = 105", 105 == result.getDivisor());
    }

    @Test
    public void testMul() {
        numberA.setDividend(2);
        numberA.setDivisor(9);

        numberB.setDividend(7);
        numberB.setDivisor(11);

        FractionNumber result = operation.mul(numberA, numberB);
        assertTrue("Dividend must = 14", 14 == result.getDividend());
        assertTrue("Divisor must = 99", 99 == result.getDivisor());
    }

    @Test
    public void testSub() {
        numberA.setDividend(9);
        numberA.setDivisor(11);

        numberB.setDividend(7);
        numberB.setDivisor(9);

        FractionNumber result = operation.div(numberA, numberB);
        assertTrue("Dividend must = 81", 81 == result.getDividend());
        assertTrue("Divisor must = 77", 77 == result.getDivisor());
    }
}
