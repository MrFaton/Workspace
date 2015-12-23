package com.nixsolutions.laba4;

import com.nixsolutions.laba4.task3.MapUtilsImpl;
import interfaces.task4.MapUtils;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for MapUtilsImpl
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class MapUtilsImplTest {
    @Test
    public void testFindThrees() {
        MapUtils mapUtils = new MapUtilsImpl();
        String str = "ab ab1&136-ab14,851!";
        Map<String, Integer> threesMap = mapUtils.findThrees(str);

        assertTrue("Map size must be 4", 4 == threesMap.size());
        assertTrue("Must contains ab1", threesMap.containsKey("ab1"));
        assertEquals("ab1 must count 2", Integer.valueOf(2), threesMap.get("ab1"));
        assertTrue("Must contains 136", threesMap.containsKey("136"));
        assertEquals("136 must count 1", Integer.valueOf(1), threesMap.get("136"));
        assertTrue("Must contains b14", threesMap.containsKey("b14"));
        assertEquals("ab1 must count 1", Integer.valueOf(1), threesMap.get("b14"));
        assertTrue("Must contains 851", threesMap.containsKey("851"));
        assertEquals("ab1 must count 1", Integer.valueOf(1), threesMap.get("851"));
    }
}
