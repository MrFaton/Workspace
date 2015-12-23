package com.nixsolutions.laba2;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;

public class FractionNumberOperationLauncher {
    public static void main(String[] args) {
        FractionNumberOperation operations = new FractionNumberOperationImpl();

        FractionNumber numberA = new FractionNumberImpl();
        numberA.setDividend(4);
        numberA.setDivisor(7);

        FractionNumber numberB = new FractionNumberImpl();
        numberB.setDividend(1);
        numberB.setDivisor(3);

        System.out.println("Add:");
        System.out.println(
                numberA.toStringValue() + " + " + numberB.toStringValue());
        System.out.println(
                "result: " + operations.add(numberA, numberB).toStringValue());

        System.out.println("***");

        System.out.println("Sub:");
        System.out.println(
                numberA.toStringValue() + " - " + numberB.toStringValue());
        System.out.println(
                "result: " + operations.sub(numberA, numberB).toStringValue());

        System.out.println("***");

        System.out.println("Mul:");
        System.out.println(
                numberA.toStringValue() + " * " + numberB.toStringValue());
        System.out.println(
                "result: " + operations.mul(numberA, numberB).toStringValue());

        System.out.println("***");

        System.out.println("Div:");
        System.out.println(
                numberA.toStringValue() + " / " + numberB.toStringValue());
        System.out.println(
                "result: " + operations.div(numberA, numberB).toStringValue());
    }
}
