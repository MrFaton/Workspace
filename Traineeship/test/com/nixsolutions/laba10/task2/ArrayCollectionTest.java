package com.nixsolutions.laba10.task2;

import com.nixsolutions.laba5.task2.ArrayCollectionImpl;
import interfaces.task5.ArrayCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Ponarin Igor
 * @since 19.12.2015
 */
public class ArrayCollectionTest {
    private ArrayCollection<Integer> collection;

    @Before
    public void setUp() {
        collection = new ArrayCollectionImpl<>();
    }

    @Test
    public void testAdd() {
        assertTrue("Collection must modified", collection.add(0));

        assertEquals("Size must be 1 after adding 1 element", 1,
                collection.getArray().length);

        assertTrue("Null should be added", collection.add(null));

        assertEquals("Size must be 2 after adding null element", 2,
                collection.getArray().length);

        Object[] expected = toArray(0, null);
        Object[] actual = collection.getArray();
        assertArrayEquals("Arrays must equals", expected, actual);
    }

    @Test
    public void testAddAll() {
        List<Integer> forenList = Arrays.asList(3, null, 4);

        assertTrue(collection.addAll(forenList));

        assertEquals("Size must be 3", 3, collection.getArray().length);

        assertNotNull(collection.getArray());
    }

    @Test
    public void testClear() {
        Integer[] array = toArray(null, 5);
        collection.setArray(array);
        assertEquals("Size must be 2", 2, collection.getArray().length);
        collection.clear();
        assertEquals("Size must be 0 after cleaning", 0,
                collection.getArray().length);
    }

    @Test
    public void testIsEmpty() {
        assertTrue("New collection must be empty", collection.isEmpty());

        Integer[] array = toArray(5);
        collection.setArray(array);
        assertEquals("Size must be 1", 1, collection.getArray().length);

        collection.setArray(new Integer[0]);
        assertTrue("Collection must be clear", collection.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse("2 is not exists", collection.contains(2));

        Integer[] array = toArray(1, 2, null, 3, null);
        collection.setArray(array);
        assertTrue("Must contains 2", collection.contains(2));
    }

    @Test
    public void testToArray() {
        Integer[] array = toArray(1, 2, null, 3, 4);
        collection.setArray(array);

        Object[] expectedArray = toArray(1, 2, null, 3, 4);
        Object[] actualArray = collection.toArray();

        assertNotNull("Array must be not null", actualArray);
        assertArrayEquals("Arrays must equals", expectedArray, actualArray);
    }

    @Test
    public void testToArray2() {
        Integer[] array = toArray(1, 5, -6, 8, null);
        collection.setArray(array);
        assertNotNull("get Long's array", collection.toArray(new Long[1]));
    }

    @Test(expected = NullPointerException.class)
    public void removeNull() {
        Integer[] array = toArray(1, null, 2);
        collection.setArray(array);
        collection.remove(null);
    }

    @Test
    public void testRemove() {
        Integer[] array = toArray(1, 3, null, 3, 8);
        collection.setArray(array);
        assertTrue("collection must modified", collection.remove(3));
        assertTrue("Collection must remove 3", collection.remove(3));
        assertFalse("Collection not modified", collection.remove(3));
    }

    @Test(expected = NullPointerException.class)
    public void containsAllBad() {
        Integer[] array = toArray(1, 5, 7, null);
        collection.setArray(array);
        collection.containsAll(null);
    }

    @Test
    public void testContainsAll() {
        Integer[] array = toArray(1, 2, 3, 4, 5, 6, null, null, 7);
        collection.setArray(array);

        List<Integer> good = Arrays.asList(2, 4, null, 1, 4);
        assertTrue("Must contains", collection.containsAll(good));

        List<Integer> bad = Arrays.asList(3, null, 11);
        assertFalse("Wrong answer", collection.containsAll(bad));
    }

    @Test(expected = NullPointerException.class)
    public void removeAllBad() {
        Integer[] array = toArray(1, 4, null);
        collection.setArray(array);
        collection.removeAll(null);
    }

    @Test
    public void testRemoveAll() {
        Integer[] array = toArray(5, 5, 2, 0, 7, 3, 1);
        collection.setArray(array);

        List<Integer> good = Arrays.asList(5, 3);
        assertTrue("Collection must modified", collection.removeAll(good));

        Object[] actualArray = collection.getArray();
        for (Object elemet : actualArray) {
            int item = Integer.valueOf(elemet.toString());
            if (item == 3 || item == 5) {
                fail("3 and 5 mus be deleted");
            }
        }

        List<Integer> bad = Arrays.asList(4, 8, 11);
        assertFalse("Collection is modifies", collection.removeAll(bad));
    }

    @Test(expected = NullPointerException.class)
    public void retainAllBad() {
        Integer[] array = toArray(4, 8, null, 1, -7, 3);
        collection.setArray(array);
        collection.retainAll(null);
    }

    @Test
    public void testRetainAll() {
        Integer[] array = toArray(4, 8, null, 1, -7, 3, 3, 4);
        collection.setArray(array);
        List<Integer> list = Arrays.asList(null, 4);

        assertTrue("Collection must modified", collection.retainAll(list));
        assertEquals("After operation size must be 3", 3,
                collection.getArray().length);

        Object[] resultArray = collection.getArray();
        boolean containsNull = false;
        for (Object element : resultArray) {
            if (element == null) {
                containsNull = true;
            } else {
                int item = Integer.valueOf(element.toString());
                if (item != 4) {
                    fail("Num must be 4");
                }
            }
        }
        if (!containsNull) {
            fail("Must contains null");
        }
    }

    @Test
    public void testGetArray() {
        Integer[] array = toArray(7, -1, 0, null, 5, 12);
        collection.setArray(array);

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
