package uk.ac.lancaster.scc210.content;

import java.util.HashMap;

public class ContentManager<T> {
    private final HashMap<String, T> content;

    private final T alternative;

    ContentManager(T alternative) {
        this.alternative = alternative;

        content = new HashMap<>();
    }

    void put(String key, T value) {
        content.put(key, value);
    }

    public T get(final String key) {
        return content.getOrDefault(key, alternative);
    }
}
