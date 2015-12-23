package com.nixsolutions.laba3;

import com.nixsolutions.laba3.task1.StringDivImpl;
import interfaces.task3.StringDiv;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Testing class for StringDivImpl
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class StringDivImplTest {
    @Test
    public void testDiv() {
        StringDiv stringDiv = new StringDivImpl();

        String argA = "5";
        String argB = "2.5 Z";

        double expected = 2.0;
        double actual = stringDiv.div(argA, argB);

        assertTrue("5 / 2.5 = 2.0", expected == actual);
    }
}
