package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.service.Service;

import java.util.Collection;
import java.util.HashMap;

/**
 * A generic container which wraps a HashMap. The purpose of this is to hide some of the functionality of HashMap.
 * This should be used to as a Super Class. Child classes will store content found in the game (i.e. textures, sounds)
 *
 * @param <T> the type parameter
 */
public class ContentManager<T> implements Service {
    private final HashMap<String, T> content;

    private final T alternative;

    /**
     * Instantiates a new Content manager.
     *
     * @param alternative if the given key cannot be found, provide the alternative
     */
    protected ContentManager(T alternative) {
        this.alternative = alternative;

        content = new HashMap<>();
    }

    /**
     * Put an item into the HashMap
     *
     * @param key   the key
     * @param value the value
     */
    protected void put(String key, T value) {
        content.put(key, value);
    }

    /**
     * Get an item out of the HashMap.
     *
     * @param key the key
     * @return the t
     */
    public T get(final String key) {
        return content.getOrDefault(key, alternative);
    }

    protected Collection<T> values() {
        return content.values();
    }
}
