package com.nixsolutions.laba4;

import com.nixsolutions.laba4.task1.CollectionUtilsImpl;
import interfaces.task4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for CollectionUtilsImpl
 *
 * @author Mr_Faton
 * @since 20.12.2015
 */
public class CollectionUtilsImplTest {
    private CollectionUtils utils;
    private Collection<Integer> col1;
    private Collection<Integer> col2;
    Collection<Integer> result;

    @Before
    public void setUp() {
        utils = new CollectionUtilsImpl();
        col1 = null;
        col2 = null;
        result = null;
    }

    @Test
    public void unionEmpty() {
        col1 = new ArrayList<>();
        col2 = Arrays.asList(1, 5, -9);

        result = utils.union(col1, col2);
        assertTrue("collection size must be 3", 3 == result.size());
    }

    @Test
    public void testUnion() {
        col1 = Arrays.asList(null, 3, 89);
        col2 = Arrays.asList(1, 5);

        result = utils.union(col1, col2);
        assertTrue("collection size must be 5", 5 == result.size());
    }

    @Test
    public void testIntersection() {
        col1 = Arrays.asList(1, 3, 5, 1, 6);
        col2 = Arrays.asList(1, 5, 4);

        result = utils.intersection(col1, col2);

        assertTrue("Must contains 1", result.contains(1));
        assertTrue("Must contains 5", result.contains(5));

        assertFalse("3 must deleted", result.contains(3));
        assertFalse("4 must deleted", result.contains(4));
        assertFalse("6 must deleted", result.contains(6));
    }

    @Test
    public void testIntersectionWithoutDuplicate() {
        col1 = Arrays.asList(2, 11, 15, 3, 5, 11, 3);
        col2 = Arrays.asList(3, 15, 3, 1);

        result = utils.intersectionWithoutDuplicate(col1, col2);
        assertTrue("Size must be 2", 2 == result.size());
        assertTrue("Must contains 3", result.contains(3));
        assertTrue("Must contains 15", result.contains(15));
    }

    @Test
    public void testDifference() {
        col1 = Arrays.asList(2, 5, 7, 8, 2, 8);
        col2 = Arrays.asList(8, 2, 4);

        result = utils.difference(col1, col2);
        assertTrue("Size must be 2", 2 == result.size());
        assertTrue("Must containce 5", result.contains(5));
        assertTrue("Must containce 7", result.contains(7));
    }
}
