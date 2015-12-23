package com.nixsolutions.laba3;

import com.nixsolutions.laba3.task1.StringUtilsImpl;
import interfaces.task3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for StringUtilsImpl
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class StringUtilsImplTest {
    private StringUtils utils;

    @Before
    public void setUp() {
        utils = new StringUtilsImpl();
    }

    @Test
    public void testInvert() {
        String original = "Hello";
        String expected = "olleH";
        String actual = utils.invert(original);
        assertEquals("String not invert", expected, actual);
    }

    @Test
    public void testCompareWords() {
        String str1 = "blue";
        String str2 = "yellow";

        String result = utils.compareWords(str1, str2);
        assertTrue("String size must be 2", 2 == result.length());
        assertTrue("String must contains b", result.contains("b"));
        assertTrue("String must contains u", result.contains("u"));
    }

    @Test
    public void testParseDouble() {
        String str1 = "3.54";
        String str2 = "0.01 Low";
        String str3 = "G 3.54";

        assertTrue("srt1 = 3.54", 3.54 == utils.parseDouble(str1));
        assertTrue("srt1 = 0.01", 0.01 == utils.parseDouble(str2));

        try {
            utils.parseDouble(str3);
            fail("Can't parse");
        } catch (IllegalArgumentException argumentEx) {
        } catch (Exception ex) {
            fail("Must throw IllegalArgumentException");
        }

        try {
            utils.parseDouble(null);
        } catch (NullPointerException ex) {
        }
    }
}
