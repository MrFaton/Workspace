package com.nixsolutions.laba8.task2;

import java.io.Serializable;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;

public class CyclicCollectionImpl implements CyclicCollection, Serializable {
    private static final long serialVersionUID = -4688187498299657380L;
    private CyclicItem collectionItem;
    private int size = 0;

    @Override
    public boolean add(CyclicItem item) {
        if (item == null) {
            throw new NullPointerException("Argument is null");
        }

        if (collectionItem == null) {
            collectionItem = item;
            item.setNextItem(collectionItem);
            size++;
            return true;
        }

        if (contains(item)) {
            throw new IllegalArgumentException("Item already added");
        }

        CyclicItem last = getLast();
        last.setNextItem(item);
        item.setNextItem(collectionItem);
        size++;
        return true;
    }

    @Override
    public CyclicItem getFirst() {
        return collectionItem;
    }

    @Override
    public void insertAfter(CyclicItem item, CyclicItem newItem) {
        if (item == null || newItem == null) {
            throw new NullPointerException("One or two arguments are null! "
                    + "item=" + item + " newItem=" + newItem);
        }
        if (!contains(item)) {
            throw new IllegalArgumentException(
                    "Collection not contains " + item);
        }
        if (contains(newItem)) {
            throw new IllegalArgumentException("newItem already added");
        }

        if (item.equals(collectionItem)) {
            newItem.setNextItem(collectionItem.nextItem());
            collectionItem.setNextItem(newItem);

            // newItem.setNextItem(collectionItem);
            // getLast().setNextItem(newItem);
            // collectionItem = newItem;
            size++;
        }

        CyclicItem lastItem = getLast();
        if (lastItem.equals(item)) {
            lastItem.setNextItem(newItem);
            newItem.setNextItem(collectionItem);
            size++;
        }

        CyclicItem curItem = collectionItem.nextItem();
        while (curItem != collectionItem) {
            if (curItem.equals(item)) {
                newItem.setNextItem(item.nextItem());
                item.setNextItem(newItem);
                size++;
                break;
            } else {
                curItem = curItem.nextItem();
            }
        }
    }

    @Override
    public boolean remove(CyclicItem item) {
        boolean modified = false;
        if (item == null) {
            throw new NullPointerException("Argument is null");
        }

        if (collectionItem == null) {
            return false;
        } else if (collectionItem.equals(item)) {
            collectionItem = collectionItem.nextItem();
            size--;
            modified = true;
        } else {
            CyclicItem curItem = collectionItem.nextItem();
            CyclicItem prevItem = collectionItem;
            while (curItem != collectionItem) {
                if (item.equals(curItem)) {
                    prevItem.setNextItem(curItem.nextItem());
                    size--;
                    modified = true;
                    break;
                }
                prevItem = curItem;
                curItem = curItem.nextItem();
            }
        }

        if (size == 0) {
            collectionItem = null;
        }
        return modified;
    }

    @Override
    public int size() {
        // if (collectionItem == null) {
        // return 0;
        // }
        //
        // int counter = 1;
        // CyclicItem curItem = collectionItem.nextItem();
        // while (curItem != collectionItem) {
        // counter ++;
        // curItem = curItem.nextItem();
        // }
        // return counter;
        return size;
    }

    public boolean contains(CyclicItem item) {
        if (collectionItem == null) {
            return false;
        }
        CyclicItem curItem = collectionItem;
        CyclicItem nextItem;

        do {
            if (curItem.equals(item)) {
                return true;
            }
            nextItem = curItem.nextItem();
            curItem = nextItem;
        } while (curItem != collectionItem);
        return false;
    }

    public CyclicItem getLast() {
        if (size() == 1) {
            return collectionItem;
        }
        CyclicItem curItem = collectionItem.nextItem();
        CyclicItem prevItem = collectionItem;
        while (!curItem.equals(collectionItem)) {
            prevItem = curItem;
            curItem = curItem.nextItem();
        }
        return prevItem;
    }

    @Override
    public String toString() {
        String text;
        text = collectionItem.toString() + "\n";
        CyclicItem curItem = collectionItem.nextItem();
        while (curItem != collectionItem) {
            text += curItem.toString() + "\n";
            curItem = curItem.nextItem();
        }
        return text;
    }

}