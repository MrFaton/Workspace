package com.nixsolutions.laba8.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nixsolutions.laba8.task1.SerializableUtilsImpl;
import com.nixsolutions.laba8.task2.CyclicCollectionImpl;
import com.nixsolutions.laba8.task2.CyclicItemImpl;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;
import interfaces.task8.SerializableUtils;

public class CyclicCollectionDemo {
    private static String filePath = "/tmp/collection.ser";

    public static void main(String[] args) {
        SerializableUtils serializableUtils = new SerializableUtilsImpl();

        // create cyclic items
        CyclicItem item1 = new CyclicItemImpl();
        CyclicItem item2 = new CyclicItemImpl();
        CyclicItem item3 = new CyclicItemImpl();

        // Configure cyclic items
        item1.setValue("a");
        item1.setTemp("aa");
        item1.setNextItem(item2);

        item2.setValue("b");
        item2.setTemp("bb");
        item2.setNextItem(item3);

        item3.setValue("c");
        item3.setTemp("cc");
        item3.setNextItem(null);

        // create cyclic collection
        CyclicCollection cyclicCillection = new CyclicCollectionImpl();
        cyclicCillection.add(item1);
        cyclicCillection.add(item2);
        cyclicCillection.add(item3);

        CyclicCollection restoredCollection = null;

        try (OutputStream out = new FileOutputStream(filePath)) {
            serializableUtils.serialize(out, cyclicCillection);
            out.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try (InputStream in = new FileInputStream(filePath)) {
            restoredCollection = (CyclicCollection) serializableUtils
                    .deserialize(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Original:");
        System.out.println(cyclicCillection);
        System.out.println("Restored:");
        System.out.println(restoredCollection);

        new File(filePath).delete();
    }
}
