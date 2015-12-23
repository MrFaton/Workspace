package com.nixsolutions.laba3.task3;

import com.nixsolutions.laba3.task1.StringUtilsImpl;
import interfaces.task3.StringUtils;

/**
 * @author Ponarin Igor
 * @since 14.12.2015
 */
public class Demo {
    public static void main(String[] args) {
        StringUtils stringUtils = new StringUtilsImpl();
        String word1 = "yellow";
        String word2 = "apple";
        System.out.println(stringUtils.compareWords(word1, word2));
    }
}
