package com.nixsolutions.laba4.task2;

import com.nixsolutions.laba4.task1.CollectionUtilsImpl;
import interfaces.task4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ponarin Igor
 * @since 15.12.2015
 */
public class Demo {
    public static void main(String[] args) {
        CollectionUtils collectionUtils = new CollectionUtilsImpl();
        List<Integer> listA = Arrays.asList(1, 5, 2, 9, 15, 22, 1);
        List<Integer> listB = Arrays.asList(3, 18, 1, 5, 9, 2, 8);

        System.out.println("Union: " + collectionUtils.union(listA, listB));
        System.out.println(
                "Intersection: " + collectionUtils.intersection(listA, listB));
        System.out.println("Intersection without duplicate: "
                + collectionUtils.intersectionWithoutDuplicate(listA, listB));
        System.out.println("Difference A - B: "
                + collectionUtils.difference(listA, listB));
    }
}
