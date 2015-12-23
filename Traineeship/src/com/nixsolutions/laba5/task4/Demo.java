package com.nixsolutions.laba5.task4;

import com.nixsolutions.laba2.FractionNumberImpl;
import com.nixsolutions.laba2.FractionNumberOperationImpl;
import com.nixsolutions.laba5.task2.ArrayCollectionImpl;
import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;
import interfaces.task5.ArrayCollection;

import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {
        ArrayCollection<FractionNumber> collection = new ArrayCollectionImpl<>();
        for (int i = 0; i < 3; i++) {
            collection.add(getRandomFractionNamber());
        }
        System.out.println("FractionNumber collection: " + collection);
        System.out.println("Sum result: " + sum(collection));
    }

    public static FractionNumber sum(
            ArrayCollection<FractionNumber> collection) {
        if (collection.size() == 1) {
            return collection.iterator().next();
        }
        FractionNumberOperation operations = new FractionNumberOperationImpl();

        // create totalSum as a zero argument
        FractionNumber totalSum = new FractionNumberImpl();
        totalSum.setDividend(0);
        totalSum.setDivisor(1);

        Iterator<FractionNumber> iterator = collection.iterator();
        while (iterator.hasNext()) {
            totalSum = operations.add(totalSum, iterator.next());
        }
        return totalSum;
    }

    public static FractionNumber getRandomFractionNamber() {
        FractionNumber fractionNumber = new FractionNumberImpl();
        fractionNumber.setDividend(getRandomInt());
        fractionNumber.setDivisor(getRandomInt());
        return fractionNumber;
    }

    public static int getRandomInt() {
        int min = 1;
        int max = 10;
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
