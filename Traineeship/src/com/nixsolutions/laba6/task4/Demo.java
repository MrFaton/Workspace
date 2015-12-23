package com.nixsolutions.laba6.task4;

import com.nixsolutions.laba6.task2.IOUtilsImpl;
import interfaces.task6.IOUtils;

public class Demo {
    public static void main(String[] args) {
        IOUtils ioUtils = new IOUtilsImpl();
        String rootDir = "/tmp";
        String[] allFiles = ioUtils.findFiles(rootDir);
        for (String file : allFiles) {
            System.out.println(file);
        }
    }
}
