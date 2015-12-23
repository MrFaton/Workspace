package com.nixsolutions.laba5.task2;

import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollectionImpl<E> implements ArrayCollection<E> {
    private E[] elements;

    public ArrayCollectionImpl() {
        elements = createNewArray(0);
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return elements.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == null) {
                    return true;
                }
            }
        } else {
            for (E element : elements) {
                if (element == null) {
                    continue;
                }
                if (element.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIteratorImpl<E>();
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[elements.length];
        System.arraycopy(elements, 0, resultArray, 0, elements.length);
        return resultArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Argument is null");
        }

        int nativeSize = elements.length;
        int externalSize = a.length;
        if (externalSize >= nativeSize) {
            System.arraycopy(elements, 0, a, 0, nativeSize);
            return a;
        } else {
            T[] resultArray = createNewArray(a, elements.length);
            System.arraycopy(elements, 0, resultArray, 0, nativeSize);
            return resultArray;
        }
    }

    @Override
    public boolean add(E e) {
        E[] updatedArray = createNewArray(elements.length + 1);
        System.arraycopy(elements, 0, updatedArray, 0, elements.length);
        updatedArray[updatedArray.length - 1] = e;
        elements = updatedArray;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Argument is null");
        }

        for (int i = 0; i < elements.length; i++) {
            E element = elements[i];
            if (element == null) {
                continue;
            }
            if (element.equals(o)) {
                int updatedSize = elements.length - 1;
                E[] updatedArray = createNewArray(elements.length - 1);
                if (i == 0) {
                    System.arraycopy(elements, 1, updatedArray, 0, updatedSize);
                } else if (i == elements.length - 1) {
                    System.arraycopy(elements, 0, updatedArray, 0, updatedSize);
                } else {
                    System.arraycopy(elements, 0, updatedArray, 0, i);
                    System.arraycopy(elements, i + 1, updatedArray, i,
                            updatedSize - i);
                }
                elements = updatedArray;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Argument is null");
        }
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Argument is null");
        }

        if (c == this) {
            throw new IllegalArgumentException();
        }

        int nativSize = elements.length;
        int externalSize = c.size();
        E[] updatedArray = createNewArray(elements.length + c.size());
        System.arraycopy(elements, 0, updatedArray, 0, nativSize);
        E[] externalArray = (E[]) c.toArray();
        System.arraycopy(externalArray, 0, updatedArray, nativSize,
                externalSize);
        elements = updatedArray;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Argument is null");
        }

        boolean modified = false;

        int[] posToDel = new int[elements.length];
        int lastPos = 0;

        for (int i = 0; i < elements.length; i++) {
            if (c.contains(elements[i])) {
                posToDel[lastPos] = i;
                lastPos++;
            }
        }

        if (lastPos > 0) {
            modified = true;
            E[] updatedArray = createNewArray(elements.length - lastPos);
            int lastCheckedPos = 0;
            int lastAddedPos = 0;
            for (int pos = 0; pos < elements.length; pos++) {
                if (pos != posToDel[lastCheckedPos]) {
                    updatedArray[lastAddedPos] = elements[pos];
                    lastAddedPos++;
                } else {
                    lastCheckedPos++;
                }
            }
            elements = updatedArray;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Argument is null");
        }

        boolean modified = false;

        E[] tempArray = createNewArray(elements.length);
        int lastAddedPosition = 0;
        for (E element : elements) {
            if (c.contains(element)) {
                tempArray[lastAddedPosition] = element;
                lastAddedPosition++;
            }
        }

        if (lastAddedPosition > 0) {
            modified = true;
            E[] updatedArray = createNewArray(lastAddedPosition);
            System.arraycopy(tempArray, 0, updatedArray, 0, lastAddedPosition);
            elements = updatedArray;
        }
        return modified;
    }

    @Override
    public void clear() {
        elements = createNewArray(0);
    }

    @Override
    public Object[] getArray() {
        Object[] array = new Object[elements.length];
        System.arraycopy(elements, 0, array, 0, elements.length);
        return array;
    }

    @Override
    public void setArray(E[] arg0) {
        if (arg0 == null) {
            throw new NullPointerException("Argument is null");
        }
        elements = arg0;
    }

    @SuppressWarnings("unchecked")
    private <E> E[] createNewArray(int capacity) {
        return (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    private <T> T[] createNewArray(T[] array, int capacity) {
        return (T[]) new Object[capacity];
    }

    @Override
    public String toString() {
        String str = "ArrayCollectionImpl: [";
        for (E element : elements) {
            str += element + ", ";
        }
        int lastCommaPos = str.lastIndexOf(",");
        str = str.substring(0, lastCommaPos);
        str = str + "]";
        return str;
    }

    public class ArrayIteratorImpl<E> implements ArrayIterator<E> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < elements.length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (pos >= elements.length) {
                throw new NoSuchElementException();
            }
            return (E) elements[pos++];
        }

        @Override
        public Object[] getArray() {
            if (elements == null) {
                return null;
            }
            Object[] array = new Object[elements.length];
            System.arraycopy(elements, 0, array, 0, elements.length);
            return array;
        }
    }
}
