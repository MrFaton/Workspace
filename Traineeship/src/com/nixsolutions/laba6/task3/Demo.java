package com.nixsolutions.laba6.task3;

import com.nixsolutions.laba6.task2.IOUtilsImpl;
import interfaces.task6.IOUtils;

import java.io.*;

public class Demo {
    public static void main(String[] args) {
        IOUtils ioUtils = new IOUtilsImpl();
        String text = "abcbdddeaf";
        String inChars = "abf";
        String outChars = "123";

        String result = null;

        try (Reader reader = new StringReader(text);
                Writer writer = new StringWriter()) {
            ioUtils.replaceChars(reader, writer, inChars, outChars);
            result = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Input: " + text);
        System.out.println("Output: " + result);
    }
}
