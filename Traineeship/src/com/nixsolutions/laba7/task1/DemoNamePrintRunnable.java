package com.nixsolutions.laba7.task1;

import interfaces.task7.simple.NamePrinterRunnable;

public class DemoNamePrintRunnable {
    public static void main(String[] args) {
        NamePrinterRunnable namePrinterA = new NamePrinterRunnableImpl();
        NamePrinterRunnable namePrinterB = new NamePrinterRunnableImpl();

        namePrinterA.setPrintName("Printer A");
        namePrinterA.setStream(System.out);
        namePrinterA.setCount(5);
        namePrinterA.setInterval(1500);

        namePrinterB.setPrintName("Printer B");
        namePrinterB.setStream(System.err);
        namePrinterB.setCount(6);
        namePrinterB.setInterval(1700);

        Thread threadA = new Thread(namePrinterA);
        Thread threadB = new Thread(namePrinterB);

        threadA.start();
        threadB.start();
    }
}
