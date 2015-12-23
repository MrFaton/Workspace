package com.nixsolutions.laba10.task2;

import com.nixsolutions.laba5.task2.ArrayCollectionImpl;
import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ponarin Igor
 * @since 19.12.2015
 */
public class ArrayIteratorTest extends TestCase {
    private ArrayCollection<Long> collection;

    @Override
    protected void setUp() throws Exception {
        collection = new ArrayCollectionImpl<>();
    }

    public void testGetIterator() {
        Long[] array = new Long[4];
        array[0] = 1L;
        array[1] = 8L;
        array[2] = -100L;
        array[3] = null;
        
        collection.setArray(array);
        
        Iterator<Long> iterator = collection.iterator();
        assertNotNull("Iterator can't be null", iterator);
        assertTrue("Iterator must be ArrayIterator",
                iterator instanceof ArrayIterator);
    }

    public void testHasNext() {
        Iterator<Long> iterator = collection.iterator();
        assertNotNull("At the empty collection Iterator can't be null",
                iterator);
        assertFalse("At the empty collection hasNext() must return false",
                iterator.hasNext());

        Long[] array = new Long[1];
        array[0] = 1L;
        collection.setArray(array);
        assertTrue("Must has next", iterator.hasNext());
        
        collection.setArray(new Long[0]);
        assertFalse("Collection has empty array", iterator.hasNext());
    }

    public void testNext() {
        Iterator<Long> iterator = collection.iterator();
        try {
            iterator.next();
            fail("Iterator should throw NoSuchElementException");
        } catch (NoSuchElementException elementEx) {
        } catch (Exception ex) {
            fail("Iterator should throw NoSuchElementException");
        }

        Long[] array = new Long[4];
        array[0] = 5L;
        array[1] = -3L;
        array[2] = 8L;
        array[3] = 10L;
        collection.setArray(array);
        
        iterator = collection.iterator();
        assertNotNull("Iterator must has next", iterator.next());
        assertNotNull("Iterator must has next", iterator.next());
        assertNotNull("Iterator must has next", iterator.next());
        assertNotNull("Iterator must has next", iterator.next());
        try {
            iterator.next();
            fail("Iterator should throw NoSuchElementException");
        } catch (NoSuchElementException elementEx) {
        } catch (Exception ex) {
            fail("Iterator should throw NoSuchElementException");
        }
    }

    public void testGetArray() {
        Iterator<Long> iterator = collection.iterator();
        ArrayIterator<Long> arrayIterator = (ArrayIterator) iterator;
        assertEquals("Array size should be 0 in a new collection", 0,
                arrayIterator.getArray().length);

        Long[] array = new Long[3];
        array[0] = null;
        array[1] = 2L;
        array[2] = -9L;
        collection.setArray(array);
        
        arrayIterator = (ArrayIterator) collection.iterator();

        Object[] actual = arrayIterator.getArray();

        assertEquals("Size must equals", array.length, actual.length);

        assertEquals("Elements should equals", array[0], actual[0]);
        assertEquals("Elements should equals", array[1], actual[1]);
        assertEquals("Elements should equals", array[2], actual[2]);
    }
}
