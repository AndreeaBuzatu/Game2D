package uk.ac.lancaster.scc210.engine.resources;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String fileName) {
        super(String.format("Unable to load %s", fileName));
    }
}
