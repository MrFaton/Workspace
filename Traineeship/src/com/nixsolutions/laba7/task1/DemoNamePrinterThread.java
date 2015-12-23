package com.nixsolutions.laba7.task1;

import interfaces.task7.simple.NamePrinterThread;

public class DemoNamePrinterThread {
    public static void main(String[] args) {
        NamePrinterThread threadA = new NamePrinterThreadJun();
        NamePrinterThread threadB = new NamePrinterThreadJun();

        threadA.setPrintName("Thread A");
        threadA.setStream(System.out);
        threadA.setCount(5);
        threadA.setInterval(1500);

        threadB.setPrintName("Thread B");
        threadB.setStream(System.err);
        threadB.setCount(6);
        threadB.setInterval(1700);

        threadA.start();
        threadB.start();
    }
}
