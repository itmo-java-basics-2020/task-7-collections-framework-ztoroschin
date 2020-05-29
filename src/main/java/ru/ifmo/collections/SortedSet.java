package ru.ifmo.collections;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents sorted set of unique values.
 * create() returns a SortedSet instance with natural ordering. (i.e. from smallest to largest in case of integer numbers)
 * from() is used to create a SortedSet instance with given Comparator.
 * Instances of a class can be created using only these two methods above.
 * <p>
 * This class should not be abstract and should be capable of adding/removing single/multiple elements.
 * It has two more methods: getSorted() and getReversed() which return an array of set contents in forward and reverse order respectively.
 * <p>
 * NB! This class must have only map(s) as an internal data structure(s).
 *
 * @param <T> set contents type
 */
public class SortedSet<T> extends AbstractSet<T> {

    private final Map<T, Object> contents;

    private SortedSet() {
        contents = new TreeMap<>();
    }

    private SortedSet(Comparator<T> comparator) {
        contents = new TreeMap<>(comparator);
    }

    public static <T> SortedSet<T> create() {
        return new SortedSet<>();
    }

    public static <T> SortedSet<T> from(Comparator<T> comparator) {
        return new SortedSet<>(comparator);
    }

    public List<T> getSorted() {
        return new ArrayList<>(contents.keySet());
    }

    public List<T> getReversed() {
        return new ArrayList<>(((TreeMap) contents).descendingKeySet());
    }

    @Override
    public boolean add(T t) {
        return contents.put(t, 0) == null;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean itemAdded = false;
        for (var item : c) {
            itemAdded |= add(item);
        }
        return itemAdded;
    }

    @Override
    public boolean remove(Object o) {
        return contents.remove(o) != null;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean itemRemoved = false;
        for (var item : c) {
            itemRemoved |= remove(item);
        }
        return itemRemoved;
    }

    @Override
    public Iterator<T> iterator() {
        return contents.keySet().iterator();
    }

    @Override
    public int size() {
        return contents.size();
    }
}
