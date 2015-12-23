package com.nixsolutions.laba3.task5;

import com.nixsolutions.laba3.task1.StringDivImpl;
import interfaces.task3.StringDiv;

/**
 * @author Ponarin Igor
 * @since 15.12.2015
 */
public class Demo {
    public static void main(String[] args) {
        StringDiv stringDiv = new StringDivImpl();
        String s1 = "18.8 da";
        String s2 = "2.1";
        System.out.println(stringDiv.div(s1, s2));
    }
}
