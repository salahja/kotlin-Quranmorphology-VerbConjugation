package org.sj.verbConjugation.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderedMap extends HashMap {
    protected List<Object> orderedKeys = new LinkedList<>();

    public OrderedMap() {
    }

    /**
     * according to the order of put method a list of keys is ccreated
     *
     * @param key   Object
     * @param value Object
     * @return Object
     */
    public Object put(Object key, Object value) {
        orderedKeys.add(key);
        return super.put(key, value);
    }

    public Object remove(Object key) {
        orderedKeys.remove(key);
        return super.remove(key);
    }

    public List<Object> getOrderedKeys() {
        return orderedKeys;
    }

}
