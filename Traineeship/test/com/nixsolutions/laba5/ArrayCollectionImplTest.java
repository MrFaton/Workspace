package com.nixsolutions.laba5;

import com.nixsolutions.laba5.task2.ArrayCollectionImpl;
import interfaces.task5.ArrayCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class for testing ArrayCollectionImpl class
 *
 * @author Ponarin Igor
 * @since 19.12.2015
 */
public class ArrayCollectionImplTest {
    private ArrayCollection<Integer> collection;

    @Before
    public void setUp() {
        collection = new ArrayCollectionImpl<>();
    }

    @Test
    public void testAdd() {
        assertTrue("Collection must modified", collection.add(0));
        assertEquals("Size must be 1 after adding 1 element", 1,
                collection.size());
        assertTrue("Null should be added", collection.add(null));
        assertEquals("Size must be 2 after adding null element", 2,
                collection.size());

        Integer[] expected = toArray(0, null);

        Integer[] actual = collection.toArray(new Integer[2]);
        assertArrayEquals("Arrays must equals", expected, actual);
    }

    @Test
    public void testAddAll() {
        List<Integer> forenList = Arrays.asList(3, null, 4);

        assertTrue(collection.addAll(forenList));
        assertEquals("Size must be 3", 3, collection.size());

        assertNotNull(collection.toArray());
    }

    @Test
    public void testClear() {
        collection.add(null);
        collection.add(5);
        assertEquals("Size must be 2", 2, collection.size());
        collection.clear();
        assertEquals("Size must be 0 after cleaning", 0, collection.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue("New collection must be empty", collection.isEmpty());
        assertTrue("Must be true after add an element", collection.add(null));
        assertEquals("Size must be 1", 1, collection.size());
        collection.clear();
        assertTrue("Collection must be clear", collection.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue("Must add elements", collection.addAll(Arrays.asList(1, 2, null, 3, null)));
        assertTrue("Must contains 2", collection.contains(2));
        assertTrue("Need to remove 2", collection.remove(2));
        assertFalse("2 is not exists", collection.contains(2));
        assertTrue("Null must exists", collection.contains(null));
    }

    @Test
    public void testToArray() {
        assertTrue("Add collection", collection.addAll(Arrays.asList(1, 2, null, 3, 4)));
        Object[] expectedArray = toArray(1, 2, null, 3, 4);
        Object[] actualArray = collection.toArray();
        assertNotNull("Array must be not null", actualArray);
        assertArrayEquals("Arrays must equals", expectedArray, actualArray);
    }

    @Test
    public void testToArray2() {
        collection.addAll(Arrays.asList(1, 5, -6, 8, null));
        assertNotNull("get Long's array", collection.toArray(new Long[1]));
    }

    @Test(expected = NullPointerException.class)
    public void removeNull() {
        collection.add(1);
        collection.add(null);
        collection.add(2);
        collection.remove(null);
    }

    @Test
    public void testRemove() {
        collection.addAll(Arrays.asList(1, 3, null, 3, 8));
        assertTrue("collection must modified", collection.remove(3));
        assertTrue("Collection must remove 3", collection.remove(3));
        assertFalse("Collection not modified", collection.remove(3));
    }

    @Test(expected = NullPointerException.class)
    public void containsAllBad() {
        collection.addAll(Arrays.asList(1, 5, 7));
        collection.containsAll(null);
    }

    @Test
    public void testContainsAll() {
        collection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, null, null, 7));
        List<Integer> good = Arrays.asList(2, 4, null, 1, 4);
        assertTrue("Must contains", collection.containsAll(good));

        List<Integer> bad = Arrays.asList(3, null, 11);
        assertFalse("Wrong answer", collection.containsAll(bad));
    }

    @Test(expected = NullPointerException.class)
    public void removeAllBad() {
        collection.addAll(Arrays.asList(1, 4, null));
        collection.removeAll(null);
    }

    @Test
    public void testRemoveAll() {
        collection.addAll(Arrays.asList(5, 5, 2, null, 7, 3, 1));
        List<Integer> good = Arrays.asList(5, 3);
        assertTrue("Collection must modified", collection.removeAll(good));
        assertFalse("5 must not exists", collection.contains(5));
        assertFalse("3 must not exists", collection.contains(3));

        List<Integer> bad = Arrays.asList(4, 8, 11);
        assertFalse("Collection is modifies", collection.removeAll(bad));
    }

    @Test(expected = NullPointerException.class)
    public void retainAllBad() {
        collection.addAll(Arrays.asList(4, 8, null, 1, -7, 3));
        collection.retainAll(null);
    }

    @Test
    public void testRetainAll() {
        collection.addAll(Arrays.asList(4, 8, null, 1, -7, 3, 3, 4));
        List<Integer> list = Arrays.asList(null, 4);
        assertTrue("Collection must modified", collection.retainAll(list));
        assertEquals("After operation size must be 3", 3, collection.size());
        assertTrue("Must have 4", collection.contains(4));
        assertTrue("Must contains null", collection.contains(null));
    }

    @Test
    public void testGetArray() {
        collection.addAll(Arrays.asList(7, -1, 0, null, 5, 12));
        Object[] expected = toArray(7, -1, 0, null, 5, 12);
        Object[] actual = collection.getArray();
        assertNotNull("Can't be null", actual);
        assertArrayEquals("Arrays must equals", expected, actual);
    }

    @Test
    public void testSetArray() {
        try {
            collection.setArray(null);
            fail("Must throw NullPointerException");
        } catch (NullPointerException ex) {
        }

        Integer[] original = toArray(1, 6, -4, null, 1);
        collection.setArray(original);
        Object[] actual = collection.getArray();
        assertArrayEquals("Arrays must equals", original, actual);
    }

    private Integer[] toArray(Integer... arr) {
        return arr;
    }
}
