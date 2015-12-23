package com.nixsolutions.laba7.task2;

import java.math.BigInteger;
import java.util.Random;

import interfaces.task7.executor.SumTask;

public class SumTaskImpl implements SumTask {
    private int tryCount;
    private int count;
    private long max;
    private BigInteger result;

    @Override
    public boolean execute() throws Exception {
        result = getRandom();
        for (int i = 0; i < count; i++) {
            result = result.add(getRandom());
        }
        return true;
    }

    @Override
    public int getTryCount() {
        return tryCount;
    }

    @Override
    public void incTryCount() {
        tryCount++;
    }

    @Override
    public BigInteger getRandom() {
        Random random = new Random();
        long num = (long) (random.nextDouble() * max);
        return BigInteger.valueOf(num);
    }

    @Override
    public BigInteger getResult() {
        return result;
    }

    @Override
    public void setCount(int c) {
        count = c;
    }

    @Override
    public void setMax(long m) {
        if (m < 1) {
            throw new IllegalArgumentException("Argument < 1");
        }
        max = m;
    }
}
