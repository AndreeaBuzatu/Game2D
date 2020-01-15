package uk.ac.lancaster.scc210.engine.resources;

import java.io.IOException;
import java.nio.file.Path;

public abstract class Resource<T> {
    T resource;

    Resource(T resource) {
        this.resource = resource;
    }

    public abstract void loadFromFile(Path path) throws IOException;

    void setResource(T resource) {
        this.resource = resource;
    }

    public T getResource() {
        return resource;
    }
}
