package uk.ac.lancaster.scc210.engine.pooling;

import java.util.LinkedList;

/**
 * A wrapper class for Queue which limits Queue to a given length
 *
 * @param <T> item to store in the Queue
 */
class FixedQueue<T> extends LinkedList<T> {
    private final int capacity;

    /**
     * Instantiate a new FixedQueue
     *
     * @param capacity the capacity for the new Queue
     */
    FixedQueue(final int capacity) {
        this.capacity = capacity;
    }

    /**
     * Add a new item to the Queue. Will only be added if capacity < size()
     *
     * @param item the item to add the the Queue
     * @return if the item is actually added to the queue or not (capacity < size())
     */
    @Override
    public boolean add(T item) {
        if (capacity < size()) {
            return super.add(item);
        }

        return false;
    }
}
