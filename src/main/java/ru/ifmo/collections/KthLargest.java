package ru.ifmo.collections;

import java.util.*;

/**
 * Design a class to find the kth largest element in a stream. k is from 1 to numbers.length.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Constructor accepts an integer k and an integer array numbers, which contains initial elements from the stream.
 * For each call to the method KthLargest.add(), return the element representing the kth largest element in the stream.
 */
public class KthLargest {

    private final TreeSet<Entry> multiset;
    private final int k;
    private int hash = 0;

    public KthLargest(int k, int[] numbers) {
        this.multiset = new TreeSet<>(Collections.reverseOrder());
        this.k = k;

        for (var number : numbers) {
            add(number);
        }
    }

    public int add(int value) {
        multiset.add(new Entry(value, hash++));

        if (k > multiset.size()) return -1;
        return multiset.stream()
                .skip(k - 1)
                .findFirst()
                .get()
                .getValue();
    }

    private static class Entry implements Comparable<Entry> {
        private final Integer value;
        private final Integer hash;

        private Entry(Integer value, Integer hash) {
            this.value = value;
            this.hash = hash;
        }

        private Integer getValue() {
            return value;
        }

        private Integer getHash() {
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(value, entry.value) &&
                    Objects.equals(hash, entry.hash);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, hash);
        }

        @Override
        public int compareTo(Entry o) {
            if (value < o.value || (value.equals(o.value) && hash < o.getHash())) {
                return -1;
            }
            return value.equals(o.value) && hash.equals(o.hash) ? 0 : 1;
        }
    }
}