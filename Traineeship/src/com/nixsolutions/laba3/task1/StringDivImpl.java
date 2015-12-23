package com.nixsolutions.laba3.task1;

import interfaces.task3.StringDiv;
import interfaces.task3.StringUtils;

/**
 * @author igor ponarin
 * @since 14.12.2015
 */
public class StringDivImpl implements StringDiv {
    @Override
    public double div(String s, String s1) {
        if (s == null || s1 == null) {
            throw new NullPointerException("One or two arguments are null: "
                    + "s=" + s + "; s1=" + s1);
        }
        
        StringUtils stringUtils = new StringUtilsImpl();
        double a = stringUtils.parseDouble(s);
        double b = stringUtils.parseDouble(s1);
        if (b == 0.0)
            throw new ArithmeticException("Divisor = 0");
        return a / b;
    }
}
