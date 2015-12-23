package com.nixsolutions.laba8.task4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.nixsolutions.laba8.task1.SerializableUtilsImpl;
import com.nixsolutions.laba8.task2.CyclicItemImpl;

import interfaces.task8.CyclicItem;
import interfaces.task8.SerializableUtils;

public class SerialisableDemo {
    private static String filePath = "/tmp/original.ser";

    public static void main(String[] args) {
        SerializableUtils serializableUtils = new SerializableUtilsImpl();
        CyclicItem original = new CyclicItemImpl();
        CyclicItem restored1 = null;
        CyclicItem restored2 = null;

        try (OutputStream out = new FileOutputStream(filePath)) {
            serializableUtils.serialize(out, original);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (FileInputStream in1 = new FileInputStream(filePath);
                FileInputStream in2 = new FileInputStream(filePath)) {
            restored1 = (CyclicItem) serializableUtils.deserialize(in1);
            restored2 = (CyclicItem) serializableUtils.deserialize(in2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Is restored1 and restored2 one object in Heap? "
                + (restored1 == restored2));

        new File(filePath).delete();
    }
}
