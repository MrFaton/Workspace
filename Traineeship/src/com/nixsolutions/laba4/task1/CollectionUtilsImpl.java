package com.nixsolutions.laba4.task1;

import interfaces.task4.CollectionUtils;

import java.util.*;

/**
 * @author Ponarin Igor
 * @since 15.12.2015
 */
public class CollectionUtilsImpl implements CollectionUtils {
    @Override
    public List<Integer> union(Collection<Integer> collection,
            Collection<Integer> collection1) {
        if (collection == null || collection1 == null) {
            throw new NullPointerException(
                    "One or two arguments are null! " + "collection="
                            + collection + " collection1=" + collection1);
        }

        List<Integer> result = new ArrayList<>(
                collection.size() + collection1.size());
        result.addAll(collection);
        result.addAll(collection1);
        return result;
    }

    @Override
    public List<Integer> intersection(Collection<Integer> collection,
            Collection<Integer> collection1) {
        if (collection == null || collection1 == null) {
            throw new NullPointerException(
                    "One or two arguments are null! " + "collection="
                            + collection + " collection1=" + collection1);
        }

        List<Integer> result = new LinkedList<>();
        result.addAll(collection);
        result.retainAll(collection1);
        return result;
    }

    @Override
    public Set<Integer> intersectionWithoutDuplicate(

            Collection<Integer> collection, Collection<Integer> collection1) {
        if (collection == null || collection1 == null) {
            throw new NullPointerException(
                    "One or two arguments are null! " + "collection="
                            + collection + " collection1=" + collection1);
        }

        Set<Integer> result = new LinkedHashSet<>();
        result.addAll(collection);
        result.retainAll(collection1);
        return result;

    }

    @Override
    public Collection<Integer> difference(Collection<Integer> collection,
            Collection<Integer> collection1) {
        if (collection == null || collection1 == null) {
            throw new NullPointerException(
                    "One or two arguments are null! " + "collection="
                            + collection + " collection1=" + collection1);
        }

        Collection<Integer> result = new LinkedList<>();
        result.addAll(collection);
        result.removeAll(collection1);
        return result;
    }
}
