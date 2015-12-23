package com.nixsolutions.laba4.task4;

import com.nixsolutions.laba4.task3.MapUtilsImpl;
import interfaces.task4.MapUtils;

import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        MapUtils mapUtils = new MapUtilsImpl();

        Map<String, Integer> threeMap = mapUtils
                .findThrees(" 12d=34512d+4_drz%12d7451 2d7");

        for (Map.Entry<String, Integer> entry : threeMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
