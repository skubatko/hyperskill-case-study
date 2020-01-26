package ru.skubatko.dev.hyperskill.case2484;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Case2484 {

    public static void main(String[] args) {
        Multiset<String> multiset = new HashMultiset<>();
        multiset.add("aaa");
        multiset.add("aaa");
        System.out.println(multiset);
    }
}

interface Multiset<E> {

    /**
     * Add an element to the multiset.
     * It increases the multiplicity of the element by 1.
     */
    void add(E elem);

    /**
     * Remove an element from the multiset.
     * It decreases the multiplicity of the element by 1.
     */
    void remove(E elem);

    /**
     * Union this multiset with another one. The result is the modified multiset (this).
     * It will contain all elements that are present in at least one of the initial multisets.
     * The multiplicity of each element is equal to the maximum multiplicity of
     * the corresponding elements in both multisets.
     */
    void union(Multiset<E> other);

    /**
     * Intersect this multiset with another one. The result is the modified multiset (this).
     * It will contain all elements that are present in the both multisets.
     * The multiplicity of each element is equal to the minimum multiplicity of
     * the corresponding elements in the intersecting multisets.
     */
    void intersect(Multiset<E> other);

    /**
     * Returns multiplicity of the given element.
     * If the set doesn't contain it, the multiplicity is 0
     */
    int getMultiplicity(E elem);

    /**
     * Check the multiset contains an element,
     * i.e. the multiplicity > 0
     */
    boolean contains(E elem);

    /**
     * The number of unique elements
     */
    int numberOfUniqueElements();

    /**
     * The size of the multiset, including repeated elements
     */
    int size();

    /**
     * The set of unique elements (without repeating)
     */
    Set<E> toSet();
}

class HashMultiset<E> implements Multiset<E> {

    private Map<E, Integer> map = new HashMap<>();

    @Override
    public void add(E elem) {
        Integer value = map.putIfAbsent(elem, 1);
        if (value != null) {
            map.put(elem, value + 1);
        }
    }

    @Override
    public void remove(E elem) {
        Integer value = map.get(elem);
        if (value == null) {
            return;
        }

        if (value.equals(1)) {
            map.remove(elem);
            return;
        }

        map.put(elem, value - 1);
    }

    @Override
    public void union(Multiset<E> other) {
        for (E e : other.toSet()) {
            int otherValue = other.getMultiplicity(e);
            map.merge(e, otherValue, Integer::max);
        }
    }

    @Override
    public void intersect(Multiset<E> other) {
        for (E e : other.toSet()) {
            int otherValue = other.getMultiplicity(e);
            if (map.get(e) != null) {
                map.merge(e, otherValue, Integer::min);
            }
        }

        Set<E> keySet = new HashSet<>(map.keySet());
        for (E e : keySet) {
            if (!(other.contains(e))) {
                map.remove(e);
            }
        }
    }

    @Override
    public int getMultiplicity(E elem) {
        return map.getOrDefault(elem, 0);
    }

    @Override
    public boolean contains(E elem) {
        return map.containsKey(elem);
    }

    @Override
    public int numberOfUniqueElements() {
        return map.size();
    }

    @Override
    public int size() {
        return map.values().stream().reduce(Integer::sum).orElse(0);
    }

    @Override
    public Set<E> toSet() {
        return map.keySet();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HashMultiset)) {
            return false;
        }

        HashMultiset<?> that = (HashMultiset<?>) o;

        return map != null ? map.equals(that.map) : that.map == null;
    }

    @Override
    public int hashCode() {
        return map != null ? map.hashCode() : 0;
    }
}
