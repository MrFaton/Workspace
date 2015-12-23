package com.nixsolutions.laba2;

import interfaces.task2.FractionNumber;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for FractionNumberImpl
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class FractionNumberImplTest {
    private FractionNumber fractionNumber;

    @Before
    public void setUp() {
        fractionNumber = new FractionNumberImpl();
    }

    @Test
    public void setGetDividend() {
        int dividend = 7;
        fractionNumber.setDividend(dividend);
        int actual = fractionNumber.getDividend();

        assertEquals("Divident must equals " + dividend, dividend, actual);
    }

    @Test
    public void setGetDivisor() {
        int divisor = 5;
        int badDivisor = 0;

        fractionNumber.setDivisor(divisor);
        int actual = fractionNumber.getDivisor();

        assertEquals("Divisir must equals " + divisor, divisor, actual);

        try {
            fractionNumber.setDivisor(badDivisor);
            fail("Can't set divisor to 0");
        } catch (IllegalArgumentException badArgument) {
        } catch (Exception ex) {
            fail("Must throws IllegalArgumentException");
        }
    }

    @Test
    public void testValue() {
        int dividend = 4;
        int divisor = 7;
        double result = dividend / divisor;

        fractionNumber.setDividend(dividend);
        fractionNumber.setDivisor(divisor);

        double value = fractionNumber.value();
        assertEquals("Value not equal", Double.valueOf(result),
                Double.valueOf(value));
    }

    @Test
    public void testToStringValue() {
        int dividend = 4;
        int divisor = 7;

        fractionNumber.setDividend(dividend);
        fractionNumber.setDivisor(divisor);

        String expected = "4/7";
        String actual = fractionNumber.toStringValue();
        assertEquals("String value not equals 4/7", expected, actual);
    }
}
