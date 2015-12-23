package com.nixsolutions.laba3.task4;

import com.nixsolutions.laba3.task1.StringUtilsImpl;
import interfaces.task3.StringUtils;

/**
 * @author Ponarin Igor
 * @since 14.12.2015
 */
public class Demo {
    public static void main(String[] args) {
        StringUtils stringUtils = new StringUtilsImpl();
        String strNumber1 = "12 op";
        String strNumber2 = "4k";

        try {
            double d1 = stringUtils.parseDouble(strNumber1);
            double d2 = stringUtils.parseDouble(strNumber2);
        } catch (IllegalArgumentException badArgument) {
            System.err.println("Can't convert string to double!");
            System.err.println("Cause: " + badArgument);
            System.err.println("Stack trace: ");
            badArgument.printStackTrace();
        }
    }
}
