package com.nixsolutions.laba2;

import interfaces.task2.FractionNumber;

/**
 * Holds fraction number
 *
 * @author Mr_Faton
 * @since 13.12.2015
 */
public class FractionNumberImpl implements FractionNumber {
    private int dividend = 0;
    private int divisor = 1;

    @Override
    public void setDividend(int i) {
        dividend = i;
    }

    @Override
    public int getDividend() {
        return dividend;
    }

    @Override
    public void setDivisor(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Divisor can't be 0");
        }
        divisor = i;
    }

    @Override
    public int getDivisor() {
        return divisor;
    }

    @Override
    public double value() {
        return dividend / divisor;
    }

    @Override
    public String toStringValue() {
        return dividend + "/" + divisor;
    }
    
    @Override
    public String toString() {
        return dividend + "/" + divisor;
    }
}
