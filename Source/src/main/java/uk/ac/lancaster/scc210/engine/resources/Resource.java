package uk.ac.lancaster.scc210.engine.resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a generic Resource found within the game. This could be textures, sounds, text etc.
 *
 * @param <T> resource to load from
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
     * Load the given resource from a path.
     *
     * @param stream the stream
     * @throws IOException if the image can't be found
     */
    public abstract void loadFromFile(InputStream stream) throws IOException;

    /**
     * Gets the current resource.
     *
     * @return the resource
     */
    public T getResource() {
        return resource;
    }
}
