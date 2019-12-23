package uk.ac.lancaster.scc210.content;

import java.util.HashMap;

public class ContentManager<T> {
    private HashMap<String, T> content;

    private T alternative;

    ContentManager(T alternative) {
        this.alternative = alternative;

        content = new HashMap<>();
    }

    void put(String key, T value) {
        content.put(key, value);
    }

    public T get(String key) {
        return content.getOrDefault(key, alternative);
    }
}
