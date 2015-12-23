package com.nixsolutions.laba2;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;

public class FractionNumberOperationImpl implements FractionNumberOperation {

    @Override
    public FractionNumber add(FractionNumber arg0, FractionNumber arg1) {
        int dividendA = arg0.getDividend();
        int divisiorA = arg0.getDivisor();
        int dividendB = arg1.getDividend();
        int divisiorB = arg1.getDivisor();

        int lcm = getLcm(divisiorA, divisiorB);

        int coefficientA = lcm / divisiorA;
        int coefficientB = lcm / divisiorB;

        dividendA *= coefficientA;
        dividendB *= coefficientB;

        int resultDivident = dividendA + dividendB;
        int resultDivisor = lcm;

        FractionNumber fractionNumber = new FractionNumberImpl();
        fractionNumber.setDividend(resultDivident);
        fractionNumber.setDivisor(resultDivisor);
        return fractionNumber;
    }

    @Override
    public FractionNumber div(FractionNumber arg0, FractionNumber arg1) {
        int dividendA = arg0.getDividend();
        int divisiorA = arg0.getDivisor();
        int dividendB = arg1.getDividend();
        int divisiorB = arg1.getDivisor();

        if (divisiorA == 0 || divisiorB == 0) {
            throw new ArithmeticException("One of two divisors are 0: "
                    + "divisiorA=" + divisiorA + "; divisiorB=" + divisiorB);
        }

        FractionNumber fractionNumber = new FractionNumberImpl();
        fractionNumber.setDividend(dividendA * divisiorB);
        fractionNumber.setDivisor(divisiorA * dividendB);
        return fractionNumber;
    }

    @Override
    public FractionNumber mul(FractionNumber arg0, FractionNumber arg1) {
        int dividendA = arg0.getDividend();
        int divisiorA = arg0.getDivisor();
        int dividendB = arg1.getDividend();
        int divisiorB = arg1.getDivisor();

        FractionNumber fractionNumber = new FractionNumberImpl();
        fractionNumber.setDividend(dividendA * dividendB);
        fractionNumber.setDivisor(divisiorA * divisiorB);
        return fractionNumber;
    }

    @Override
    public FractionNumber sub(FractionNumber arg0, FractionNumber arg1) {
        int dividendA = arg0.getDividend();
        int divisiorA = arg0.getDivisor();
        int dividendB = arg1.getDividend();
        int divisiorB = arg1.getDivisor();

        int lcm = getLcm(divisiorA, divisiorB);

        int coefficientA = lcm / divisiorA;
        int coefficientB = lcm / divisiorB;

        dividendA *= coefficientA;
        dividendB *= coefficientB;

        int resultDivident = dividendA - dividendB;
        int resultDivisor = lcm;

        FractionNumber fractionNumber = new FractionNumberImpl();
        fractionNumber.setDividend(resultDivident);
        fractionNumber.setDivisor(resultDivisor);
        return fractionNumber;
    }

    // Calculate Least Common Multiple
    private int getLcm(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        int a = x;
        int b = y;
        while (a != 0 && b != 0) {
            if (a >= b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return (x * y) / (a + b);
    }
}
