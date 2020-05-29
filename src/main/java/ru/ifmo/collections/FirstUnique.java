package ru.ifmo.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Design a class which contains integers and returns first unique integer (in order of addition).
 * FirstUniqueTest can be used as an example.
 */
public class FirstUnique {

    private final Map<Integer, Integer> map;

    public FirstUnique(int[] numbers) {
        map = new LinkedHashMap<>();
        for (var number : numbers) {
            add(number);
        }
    }

    public int showFirstUnique() {
        for (var item : map.entrySet()) {
            if (item.getValue().equals(1)) {
                return item.getKey();
            }
        }
        return -1;
    }

    public void add(int value) {
        map.compute(value, (key, val)
                -> (val == null)
                    ? 1
                    : val + 1);
    }
}
