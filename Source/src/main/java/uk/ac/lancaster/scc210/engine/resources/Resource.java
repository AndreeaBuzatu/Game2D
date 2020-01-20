package uk.ac.lancaster.scc210.engine.resources;

import java.io.IOException;
import java.nio.file.Path;

/**
 * The type Resource.
 *
 * @param <T> the type parameter
 */
public abstract class Resource<T> {
    /**
     * The Resource.
     */
    T resource;

    /**
     * Instantiates a new Resource.
     *
     * @param resource the resource
     */
    Resource(T resource) {
        this.resource = resource;
    }

    /**
     * Load from file.
     *
     * @param path the path
     * @throws IOException the io exception
     */
    public abstract void loadFromFile(Path path) throws IOException;

    /**
     * Sets resource.
     *
     * @param resource the resource
     */
    void setResource(T resource) {
        this.resource = resource;
    }

    /**
     * Gets resource.
     *
     * @return the resource
     */
    public T getResource() {
        return resource;
    }
}
