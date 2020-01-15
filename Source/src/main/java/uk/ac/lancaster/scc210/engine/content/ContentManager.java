package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.service.Service;

import java.util.HashMap;

public class ContentManager<T> implements Service {
    private final HashMap<String, T> content;

    private final T alternative;

    protected ContentManager(T alternative) {
        this.alternative = alternative;

        content = new HashMap<>();
    }

    protected void put(String key, T value) {
        content.put(key, value);
    }

    public T get(final String key) {
        return content.getOrDefault(key, alternative);
    }
}
