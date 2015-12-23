package com.nixsolutions.laba6.task5;

import com.nixsolutions.laba6.task2.IOUtilsImpl;
import interfaces.task6.IOUtils;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        IOUtils ioUtils = new IOUtilsImpl();
        String rootDir = "/tmp";
        String extension = ".java";

        createTempFiles();

        String[] filesWithExtension = ioUtils.findFiles(rootDir, extension);
        for (String file : filesWithExtension) {
            System.out.println(file);
        }

        deleteTempFiles();
    }

    public static void createTempFiles() {
        try {
            new File("/tmp/testFile1.java").createNewFile();
            new File("/tmp/testFile2.java").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTempFiles() {
        new File("/tmp/testFile1.java").delete();
        new File("/tmp/testFile2.java").delete();
    }
}
