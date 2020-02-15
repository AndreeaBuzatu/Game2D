package uk.ac.lancaster.scc210.engine.resources;

import uk.ac.lancaster.scc210.engine.StateBasedGame;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * Contains the functionality and error handling for loading a resource
 */
public class ResourceLoader {
    private static final String ERROR_TITLE = "Unable to load resource";

    /**
     * Given a resource, load it. If the resource fails to load, show an error message in the box
     *
     * @param <T>      the type parameter
     * @param resource the resource
     * @param fileName the file name
     * @throws ResourceNotFoundException the resource not found exception
     */
    public static <T extends Resource> void loadFromStream(T resource, String fileName) throws ResourceNotFoundException {
        InputStream stream = loadFromRes(fileName);

        try {
            resource.loadFromFile(stream);

            StateBasedGame.LOGGER.log(Level.INFO, String.format("[%s] Loading %s", resource.getClass().getName(), fileName));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, String.format("Unable to load %s", fileName), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);

            throw new ResourceNotFoundException(fileName);
        }
    }

    private static InputStream loadFromRes(String fileName) throws ResourceNotFoundException {
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(fileName);

        if (stream == null) {
            throw new ResourceNotFoundException(fileName);
        }

        return stream;
    }
}
