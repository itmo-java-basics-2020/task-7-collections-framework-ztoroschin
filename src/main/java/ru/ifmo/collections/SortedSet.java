package ru.ifmo.collections;

import java.util.*;

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
        List<T> arrayList = new ArrayList<>(contents.keySet());
        Collections.reverse(arrayList);
        return arrayList;
    }

    @Override
    public boolean add(T t) {
        boolean notContainsKey = !contents.containsKey(t);
        if (notContainsKey) {
            contents.put(t, 0);
        }
        return notContainsKey;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean itemAdded = false;
        for (var item : c) {
            if (add(item)) {
                itemAdded = true;
            }
        }
        return itemAdded;
    }

    @Override
    public boolean remove(Object o) {
        boolean containsKey = contents.containsKey(o);
        if (containsKey) {
            contents.remove(o);
        }
        return containsKey;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean itemRemoved = false;
        for (var item : c) {
            if (remove(item)) {
                itemRemoved = true;
            }
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
