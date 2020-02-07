package uk.ac.lancaster.scc210.engine.resources;

/**
 * The ResourceNotFoundException.
 */
public class ResourceNotFoundException extends Exception {
    /**
     * Instantiates a new ResourceNotFoundException.
     *
     * @param fileName the file name of the resource
     */
    public ResourceNotFoundException(String fileName) {
        super(String.format("Unable to load %s", fileName));
    }
}
