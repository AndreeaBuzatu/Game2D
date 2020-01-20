package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.service.Service;

import java.util.HashMap;

/**
 * The type Content manager.
 *
 * @param <T> the type parameter
 */
public class ContentManager<T> implements Service {
    private final HashMap<String, T> content;

    private final T alternative;

    /**
     * Instantiates a new Content manager.
     *
     * @param alternative the alternative
     */
    protected ContentManager(T alternative) {
        this.alternative = alternative;

        content = new HashMap<>();
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    protected void put(String key, T value) {
        content.put(key, value);
    }

    /**
     * Get t.
     *
     * @param key the key
     * @return the t
     */
    public T get(final String key) {
        return content.getOrDefault(key, alternative);
    }
}
