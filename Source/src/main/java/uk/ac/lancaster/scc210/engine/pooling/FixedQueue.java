package uk.ac.lancaster.scc210.engine.pooling;

import java.util.LinkedList;

public class FixedQueue<T> extends LinkedList<T> {
    private final int capacity;

    FixedQueue(final int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean add(T entity) {
        if (capacity < size()) {
            return super.add(entity);
        }

        return false;
    }
}
