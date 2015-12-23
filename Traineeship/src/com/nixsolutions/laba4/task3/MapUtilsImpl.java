package com.nixsolutions.laba4.task3;

import interfaces.task4.MapUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtilsImpl implements MapUtils {

    @Override
    public Map<String, Integer> findThrees(String arg0) {
        if (arg0 == null) {
            throw new NullPointerException("Argument is null");
        }
        String regEx = "[A-Za-z0-9]{3}";
        Map<String, Integer> threesMap = new LinkedHashMap<>();
        int limit = arg0.length() - 3;
        for (int i = 0; i <= limit; i++) {
            String subStr = arg0.substring(i, i + 3);
            if (subStr.matches(regEx)) {
                if (threesMap.containsKey(subStr)) {
                    int count = threesMap.get(subStr);
                    count++;
                    threesMap.put(subStr, count);
                } else {
                    threesMap.put(subStr, 1);
                }
            }
        }
        return threesMap;
    }
}
