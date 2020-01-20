package uk.ac.lancaster.scc210.engine.resources;

/**
 * The type Resource not found exception.
 */
public class ResourceNotFoundException extends Exception {
    /**
     * Instantiates a new Resource not found exception.
     *
     * @param fileName the file name
     */
    public ResourceNotFoundException(String fileName) {
        super(String.format("Unable to load %s", fileName));
    }
}
