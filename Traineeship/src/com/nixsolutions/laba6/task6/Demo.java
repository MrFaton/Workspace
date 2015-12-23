package com.nixsolutions.laba6.task6;

import com.nixsolutions.laba6.task2.IOUtilsImpl;
import interfaces.task6.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        IOUtils ioUtils = new IOUtilsImpl();
        byte[] file = new byte[1024 * 1024];
        String sourceFile = "/tmp/source.f";
        String destUnBuffered = "/tmp/unBuffered.f";
        String destBuffered = "/tmp/buffered.f";
        String destBest = "/tmp/best.f";

        try (FileOutputStream out = new FileOutputStream(sourceFile)) {
            out.write(file);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start;
        long end;

        start = System.currentTimeMillis();
        ioUtils.copyFile(sourceFile, destUnBuffered);
        end = System.currentTimeMillis();
        System.out.println("UnBuffered copy: " + (end - start) + " mls");

        start = System.currentTimeMillis();
        ioUtils.copyFileBuffered(new File(sourceFile), new File(destBuffered));
        end = System.currentTimeMillis();
        System.out.println("Buffered copy: " + (end - start) + " mls");

        start = System.currentTimeMillis();
        ioUtils.copyFileBest(sourceFile, destBest);
        end = System.currentTimeMillis();
        System.out.println("Best copy: " + (end - start) + " mls");

        new File(sourceFile).delete();
        new File(destUnBuffered).delete();
        new File(destBuffered).delete();
        new File(destBest).delete();
    }

}
