package uk.ac.lancaster.scc210.engine.resources;

import java.io.IOException;
import java.nio.file.Path;

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
     * @param path the path
     * @throws IOException if the image can't be found
     */
    public abstract void loadFromFile(Path path) throws IOException;

    /**
     * Gets the current resource.
     *
     * @return the resource
     */
    public T getResource() {
        return resource;
    }
}
