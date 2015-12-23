package com.nixsolutions.laba7.task1;

import java.io.PrintStream;

import interfaces.task7.simple.NamePrinterThread;

public class NamePrinterThreadJun extends NamePrinterThread {
    private int count;
    private long interval;
    private String name;
    private PrintStream stream;

    @Override
    public void setCount(int c) {
        if (c <= 0) {
            throw new IllegalArgumentException("Argument <= 0");
        }
        count = c;
    }

    @Override
    public void setInterval(long i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Argument <= 0");
        }
        interval = i;
    }

    @Override
    public void setPrintName(String n) {
        if (n == null) {
            throw new NullPointerException("Argument is null");
        }
        if (n.length() == 0) {
            throw new IllegalArgumentException("Name's length == 0");
        }
        name = n;
    }

    @Override
    public void setStream(PrintStream s) {
        if (s == null) {
            throw new NullPointerException("Argument is null");
        }
        stream = s;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                stream.println(name);
                Thread.sleep(interval);
            }
        } catch (InterruptedException ex) {
            stream.println("Thread was interrupted");
        }
    }

}
