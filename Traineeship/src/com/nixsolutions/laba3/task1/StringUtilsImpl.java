package com.nixsolutions.laba3.task1;

import interfaces.task3.StringUtils;

import java.util.Arrays;

/**
 *
 * @author igor ponarin
 * @since 14.12.2015
 */
public class StringUtilsImpl implements StringUtils {
    @Override
    public String invert(String s) {
        if (s == null)
            return "";
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = temp;
        }

        return new String(chars);
    }

    @Override
    public String compareWords(String s, String s1) {
        if (s == null || s1 == null) {
            throw new NullPointerException("One or two arguments are null: "
                    + "s=" + s + " s1=" + s1);
        }
        char[] baseChars = s.toCharArray();
        char[] checkedChars = s1.toCharArray();

        char[] resultChars = new char[baseChars.length];
        int match = 0;

        Arrays.sort(checkedChars);

        for (char searchedChar : baseChars) {
            if (Arrays.binarySearch(checkedChars, searchedChar) < 0) {
                resultChars[match] = searchedChar;
                match++;
            }
        }
        return new String(resultChars, 0, match);
    }

    @Override
    public double parseDouble(String s) {
        if (s == null) {
            throw new NullPointerException("Argument is null");
        }
        String[] tokens = s.split(" ");
        String numStr = tokens[0];
        try {
            return Double.parseDouble(numStr);
        } catch (NumberFormatException numberEx) {
            throw new IllegalArgumentException(numberEx);
        }
    }
}
